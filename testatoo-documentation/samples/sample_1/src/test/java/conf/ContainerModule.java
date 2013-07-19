package conf;

import org.testatoo.config.AbstractTestatooModule;

import static org.testatoo.config.Scope.TEST_SUITE;
import static org.testatoo.container.TestatooContainer.JETTY;

public class ContainerModule extends AbstractTestatooModule {
    @Override
    protected void configure() {
        containers().register(createContainer()
                .implementedBy(JETTY)
                .webappRoot("src/main/webapp")
                .port(8080)
                .build())
                .scope(TEST_SUITE);
    }
}
