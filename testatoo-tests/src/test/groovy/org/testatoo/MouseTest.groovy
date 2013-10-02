package org.testatoo

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.Testatoo
import org.testatoo.core.component.Button
import org.testatoo.core.component.CheckBox
import org.testatoo.core.component.Radio

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(TestModule)
class MouseTest {

    @Delegate
    private static Testatoo testatoo = new Testatoo()

    @BeforeClass
    public static void openTestPage() {
        open('/mouse.html')
    }

    @Test
    public void click() {
        Button button = $('#button_1') as Button
        assertThat { button.has text.equalsTo('Button') }
        clickOn button
        assertThat { button.has text.equalsTo('Button Clicked !') }

        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat {checkBox.is unchecked}
        clickOn(checkBox)
        assertThat {checkBox.is checked}

        Radio radio = $('#radio') as Radio
        assertThat {radio.is unchecked}
        clickOn(radio)
        assertThat {radio.is checked}

        // test click on dropdown items
    }

    @Test
    public void doubleClick() {
        Button button = $('#button_2') as Button
        assertThat { button.has text.equalsTo('Button') }
        doubleClickOn button
        assertThat { button.has text.equalsTo('Button Double Clicked!') }
    }

    @Test
    public void rightClick() {
        Button button = $('#button_5') as Button
        assertThat { button.has text.equalsTo('Button') }
        rightClickOn button
        assertThat { button.has text.equalsTo('Button Right Clicked!') }
    }

    @Test
    public void mouseOver() {
        Button button = $('#button_3') as Button
        assertThat { button.has text.equalsTo('Button') }
        mouseOverOn button
        assertThat { button.has text.equalsTo('Button Mouse Over!') }
    }

    @Test
    public void mouseOut() {
        Button button = $('#button_4') as Button
        assertThat { button.has text.equalsTo('Button') }
        mouseOutOn button
        assertThat { button.has text.equalsTo('Button Mouse Out!') }
    }

//    @Test
//    public void test_component_drag_and_drop() throws Exception {
//        assertThat(component(Image.class, "image").isVisible(), is(false));
//        Panel draggablePanel = component(Panel.class, "draggable");
//        Panel droppablePanel = component(Panel.class, "droppable");
//
//        Mouse.drag(draggablePanel).on(droppablePanel);
//
//        assertThat(component(Image.class, "image"), is(visible()));
//    }


}
