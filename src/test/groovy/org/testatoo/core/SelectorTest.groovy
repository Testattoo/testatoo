/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Button
import org.testatoo.bundle.html5.input.InputTypeText
import org.testatoo.core.component.Component

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.component.ComponentFactory.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SelectorTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        visit 'http://localhost:8080/selectors.html'
    }

    @Test
    public void should_use_$_as_a_single_selector() {
        Button button = $('#button') as Button

        assert button.enabled()
        assert button.visible()
    }

    @Test
    public void should_throw_an_error_if_single_selector_find_many_components() {
        try {
            Button button = $('[type="text"]') as Button
            button.should { be enabled }
        } catch (ComponentException e) {
            assert e.message == "Component defined by expression \$('[type=\"text\"]') is not unique: got 4"
        }
    }

    @Test
    public void should_throw_an_error_if_single_selector_return_not_expected_component_type() {
        try {
            Button button = $('[type="text"]:first') as Button
            button.should { be enabled }
        } catch (ComponentException e) {
            assert e.message.contains('Expected a Button')
            assert e.message.contains('but was: InputTypeText')
        }
    }

    @Test
    public void should_have_at_least_an_Identifier_available_on_Component() {
        // 1 - A component without identifier cannot be used
        try {
            InvalidComponent cmp = $('#button') as InvalidComponent
            cmp.should { be visible }
        } catch (ComponentException e) {
            assert e.message == 'Missing @Identifier annotation on type org.testatoo.core.SelectorTest$InvalidComponent'
        }

        // 2 - If component don't have Identifier on it the Identifier on the superclass is used
        BigButton cmp = $('#button') as BigButton
        cmp.should { be visible }
        assert  cmp.big
    }

    @Test
    public void should_use_$$_as_a_multi_selector() {
        List<Button> buttons = $$('.btn', Button)
        assert buttons.size() == 4

        List<InputTypeText> textFields = $$('[type="text"]', InputTypeText)
        assert textFields.size() == 4

        textFields.each {
            assert it.enabled()
            assert it.visible()
            assert it.empty()
        }

        textFields.each { it ->
            fill it with 'TESTATOO!'
        }

        textFields.each {
            assert !it.empty()
            assert it.value() == 'TESTATOO!'
        }
    }

    @Test
    public void should_find_button_by_text() {
        button 'Button' should { have text('Button') }
        button 'Submit' should { have text('Submit') }
        button 'Reset' should { have text('Reset') }
        button 'My Button Text' should { have text('My Button Text') }
    }

    @Test
    public void should_find_radio_by_label() {
        radio 'My Radio' should { have label('My Radio') }
    }

    @Test
    public void should_find_checkbox_by_label() {
        checkbox 'Check me' should { have label('Check me') }
    }

    @Test
    public void should_find_fields_by_label_or_placeholder() {
        textField 'Text' should { have label('Text') }
        textField 'Placeholder' should { have placeholder('Placeholder') }

        passwordField 'Password:' should { have label('Password:') }
        passwordField 'Password' should { have placeholder('Password') }

        searchField 'Search' should { have label('Search') }
        searchField 'Search...' should { have placeholder('Search...') }

        emailField 'Email:' should { have label('Email:') }
        emailField 'my@email.org' should { have placeholder('my@email.org') }

        urlField 'URL' should { have label('URL') }
        urlField 'www.mysite.org' should { have placeholder('www.mysite.org') }

        numberField 'Number' should { have label('Number') }

        rangeField 'Range' should { have label('Range') }

        colorField 'Color' should { have label('Color') }

        dateField 'Date' should { have label('Date') }
        dateField 'yyyy/mm/dd' should { have placeholder('yyyy/mm/dd') }

        dateTimeField 'DateTime:' should { have label('DateTime:') }
        dateTimeField 'DateTime' should { have placeholder('DateTime') }

        monthField 'Month:' should { have label('Month:') }
        monthField 'Month' should { have placeholder('Month') }

        phoneField 'Phone' should { have label('Phone') }
        phoneField '+1 514 123 4567' should { have placeholder('+1 514 123 4567') }

        timeField 'Time:' should { have label('Time:') }
        timeField 'Time' should { have placeholder('Time') }

        weekField 'Week:' should { have label('Week:') }
        weekField 'Week' should { have placeholder('Week') }
    }

    @Test
    public void should_find_link_by_text() {
    }

    @Test
    public void should_find_dropdown_by_label() {
        dropdown 'Elements' should {
            have label('Elements')
            have items('Helium', 'Boron', 'Polonium', 'Calcium', 'Radium')
        }
    }

    @Test
    public void should_find_listbox_by_label() {
        listBox 'Cities' should {
            have label('Cities')
            have items('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich')
        }
    }

    @Test
    public void should_find_item_by_value() {
        item 'Montreal' should {
            have value('Montreal')
        }
    }

    @Test
    public void should_find_group_by_value() {
        group 'Cat-2' should {
            have value('Cat-2')
            have items('Jupiter', 'Saturn', 'Uranus', 'Neptune')
        }
    }

    @Test
    public void should_find_heading_by_text() {
        heading 'My heading' should {
            have text('My heading')
        }
    }

    @Test
    public void should_find_panel_by_title() {
        panel 'My Panel title' should {
            have title('My Panel title')
        }
    }

    private class InvalidComponent extends Component {}

    private class BigButton extends Button {
        public boolean isBig() { true }
    }
}
