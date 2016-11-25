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

import org.junit.ClassRule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.category.UserAgent
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.A
import org.testatoo.bundle.html5.Form

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.*

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
        Browser.open 'http://localhost:8080/components.html'

        assert Browser.title == 'Testatoo Rocks'
        assert Browser.pageSource.contains('<title>Testatoo Rocks</title>')
        assert Browser.url == 'http://localhost:8080/components.html'

        Browser.open('http://localhost:8080/keyboard.html')
        assert Browser.url == 'http://localhost:8080/keyboard.html'
    }

    @Test
    void should_be_able_to_navigate() {
        Browser.open 'http://localhost:8080/components.html'

        assert Browser.url == 'http://localhost:8080/components.html'

        Browser.navigateTo('http://localhost:8080/keyboard.html')
        assert Browser.url == 'http://localhost:8080/keyboard.html'

        Browser.back()
        assert Browser.url == 'http://localhost:8080/components.html'

        Browser.forward()
        assert Browser.url == 'http://localhost:8080/keyboard.html'

        Browser.refresh()
        assert Browser.url == 'http://localhost:8080/keyboard.html'
    }

    @Test
    void should_manage_windows() {
        Browser.open 'http://localhost:8080/components.html'

        assert Browser.windows.size() == 1
        String main_window_id = Browser.windows[0].id

        A link = $('#link') as A
        Form form = $('#dsl-form') as Form

        assert Browser.windows.size() == 1
        assert link.available()
        assert !form.available()

        clickOn link

        assert Browser.windows.size() == 2
        Browser.switchTo(Browser.windows[1])
        assert form.available()

        Browser.windows[1].close()
        assert Browser.windows.size() == 1
        assert Browser.windows[0].id == main_window_id
        assert Browser.windows[0].toString() == main_window_id
    }
}