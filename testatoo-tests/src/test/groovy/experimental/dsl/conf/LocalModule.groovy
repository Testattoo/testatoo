package experimental.dsl.conf

import org.testatoo.config.AbstractTestatooModule
import org.testatoo.config.Scope

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class LocalModule extends AbstractTestatooModule {
    static int seleniumPort = -1;

    @Override
    protected void configure() {

        System.setProperty("host", "localhost");

        if (seleniumPort == -1) {
            seleniumPort = findFreePort();
        }

        seleniumServers().registerProvider(createSeleniumServer()
                .port(seleniumPort)
                .useSingleWindow(true)
                .build())
                .scope(Scope.TEST_SUITE);

        seleniumSessions()
                .registerProvider(createSeleniumSession()
                .website("http://" + System.getProperty("host") + ":" + System.getProperty("port"))
                .browser("*googlechrome")
                .serverHost("localhost")
                .serverPort(seleniumPort)
                .build())
                .scope(Scope.TEST_CLASS)
                .withTimeout(20000);
    }
}