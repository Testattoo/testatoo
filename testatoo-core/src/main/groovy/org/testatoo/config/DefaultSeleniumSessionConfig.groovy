package org.testatoo.config

import com.thoughtworks.selenium.CommandProcessor
import com.thoughtworks.selenium.DefaultSelenium
import com.thoughtworks.selenium.HttpCommandProcessor
import com.thoughtworks.selenium.Selenium
import org.testatoo.config.selenium.SeleniumSessionConfig
import org.testatoo.config.selenium.SeleniumSessionConfigBuilder

import java.util.logging.Level
import java.util.logging.Logger

import static org.testatoo.config.Ensure.notNull;

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
        return registerProvider(new Provider<Selenium>() {
            @Override
            public Selenium get() {
                return session
            }
        });
    }

    @Override
    public ScopedProvider<SeleniumSessionConfigBuilder> registerProvider(Provider<Selenium> session) {
        notNull(session, "Selenium session provider");
        final Provider<Selenium> singleton = SingletonProvider.from(session)
        final DefaultSeleniumSessionConfigBuilder seleniumSessionConfigBuilder = new DefaultSeleniumSessionConfigBuilder(this)
        return new ScopedProvider<SeleniumSessionConfigBuilder>() {
            @Override
            public SeleniumSessionConfigBuilder scope(Scope scope) {
                if (scope == Scope.TEST_CLASS) {
                    config.register(new EventListener(Priority.IMPLEMENTATION) {
                        @Override
                        void onStart() throws Throwable {
                            Selenium seleniumSession = singleton.get()
                            if (DefaultSeleniumSessionConfig.LOGGER.isLoggable(Level.FINE))
                                DefaultSeleniumSessionConfig.LOGGER.fine("Starting Selenium session...")
                            seleniumSessionConfigBuilder.fireBeforeStart(seleniumSession)
                            seleniumSession.start()
                            seleniumSessionConfigBuilder.fireAfterStart(seleniumSession)
                        }

                        @Override
                        void onStop() throws Throwable {
                            Selenium seleniumSession = singleton.get()
                            if (DefaultSeleniumSessionConfig.LOGGER.isLoggable(Level.FINE))
                                DefaultSeleniumSessionConfig.LOGGER.fine("Stopping Selenium session...")
                            seleniumSessionConfigBuilder.fireBeforeStop(seleniumSession)
                            seleniumSession.stop()
                            seleniumSessionConfigBuilder.fireAfterStop(seleniumSession)
                        }
                    });
                } else if (scope == Scope.TEST_SUITE) {
                    config.register(new EventListener(Priority.IMPLEMENTATION) {
                        @Override
                        void onStart() throws Throwable {
                            Selenium seleniumSession = singleton.get()
                            // if it is a DefaultSelenium, it can be managed statically
                            // otherwise fallback to default behavior
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
                                    if (!DefaultSeleniumSessionConfig.SCOPE_TEST_SUITE.containsKey(key)) {
                                        if (DefaultSeleniumSessionConfig.LOGGER.isLoggable(Level.FINE))
                                            DefaultSeleniumSessionConfig.LOGGER.fine(String.format("Starting Selenium session: website=%s, browser=%s, server=%s", browserURL, browserStartCommand, rcServerLocation))
                                        seleniumSessionConfigBuilder.fireBeforeStart(seleniumSession)
                                        seleniumSession.start()
                                        seleniumSessionConfigBuilder.fireAfterStart(seleniumSession)
                                        DefaultSeleniumSessionConfig.SCOPE_TEST_SUITE.put(key, seleniumSession)
                                    } else {
                                        seleniumSession = DefaultSeleniumSessionConfig.SCOPE_TEST_SUITE.get(key)
                                    }
                                }
                            } else {
                                if (DefaultSeleniumSessionConfig.LOGGER.isLoggable(Level.FINE))
                                    DefaultSeleniumSessionConfig.LOGGER.fine("Starting Selenium session...")
                                seleniumSessionConfigBuilder.fireBeforeStart(seleniumSession)
                                seleniumSession.start()
                                seleniumSessionConfigBuilder.fireAfterStart(seleniumSession)
                            }
                        }

                        @Override
                        void onStop() throws Throwable {
                            Selenium seleniumSession = singleton.get()
                            // if it is a DefaultSelenium, it can be managed statically
                            // otherwise fallback to default behavior
                            if (seleniumSession instanceof DefaultSelenium) {
                                // nothing to do, the selenium session will be cleared by shutdown hook
                            } else {
                                if (DefaultSeleniumSessionConfig.LOGGER.isLoggable(Level.FINE))
                                    DefaultSeleniumSessionConfig.LOGGER.fine("Stopping Selenium session...")
                                seleniumSession.stop()
                            }
                        }
                    });
                    Shutdown.addHook(new Shutdown.Hook() {
                        @Override
                        public void onShutdown() throws Throwable {
                            Selenium seleniumSession = singleton.get()
                            // if it is a DefaultSelenium, it can be managed statically
                            if (seleniumSession instanceof DefaultSelenium) {
                                singleton.get().stop()
                            }
                        }
                    });
                }
                return seleniumSessionConfigBuilder
            }
        };
    }

}