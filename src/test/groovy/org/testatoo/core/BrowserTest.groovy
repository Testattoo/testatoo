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
package org.testatoo.core

import org.junit.ClassRule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.category.UserAgent
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.A
import org.testatoo.bundle.html5.Form
import org.testatoo.core.internal.Wait

import static org.testatoo.WebDriverConfig.BASE_URL
import static org.testatoo.core.Browser.*
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.*
import static org.testatoo.core.internal.Wait.waitUntil

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
@Category(UserAgent.All)
class BrowserTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @Test
    void should_be_able_to_have_browser_properties_access() {
        open BASE_URL + 'components.html'

        assert title == 'Testatoo Rocks'
        assert pageSource.contains('<title>Testatoo Rocks</title>')
        assert url == BASE_URL + 'components.html'

        open(BASE_URL + 'keyboard.html')
        assert url == BASE_URL + 'keyboard.html'
    }

    @Test
    void should_be_able_to_navigate() {
        open BASE_URL + 'components.html'

        assert url == BASE_URL + 'components.html'

        navigateTo(BASE_URL + 'keyboard.html')
        assert url == BASE_URL + 'keyboard.html'

        back()
        assert url == BASE_URL + 'components.html'

        forward()
        assert url == BASE_URL + 'keyboard.html'

        refresh()
        assert url == BASE_URL + 'keyboard.html'
    }

    @Test
    void should_manage_windows() {
        open BASE_URL + 'components.html'
        A link = $('#link') as A
        Form form = $('#dsl-form') as Form

        assert windows.size() == 1
        assert link.available()
        assert !form.available()

        String main_window_id = windows[0].id

        clickOn link

        waitUntil({ windows.size() == 2 })
        switchTo(windows[1])
        assert form.available()

        windows[1].close()
        waitUntil({ windows.size() == 1 })
        assert windows[0].id == main_window_id
        assert windows[0].toString() == main_window_id
    }
}