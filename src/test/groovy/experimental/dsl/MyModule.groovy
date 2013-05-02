package experimental.dsl

import org.testatoo.config.AbstractTestatooModule
import org.testatoo.config.Scope
import org.testatoo.config.cartridge.TestatooCartridge

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-01
 */
class MyModule extends AbstractTestatooModule {

    static int seleniumPort = -1;

    @Override
    protected void configure() {

        if (seleniumPort == -1) {
            seleniumPort = findFreePort();
        }

        seleniumServers().registerProvider(createSeleniumServer()
            .port(4444)
            .useSingleWindow(true)
            .build())
            .scope(Scope.TEST_CLASS);

        seleniumSessions()
            .registerProvider(createSeleniumSession()
            .website("http://localhost:8080")
            .browser("*googlechrome")
            .serverHost("localhost")
            .serverPort(4444)
            .build())
            .scope(Scope.TEST_CLASS)
            .withTimeout(5000)
            .inCartridge(TestatooCartridge.HTML4);

        useAnnotations();
    }
}
