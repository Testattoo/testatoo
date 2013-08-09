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
        return registerProvider(new Provider<SeleniumServer>() {
            @Override
            public SeleniumServer get() {
                return seleniumServer
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
                    case Scope.TEST_SUITE:
                        config.register(new EventListener(Priority.SELENIUM_SERVER) {
                            @Override
                            void onStart() throws Throwable {
                                SeleniumServer server = singleton.get()
                                if (!DefaultSeleniumServerConfig.SCOPE_TEST_SUITE.contains(server.port)) {
                                    if (DefaultSeleniumServerConfig.LOGGER.isLoggable(Level.FINE))
                                        DefaultSeleniumServerConfig.LOGGER.fine("Starting SeleniumServer on port " + server.port + "...")
                                    server.start();
                                    DefaultSeleniumServerConfig.SCOPE_TEST_SUITE.add(server.port)
                                }
                            }

                            @Override
                            void onStop() {
                            }
                        });
                        Shutdown.addHook(new Shutdown.Hook() {
                            @Override
                            public void onShutdown() throws Throwable {
                                SeleniumServer server = singleton.get()
                                if (DefaultSeleniumServerConfig.LOGGER.isLoggable(Level.FINE))
                                    DefaultSeleniumServerConfig.LOGGER.fine("Stopping SeleniumServer on port " + server.port + "...")
                                server.stop()
                            }
                        });
                        break;
                    case Scope.TEST_CLASS:
                        config.register(new EventListener(Priority.SELENIUM_SERVER) {
                            @Override
                            void onStart() throws Throwable {
                                SeleniumServer server = singleton.get()
                                if (DefaultSeleniumServerConfig.LOGGER.isLoggable(Level.FINE))
                                    DefaultSeleniumServerConfig.LOGGER.fine("Starting SeleniumServer on port " + server.port + "...")
                                server.start()
                            }

                            @Override
                            void onStop() throws Throwable {
                                SeleniumServer server = singleton.get()
                                if (DefaultSeleniumServerConfig.LOGGER.isLoggable(Level.FINE))
                                    DefaultSeleniumServerConfig.LOGGER.fine("Stopping SeleniumServer on port " + server.port + "...")
                                server.stop()
                            }
                        });
                        break;
                }
                return DefaultSeleniumServerConfig.this
            }
        };
    }
}