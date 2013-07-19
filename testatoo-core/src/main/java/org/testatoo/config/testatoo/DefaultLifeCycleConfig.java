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
package org.testatoo.config.testatoo;

import org.aopalliance.intercept.MethodInterceptor;
import org.testatoo.config.lifecycle.LifeCycleConfig;
import org.testatoo.config.lifecycle.TestListener;

import static org.testatoo.config.testatoo.Ensure.notNull;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultLifeCycleConfig implements LifeCycleConfig {

    private final DefaultTestatooConfig config;

    DefaultLifeCycleConfig(DefaultTestatooConfig config) {
        this.config = config;
    }

    @Override
    public LifeCycleConfig onStart(final Runnable runnable) {
        notNull(runnable, "onStart listener");
        config.register(new EventListener(Priority.LIFECYCLE) {
            @Override
            void onStart() {
                runnable.run();
            }
        });
        return this;
    }

    @Override
    public LifeCycleConfig onStop(final Runnable runnable) {
        notNull(runnable, "onStop listener");
        config.register(new EventListener(Priority.LIFECYCLE) {
            @Override
            void onStop() {
                runnable.run();
            }
        });
        return this;
    }

    @Override
    public LifeCycleConfig onTest(MethodInterceptor interceptor) {
        notNull(interceptor, "Test interceptor");
        config.register(interceptor);
        return this;
    }

    @Override
    public LifeCycleConfig onTest(TestListener listener) {
        notNull(listener, "Test listener");
        config.add(listener);
        return this;
    }
}
