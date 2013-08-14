/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.config

import org.testatoo.config.annotation.ConcurrentEvaluation
import org.testatoo.core.evaluator.EvaluatorHolder

import java.lang.reflect.Method
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultConcurrentTestingConfig implements ConcurrentTestingConfig {

    private static final Logger LOGGER = Logger.getLogger(ConcurrentTestingConfig.class.getName())

    final DefaultTestatooConfig testatooConfig

    private Executor executor = new Executor() {
        @Override
        public void execute(Runnable command) {
            command.run()
        }
    };

    DefaultConcurrentTestingConfig(DefaultTestatooConfig testatooConfig) {
        this.testatooConfig = testatooConfig
    }

    @Override
    public void threads(final int nThreads) {
        testatooConfig.register(new EventListener(Priority.CONCURRENT) {
            int jobs = 0
            ExecutorService executorService
            CompletionService<?> completionService

            // Equivalent to @BeforeClass
            @Override
            void onStart() {
                executorService = new ThreadPoolExecutor(nThreads, nThreads, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy()) {
                    @SuppressWarnings("unchecked")
                    @Override
                    public <T> Future<T> submit(Callable<T> task) {
                        jobs++;
                        return (Future<T>) completionService.submit((Callable) task)
                    }

                    @Override
                    public Future<?> submit(Runnable task) {
                        jobs++;
                        return completionService.submit(task, null)
                    }
                };
                completionService = new ExecutorCompletionService<Void>(executorService)
                EvaluatorHolder.setConcurrentExecutor(executorService)
            }

            // Equivalent to @AfterClass
            @Override
            void onStop() throws Throwable {
                while (jobs-- > 0) {
                    try {
                        completionService.take().get()
                    } catch (ExecutionException e) {
                        throw e.getCause()
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt()
                        executorService.shutdownNow()
                        executorService = null
                    }
                }
                if (executorService != null) {
                    executorService.shutdown()
                    executorService = null
                }
                EvaluatorHolder.clearConcurrentExecutor()
            }
        });
    }

    @Override
    public void distributeTestsAmongst(final String... evaluatorNames) {
        final AtomicLong jobs = new AtomicLong(0)
        final BlockingQueue<TestatooRunnable> runnables = new LinkedBlockingQueue<TestatooRunnable>()

        executor = new Executor() {
            @Override
            public void execute(Runnable runnable) {
                TestatooRunnable testBlock = (TestatooRunnable) runnable
                if (testBlock.testMethod().isAnnotationPresent(ConcurrentEvaluation.class)
                        || testBlock.testClass().isAnnotationPresent(ConcurrentEvaluation.class)) {
                    if (LOGGER.isLoggable(Level.FINE))
                        LOGGER.fine(String.format("[%s] Scheduling test %s for concurrent execution", Thread.currentThread().getName(), testBlock.testMethod()))
                    jobs.incrementAndGet()
                    runnables.offer(testBlock)
                } else {
                    if (LOGGER.isLoggable(Level.FINE))
                        LOGGER.fine(String.format("[%s] Running test %s", Thread.currentThread().getName(), testBlock.testMethod()))
                    testBlock.run()
                }
            }
        };

        testatooConfig.register(new EventListener(Priority.CONCURRENT) {
            Thread[] threads = new Thread[evaluatorNames.length]

            // equivalent to @BeforeClass
            @Override
            void onStart() {
                for (int i = 1; i < threads.length; i++) {
                    final String name = evaluatorNames[i]
                    threads[i] = new Thread(name + "-Thread") {
                        @Override
                        public void run() {
                            while (!Thread.currentThread().isInterrupted()) {
                                try {
                                    TestatooRunnable testBlock = runnables.take()
                                    if (LOGGER.isLoggable(Level.FINE))
                                        LOGGER.fine(String.format("[%s] Running test %s", Thread.currentThread().name, testBlock.testMethod()))
                                    EvaluatorHolder.withEvaluator(name).run(testBlock)
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt()
                                    if (LOGGER.isLoggable(Level.FINE))
                                        LOGGER.fine(String.format("[%s] interrupted !", Thread.currentThread().name))
                                    return;
                                }
                            }
                        }
                    };
                    if (LOGGER.isLoggable(Level.FINE))
                        LOGGER.fine(String.format("Starting thread for evaluator: %s", name))
                    threads[i].start()
                }
            }

            // equivalent to @AfterClass
            @Override
            void onStop() throws Throwable {
                while (!runnables.isEmpty()) {
                    TestatooRunnable testBlock = runnables.poll(1, TimeUnit.SECONDS)
                    if (testBlock != null) {
                        if (LOGGER.isLoggable(Level.FINE))
                            LOGGER.fine(String.format("[%s] Running test %s on evaluator %s", Thread.currentThread().name, testBlock.testMethod(), evaluatorNames[0]))
                        EvaluatorHolder.withEvaluator(evaluatorNames[0]).run(testBlock)
                    }
                }
                if (LOGGER.isLoggable(Level.FINE))
                    LOGGER.fine("Stopping evaluator threads...")
                for (Thread thread : threads) {
                    if (thread != null)
                        thread.interrupt()
                }
            }
        });

    }

    public void scheduleTest(final Class<?> testClass, final Method testMethod, final Runnable testBlock) {
        executor.execute(new TestatooRunnable() {
            @Override
            public Method testMethod() {
                testMethod
            }

            @Override
            public Class<?> testClass() {
                testClass
            }

            @Override
            public void run() {
                testBlock.run()
            }
        });
    }

    private interface TestatooRunnable extends Runnable {

        Method testMethod()

        Class<?> testClass()
    }
}