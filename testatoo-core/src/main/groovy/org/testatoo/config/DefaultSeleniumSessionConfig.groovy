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

import com.thoughtworks.selenium.CommandProcessor
import com.thoughtworks.selenium.DefaultSelenium
import com.thoughtworks.selenium.HttpCommandProcessor
import com.thoughtworks.selenium.Selenium
import org.testatoo.config.selenium.SeleniumSessionConfig
import org.testatoo.config.selenium.SeleniumSessionConfigBuilder

import java.util.logging.Level
import java.util.logging.Logger

import static org.testatoo.config.Ensure.notNull
import static org.testatoo.config.Priority.IMPLEMENTATION
import static org.testatoo.config.Scope.TEST_CLASS
import static org.testatoo.config.Scope.TEST_SUITE

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultSeleniumSessionConfig implements SeleniumSessionConfig {

    private static final Logger LOGGER = Logger.getLogger(DefaultSeleniumSessionConfig.class.name)
    private static final Map<String, Selenium> SCOPE_TEST_SUITE = new HashMap<String, Selenium>()

    private final DefaultTestatooConfig config

    DefaultSeleniumSessionConfig(DefaultTestatooConfig config) {
        this.config = config
    }

    @Override
    public ScopedProvider<SeleniumSessionConfigBuilder> register(final Selenium session) {
        notNull(session, "Selenium session")
        return registerProvider({ session } as Provider<Selenium>);
    }

    @Override
    public ScopedProvider<SeleniumSessionConfigBuilder> registerProvider(Provider<Selenium> session) {
        notNull(session, "Selenium session provider");
        final Provider<Selenium> singleton = SingletonProvider.from(session)
        final DefaultSeleniumSessionConfigBuilder seleniumSessionConfigBuilder = new DefaultSeleniumSessionConfigBuilder(this)
        return { Scope scope ->
            if (scope == TEST_CLASS) {

                config.register(new EventListener(IMPLEMENTATION) {
                    @Override
                    void onStart() throws Throwable {
                        Selenium seleniumSession = singleton.get()
                        if (LOGGER.isLoggable(Level.FINE))
                            LOGGER.fine("Starting Selenium session...")
                        seleniumSessionConfigBuilder.fireBeforeStart(seleniumSession)
                        seleniumSession.start()
                        seleniumSessionConfigBuilder.fireAfterStart(seleniumSession)
                    }

                    @Override
                    void onStop() throws Throwable {
                        Selenium seleniumSession = singleton.get()
                        if (LOGGER.isLoggable(Level.FINE))
                            LOGGER.fine("Stopping Selenium session...")
                        seleniumSessionConfigBuilder.fireBeforeStop(seleniumSession)
                        seleniumSession.stop()
                        seleniumSessionConfigBuilder.fireAfterStop(seleniumSession)
                    }
                });

            }

            if (scope == TEST_SUITE) {

                config.register(new EventListener(IMPLEMENTATION) {
                    @Override
                    void onStart() throws Throwable {
                        Selenium seleniumSession = singleton.get()
                        // If it is a DefaultSelenium, it can be managed statically
                        // Otherwise fallback to default behavior
                        if (seleniumSession instanceof DefaultSelenium) {
                            // All of this is a very hugly hack to determine if we can reuse an existing session
                            // since we cannot recover the needed information from Selenium interface.
                            CommandProcessor commandProcessor = Reflect.get(seleniumSession, "commandProcessor")
                            if (commandProcessor instanceof HttpCommandProcessor) {
                                // rcServerLocation + browserURL + browserStartCommand
                                String rcServerLocation = Reflect.get(commandProcessor, "rcServerLocation")
                                String browserURL = Reflect.get(commandProcessor, "browserURL")
                                String browserStartCommand = Reflect.get(commandProcessor, "browserStartCommand")
                                String key = rcServerLocation + browserURL + browserStartCommand
                                if (!SCOPE_TEST_SUITE.containsKey(key)) {
                                    if (LOGGER.isLoggable(Level.FINE))
                                        LOGGER.fine(String.format("Starting Selenium session: website=%s, browser=%s, server=%s", browserURL, browserStartCommand, rcServerLocation))
                                    seleniumSessionConfigBuilder.fireBeforeStart(seleniumSession)
                                    seleniumSession.start()
                                    seleniumSessionConfigBuilder.fireAfterStart(seleniumSession)
                                    SCOPE_TEST_SUITE.put(key, seleniumSession)
                                } else {
                                    seleniumSession = SCOPE_TEST_SUITE.get(key)
                                }
                            }
                        } else {
                            if (LOGGER.isLoggable(Level.FINE))
                                LOGGER.fine("Starting Selenium session...")
                            seleniumSessionConfigBuilder.fireBeforeStart(seleniumSession)
                            seleniumSession.start()
                            seleniumSessionConfigBuilder.fireAfterStart(seleniumSession)
                        }
                    }

                    @Override
                    void onStop() throws Throwable {
                        Selenium seleniumSession = singleton.get()
                        // If it is a DefaultSelenium, it can be managed statically
                        // Otherwise fallback to default behavior
                        if (seleniumSession instanceof DefaultSelenium) {
                            // Nothing to do, the selenium session will be cleared by shutdown hook
                        } else {
                            if (LOGGER.isLoggable(Level.FINE))
                                LOGGER.fine("Stopping Selenium session...")
                            seleniumSession.stop()
                        }
                    }
                });

                Shutdown.addHook({
                    Selenium seleniumSession = singleton.get()
                    // if it is a DefaultSelenium, it can be managed statically
                    if (seleniumSession instanceof DefaultSelenium) {
                        singleton.get().stop()
                    }
                } as Shutdown.Hook)

            }

            return seleniumSessionConfigBuilder

        } as ScopedProvider<SeleniumSessionConfigBuilder>
    }

}