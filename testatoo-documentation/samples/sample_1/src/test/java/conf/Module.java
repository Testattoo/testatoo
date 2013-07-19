package conf;

import org.testatoo.config.AbstractTestatooModule;

public class Module extends AbstractTestatooModule {
    @Override
    protected void configure() {
        install(new ContainerModule());
        install(new SeleniumModule());
    }
}
