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

import com.ovea.tajin.server.Container;
import org.testatoo.config.Provider;
import org.testatoo.config.Scope;
import org.testatoo.config.ScopedProvider;
import org.testatoo.config.SingletonProvider;
import org.testatoo.config.container.ContainerConfig;
import org.testatoo.config.container.ContainerInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.testatoo.config.testatoo.Ensure.notNull;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultContainerConfig implements ContainerConfig {

    private static final Map<Integer, Container> CONTAINERS = new ConcurrentHashMap<Integer, Container>();

    private final DefaultTestatooConfig config;

    public DefaultContainerConfig(DefaultTestatooConfig config) {
        this.config = config;
    }

    @Override
    public ScopedProvider<ContainerConfig> register(final ContainerInfo container) {
        notNull(container, "Container");
        return registerProvider(new Provider<ContainerInfo>() {
            @Override
            public ContainerInfo get() {
                return container;
            }
        });
    }

    @Override
    public ScopedProvider<ContainerConfig> registerProvider(Provider<ContainerInfo> container) {
        notNull(container, "Container provider");
        final Provider<ContainerInfo> singleton = SingletonProvider.from(container);
        return new ScopedProvider<ContainerConfig>() {
            @Override
            public ContainerConfig scope(Scope scope) {
                switch (scope) {
                    case TEST_SUITE:
                        config.register(new EventListener(Priority.CONTAINER) {
                            @Override
                            void onStart() throws Throwable {
                                ContainerInfo container = singleton.get();
                                if (!CONTAINERS.containsKey(container.configuration.port())) {
                                    Container c = container.get();
                                    CONTAINERS.put(container.configuration.port(), c);
                                    c.start();
                                }
                            }

                            @Override
                            void onStop() {
                            }
                        });
                        break;
                    case TEST_CLASS:
                        config.register(new EventListener(Priority.CONTAINER) {
                            @Override
                            void onStart() throws Throwable {
                                ContainerInfo container = singleton.get();
                                Container c = CONTAINERS.get(container.configuration.port());
                                if (c == null) {
                                    c = container.get();
                                    CONTAINERS.put(container.configuration.port(), c);
                                }
                                c.start();
                            }

                            @Override
                            void onStop() throws Throwable {
                                ContainerInfo container = singleton.get();
                                CONTAINERS.get(container.configuration.port()).stop();
                            }
                        });
                        break;
                }
                return DefaultContainerConfig.this;
            }
        };
    }

}
