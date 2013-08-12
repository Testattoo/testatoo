package org.testatoo.config

import org.testatoo.config.lifecycle.LifeCycleConfig
import org.testatoo.config.lifecycle.TestInterceptor
import org.testatoo.config.lifecycle.TestListener

import static org.testatoo.config.Ensure.notNull

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

// TOTO GROVVYFY EventListener
final class DefaultLifeCycleConfig implements LifeCycleConfig {

    private final DefaultTestatooConfig config

    DefaultLifeCycleConfig(DefaultTestatooConfig config) {
        this.config = config
    }

    @Override
    public LifeCycleConfig onStart(final Runnable runnable) {
        notNull(runnable, "onStart listener")
        config.register(new EventListener(Priority.LIFECYCLE) {
            @Override
            void onStart() {
                runnable.run()
            }
        });
        return this
    }

    @Override
    public LifeCycleConfig onStop(final Runnable runnable) {
        notNull(runnable, "onStop listener")
        config.register(new EventListener(Priority.LIFECYCLE) {
            @Override
            void onStop() {
                runnable.run()
            }
        });
        return this
    }

    @Override
    public LifeCycleConfig onTest(TestInterceptor interceptor) {
        notNull(interceptor, "Test interceptor")
        config.register(interceptor)
        return this
    }

    @Override
    public LifeCycleConfig onTest(TestListener listener) {
        notNull(listener, "Test listener")
        config.add(listener)
        return this
    }
}