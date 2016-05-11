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
package doc.spec

import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.core.component.Radio

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SpecTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    Radio male_radio
    Radio female_radio

    @Before
    public void before() {
        browser.open 'http://localhost:8080/spec.html'

        male_radio = $('[name=gender]:first') as org.testatoo.bundle.html5.Radio
        female_radio = $('[value=female]:last') as org.testatoo.bundle.html5.Radio
    }

    @Test
    public void should_select_gender() {
        // tag::specMethod[]
        male_radio.should {
            be unchecked
            have label('Male')
        }

        female_radio.should {
            be unchecked
            have label('Female')
        }

        check male_radio
        male_radio.should { be checked }
        female_radio.should { be unchecked }

        check female_radio
        male_radio.should { be unchecked }
        female_radio.should { be checked }
        // end::specMethod[]
    }
}
