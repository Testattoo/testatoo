package experimental.dsl

import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.Testatoo

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class DslLanguageTest extends Testatoo {
}
