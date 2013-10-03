package org.testatoo

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.testatoo.config.TestatooJunitRunner
import org.testatoo.config.TestatooModules
import org.testatoo.core.Testatoo
import org.testatoo.core.component.*
import org.testatoo.core.property.Title

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
        assertThat { button.has text.equalsTo('Button') }
        clickOn button
        assertThat { button.has text.equalsTo('Button Clicked!') }

        CheckBox checkBox = $('#checkbox') as CheckBox
        assertThat {checkBox.is unchecked}
        clickOn checkBox
        assertThat {checkBox.is checked}

        Radio radio = $('#radio') as Radio
        assertThat {radio.is unchecked}
        clickOn radio
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
        mouseOutOff button
        assertThat { button.has text.equalsTo('Button Mouse Out!') }
    }

    @Test
    public void dragAndDrop() {
        DropPanel dropPanel = $('#droppable') as DropPanel
        assertThat { dropPanel.has title.equalsTo('Drop here')}

        Panel dragPanel =  $('#draggable') as Panel

        drag dragPanel on dropPanel
        assertThat { dropPanel.has title.equalsTo('Dropped!')}
    }

    class DropPanel extends Panel  {
        DropPanel() {
            support Title, { Component c -> c.evaluator.getString("testatoo.ext.getText('${c.id} h1')") }
        }
    }


}
