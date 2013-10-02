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
import static org.testatoo.core.Testatoo.waitUntil

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class DSLTest {

    @Delegate
    private static Testatoo testatoo = new Testatoo()

    @BeforeClass
    public static void openTestPage() {
        open('/index.html')
    }

    @Test
    public void test_AND() {
        Button button = $('#button') as Button
        assert button.is(enabled).and(button.is(visible))
        assert button.is(enabled) & button.is(visible)
    }

    @Test
    public void test_OR() {
        Button button = $('#button') as Button
        assert button.is(disabled).or(button.is(visible))
        assert button.is(disabled) | button.is(visible)
    }

    @Test
    public void test_wait() {
        Button button = $('#add-message') as Button
        Button message = $('#msg') as Button

        assert button.is(enabled) & button.is(visible)
        assert button.is(enabled).and(button.is(visible))

        assert message.is(missing)

        button.click()

        assert button.is(enabled).or(button.is(visible))
        assert button.is(enabled) | button.is(visible)

        assert button.is(disabled) & button.is(visible)
        assert button.is(disabled).and(button.is(visible))

        //waitUntil button.is(enabled).or(message.is(visible))
        //waitUntil button.is(enabled) | message.is(visible)

        //waitUntil button.is(enabled).and(message.is(visible)), 10.seconds
        waitUntil button.is(enabled) & message.is(visible), 10.seconds

    }
}
