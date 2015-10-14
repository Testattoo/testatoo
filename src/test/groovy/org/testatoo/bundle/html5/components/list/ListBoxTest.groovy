/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
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
package org.testatoo.bundle.html5.components.list

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ListBoxTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        ListBox cities = $('#cities') as ListBox

        assert cities.items.size() == 6
        assert cities.visibleItems.size() == 3
        assert cities.multiSelectable

        assert cities.item('Quebec').value == 'Quebec'

        ListBox planets = $('#planets') as ListBox

        assert planets.groupItems.size() == 2
        assert planets.groupItems[0].value == 'Cat-1'
        assert planets.groupItem('Cat-1').value == 'Cat-1'
    }

    @Test
    public void groupItem_should_have_expected_behaviours() {
        ListBox planets = $('#planets') as ListBox
        GroupItem groupItem = planets.groupItem('Cat-1')

        assert groupItem.items.size() == 4
        assert groupItem.items[0].value == 'Mercury'
        assert groupItem.item('Earth').value == 'Earth'
    }
}
