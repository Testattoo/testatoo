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
package org.testatoo.bundle.html5

import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.input.InputTypeEmail
import org.testatoo.bundle.html5.input.InputTypePassword
import org.testatoo.core.ComponentException
import org.testatoo.core.component.Panel
import org.testatoo.core.component.field.EmailField
import org.testatoo.core.component.field.PasswordField

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.getBrowser

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class HtmlComponentTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @Test
    public void should_implement_default_states() {
        browser.open 'http://localhost:8080/components.html'

        Button button = $('#button') as Button
        assert button.available()
        assert button.enabled()
        assert button.visible()

        button = $('#submit') as Button
        assert button.disabled()

        Panel panel = $('#hidden_panel') as Div
        assert panel.hidden()

        panel = $("#none_existing_id") as Div
        assert panel.missing()
    }

    @Test
    public void should_find_child_elements_by_css() {
        // TODO Roch
    }

    @Test
    public void should_find_child_elements_by_js() {
        // TODO Roch
    }

    @Test
    public void should_be_able_to_test_if_component_contains_other_component() {
        browser.open 'http://localhost:8080/container.html'

        Div panel = $('#panel') as Div
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        panel.contains(visible_button, invisible_button)

        Form form = $('#form') as Form
        EmailField email_field = $('[type=email]') as InputTypeEmail
        PasswordField password_field = $('[type=password]') as InputTypePassword
        Button submit_button = $('[type=submit]') as Button
        Button reset_button = $('[type=reset]') as Button

        form.contains(
                email_field,
                password_field,
                submit_button,
                reset_button
        )

        try {
            panel.contains(
                    submit_button,
                    reset_button
            )
        } catch (ComponentException e) {
            assert e.message == "Component Div:panel does not contain expected component(s): [Button:${submit_button.id()}, Button:${reset_button.id()}]" as String
        }
    }

    @Test
    public void should_be_able_to_test_if_component_display_other_component() {
        browser.open 'http://localhost:8080/container.html'

        Div panel = $('#panel') as Div
        Button visible_button = $('#visible_button') as Button
        Button invisible_button = $('#invisible_button') as Button

        panel.displays(visible_button)

        try {
            panel.displays(
                    visible_button,
                    invisible_button
            )
        } catch (ComponentException e) {
            assert e.message == "Component Button with id invisible_button expected visible but was hidden"
        }

        EmailField email_field = $('[type=email]') as InputTypeEmail
        try {
            panel.displays(email_field)
        } catch (ComponentException e) {
            assert e.message == "Component Div:panel does not display expected component(s): [InputTypeEmail:${email_field.id()}]" as String
        }
    }
}
