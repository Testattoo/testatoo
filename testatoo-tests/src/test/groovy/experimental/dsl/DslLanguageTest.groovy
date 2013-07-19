package experimental.dsl

import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.experimental.dsl.Testatoo
import org.testatoo.html.conf.Module

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@RunWith(TestatooJunitRunner.class)
@TestatooModules(Module.class)
class DslLanguageTest extends Testatoo {
}
