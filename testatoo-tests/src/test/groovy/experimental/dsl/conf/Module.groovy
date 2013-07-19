package experimental.dsl.conf

import org.testatoo.config.AbstractTestatooModule
import org.testatoo.config.lifecycle.TestListenerAdapter

import java.lang.reflect.Method

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Module extends AbstractTestatooModule {

    @Override
    protected void configure() {
        if (System.getProperty("port") == null) {
            System.setProperty("port", "" + findFreePort());
        }

        install(new org.testatoo.html.conf.LocalModule());
        install(new org.testatoo.html.conf.ContainerModule());

        // TODO; NEW FEATURES TO DOCUMENT
        // 1. Ability to add listeners on test execution
        lifecycle().onTest(new TestListenerAdapter() {
            @Override
            public void onTest(Object instance, Method method) {
                System.out.println("===> Executing: " + method);
            }
        });

        // 2. Ability to add annotation support:
        // Exemple of currently supported annotation:
        // See SeleniumTest for usage
        useAnnotations();
    }
}