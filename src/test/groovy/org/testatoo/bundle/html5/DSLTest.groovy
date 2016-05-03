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
import org.testatoo.core.component.Panel

import static org.testatoo.core.Actions.check
import static org.testatoo.core.Actions.visit
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DSLTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @Test
    public void should_available_for_component() {
        visit 'http://localhost:8080/components.html'

        Button button = $('#button') as Button
        button.should {
            be available
            be enabled
            be visible
        }

        button = $('#submit') as Button
        button.should { be disabled }

        Panel panel = $('#hidden_panel') as Div
        panel.should { be hidden }

        panel = $("#none_existing_id") as Div
        panel.should { be missing }
    }

    @Test
    public void should_available_for_checkable_and_uncheckable_components() {
        visit 'http://localhost:8080/components.html'

        CheckBox checkbox = $('#checkbox') as CheckBox

        checkbox.should { be unchecked }
        check checkbox
        checkbox.should { be checked }

        Radio radio = $('#radio_2') as Radio
        radio.should { be unchecked }
        check radio
        radio.should { be checked }
    }


}
