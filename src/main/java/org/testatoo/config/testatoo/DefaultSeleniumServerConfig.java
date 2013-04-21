/**
 * Copyright (C) 2013 Ovea <dev@testatoo.org>
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

import org.testatoo.config.Provider;
import org.testatoo.config.Scope;
import org.testatoo.config.ScopedProvider;
import org.testatoo.config.SingletonProvider;
import org.testatoo.config.selenium.SeleniumServerConfig;
import org.testatoo.selenium.server.SeleniumServer;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testatoo.config.testatoo.Ensure.notNull;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultSeleniumServerConfig implements SeleniumServerConfig {

    private static final Logger LOGGER = Logger.getLogger(DefaultSeleniumServerConfig.class.getName());
    private static final Set<Integer> SCOPE_TEST_SUITE = new HashSet<Integer>();

    private final DefaultTestatooConfig config;

    public DefaultSeleniumServerConfig(DefaultTestatooConfig config) {
        this.config = config;
    }

    @Override
    public ScopedProvider<SeleniumServerConfig> register(final SeleniumServer seleniumServer) {
        notNull(seleniumServer, "Selenium server");
        return registerProvider(new Provider<SeleniumServer>() {
            @Override
            public SeleniumServer get() {
                return seleniumServer;
            }
        });
    }

    @Override
    public ScopedProvider<SeleniumServerConfig> registerProvider(final Provider<SeleniumServer> seleniumServer) {
        notNull(seleniumServer, "Selenium server provider");
        final Provider<SeleniumServer> singleton = SingletonProvider.from(seleniumServer);
        return new ScopedProvider<SeleniumServerConfig>() {
            @Override
            public SeleniumServerConfig scope(Scope scope) {
                switch (scope) {
                    case TEST_SUITE:
                        config.register(new EventListener(Priority.SELENIUM_SERVER) {
                            @Override
                            void onStart() {
                                SeleniumServer server = singleton.get();
                                if (!SCOPE_TEST_SUITE.contains(server.getPort())) {
                                    if (LOGGER.isLoggable(Level.FINE))
                                        LOGGER.fine("Starting SeleniumServer on port " + server.getPort() + "...");
                                    server.start();
                                    SCOPE_TEST_SUITE.add(server.getPort());
                                }
                            }

                            @Override
                            void onStop() {
                            }
                        });
                        Shutdown.addHook(new Callable() {
                            @Override
                            public Object call() throws Exception {
                                SeleniumServer server = singleton.get();
                                if (LOGGER.isLoggable(Level.FINE))
                                    LOGGER.fine("Stopping SeleniumServer on port " + server.getPort() + "...");
                                server.stop();
                                return null;
                            }
                        });
                        break;
                    case TEST_CLASS:
                        config.register(new EventListener(Priority.SELENIUM_SERVER) {
                            @Override
                            void onStart() {
                                SeleniumServer server = singleton.get();
                                if (LOGGER.isLoggable(Level.FINE))
                                    LOGGER.fine("Starting SeleniumServer on port " + server.getPort() + "...");
                                server.start();
                            }

                            @Override
                            void onStop() {
                                SeleniumServer server = singleton.get();
                                if (LOGGER.isLoggable(Level.FINE))
                                    LOGGER.fine("Stopping SeleniumServer on port " + server.getPort() + "...");
                                server.stop();
                            }
                        });
                        break;
                }
                return DefaultSeleniumServerConfig.this;
            }
        };
    }
}
