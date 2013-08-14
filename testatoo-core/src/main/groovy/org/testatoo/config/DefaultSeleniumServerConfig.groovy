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

import org.openqa.selenium.server.SeleniumServer
import org.testatoo.config.selenium.SeleniumServerConfig

import java.util.logging.Level
import java.util.logging.Logger

import static org.testatoo.config.Ensure.notNull

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultSeleniumServerConfig implements SeleniumServerConfig {

    private static final Logger LOGGER = Logger.getLogger(DefaultSeleniumServerConfig.class.name)
    private static final Set<Integer> SCOPE_TEST_SUITE = new HashSet<Integer>()

    private final DefaultTestatooConfig config

    public DefaultSeleniumServerConfig(DefaultTestatooConfig config) {
        this.config = config
    }

    @Override
    public ScopedProvider<SeleniumServerConfig> register(final SeleniumServer seleniumServer) {
        notNull(seleniumServer, "Selenium server")
        return registerProvider({ seleniumServer } as Provider<SeleniumServer>)
    }

    @Override
    public ScopedProvider<SeleniumServerConfig> registerProvider(final Provider<SeleniumServer> seleniumServer) {
        notNull(seleniumServer, "Selenium server provider");
        final Provider<SeleniumServer> singleton = SingletonProvider.from(seleniumServer);
        DefaultSeleniumServerConfig that = this
        return { Scope scope ->

            if (scope == Scope.TEST_SUITE) {

                config.register(new EventListener(Priority.SELENIUM_SERVER) {
                    @Override
                    void onStart() throws Throwable {
                        SeleniumServer server = singleton.get()
                        if (!SCOPE_TEST_SUITE.contains(server.port)) {
                            if (LOGGER.isLoggable(Level.FINE))
                                LOGGER.fine("Starting SeleniumServer on port " + server.port + "...")
                            server.start();
                            SCOPE_TEST_SUITE.add(server.port)
                        }
                    }
                });

                Shutdown.addHook({
                    SeleniumServer server = singleton.get()
                    if (LOGGER.isLoggable(Level.FINE))
                        LOGGER.fine("Stopping SeleniumServer on port " + server.port + "...")
                    server.stop()
                } as Shutdown.Hook)

            }

            if (scope == Scope.TEST_CLASS) {

                config.register(new EventListener(Priority.SELENIUM_SERVER) {
                    @Override
                    void onStart() throws Throwable {
                        SeleniumServer server = singleton.get()
                        if (LOGGER.isLoggable(Level.FINE))
                            LOGGER.fine("Starting SeleniumServer on port " + server.port + "...")
                        server.start()
                    }

                    @Override
                    void onStop() throws Throwable {
                        SeleniumServer server = singleton.get()
                        if (LOGGER.isLoggable(Level.FINE))
                            LOGGER.fine("Stopping SeleniumServer on port " + server.port + "...")
                        server.stop()
                    }
                });

            }

            return that

        } as ScopedProvider<SeleniumServerConfig>

    }
}