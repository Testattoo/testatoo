package experimental.dsl.conf

import com.ovea.tajin.server.Server
import org.testatoo.config.AbstractTestatooModule
import org.testatoo.config.Scope

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ContainerModule extends AbstractTestatooModule {
    @Override
    protected void configure() {

        containers().registerProvider(createContainer()
                .implementedBy(Server.JETTY9)
                .webappRoot("src/test/webapp")
                .port(Integer.parseInt(System.getProperty("port")))
                .build())
                .scope(Scope.TEST_SUITE);
    }
}