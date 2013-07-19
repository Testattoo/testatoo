package conf;

import org.testatoo.config.AbstractTestatooModule;
import org.testatoo.config.Scope;
import org.testatoo.config.cartridge.TestatooCartridge;

public class SeleniumModule extends AbstractTestatooModule {
    @Override
    protected void configure() {
        int seleniumPort = findFreePort();

        seleniumServers().register(createSeleniumServer()
                .port(seleniumPort)
                .useSingleWindow(true)
                .build())
                .scope(Scope.TEST_SUITE);

        seleniumSessions().register(createSeleniumSession()
                .website("http://localhost:8080")
                .browser("*firefox")
                .serverHost("localhost")
                .serverPort(seleniumPort).build())
                .scope(Scope.TEST_SUITE)
                .withTimeout(20000)
                .inCartridge(TestatooCartridge.HTML4);
    }
}
