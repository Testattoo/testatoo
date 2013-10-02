package org.testatoo

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.open

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class ExtensionTest {

    @Delegate
    private static Testatoo testatoo = new Testatoo()

    @BeforeClass
    public static void openTestPage() {
        open('/component.html')
    }

    @Test
    public void are_have_extension() {
        Button button = $('#button') as Button

        // are / have extension
        assert [button].are(enabled)

    }
}
