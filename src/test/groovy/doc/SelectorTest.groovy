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
package doc

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.Button

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SelectorTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        browser.open 'http://localhost:8080/selectors.html'
    }

    @Test
    public void should_select_unique_component_by_css_selector() {
        // tag::componentSelection[]
        $('css selector');

        // Samples
        Button button = $('#button') as Button              // <1>
        Button reset = $('.btn-secondary') as Button        // <2>
        Button submit = $('input:eq(1)') as Button          // <3>
        Button myButton = $('[data-role=myRole]') as Button // <4>

        // end::componentSelection[]

        assert button.text() == 'Button'
        assert reset.text() == 'Reset'
        assert submit.text() == 'Submit'
        assert myButton.text() == 'My Button Text'
    }

    @Test
    public void should_select_many_components_by_css_selector() {
        // tag::componentsSelection[]

        // end::componentsSelection[]
    }

}
