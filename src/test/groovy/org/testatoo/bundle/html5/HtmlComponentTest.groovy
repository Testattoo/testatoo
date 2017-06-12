/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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

import static org.testatoo.WebDriverConfig.BASE_URL
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class HtmlComponentTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @Test
    void should_implement_default_states() {
        visit BASE_URL + 'components.html'

        Button button = $('#button') as Button
        assert button.available()
        assert button.enabled()
        assert button.visible()

        button = $('#submit') as Button
        assert !button.enabled()

        Panel panel = $('#hidden_panel') as Div
        assert !panel.visible()

        panel = $("#none_existing_id") as Div
        assert !panel.available()
    }

    @Test
    void should_find_child_elements_by_css() {
        // TODO David
    }

    @Test
    void should_find_child_elements_by_js() {
        // TODO David
    }
}