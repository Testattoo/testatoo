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
package org.testatoo.component

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.component.list.Dropdown
import org.testatoo.core.component.list.GroupItem
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.Mouse.click_on
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class DropdownTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    // http://en.wikipedia.org/wiki/Drop-down_list
    @Test
    public void should_have_expected_behaviours() {
        Dropdown dropdown = $('#elements') as Dropdown

        dropdown.should { be enabled }
        dropdown.should { be visible }

        dropdown.should { have label('Elements list') }
        dropdown.should { have size(5) }
        dropdown.should { have items.equalsTo('Helium', 'Boron', 'Polonium', 'Calcium', 'Radium') }
        dropdown.should { have items('Helium', 'Boron', 'Polonium', 'Calcium', 'Radium') }

        dropdown.should { have items.containing('Polonium', 'Calcium') }

        dropdown.should { have selectedItems('Helium') }
        dropdown.items[2].should { be unselected }

        click_on dropdown.items[2]

        dropdown.should { have selectedItems('Polonium') }
        dropdown.items[2].should { be selected }

        assert dropdown.items.size == 5
        dropdown.should { have 5.items }

        dropdown.items[0].should { have label('H') }
        dropdown.items[1].should { have label('B') }
        dropdown.items[2].should { have label('Pol') }
        dropdown.items[3].should { have label('Ca') }
        dropdown.items[4].should { have label('Ra') }

        click_on dropdown.items[4]

        dropdown.should { have selectedItems('Radium') }
        dropdown.items[4].should { be selected }

        dropdown = $('#countries') as Dropdown
        dropdown.should { be disabled }
        dropdown.should { have items('Canada', 'France', 'Spain') }
        dropdown.items[0].should { be disabled }

        dropdown = $('#os') as Dropdown
        dropdown.should { have 8.items }
        dropdown.should { have items('None', 'Ubuntu', 'Fedora', 'Gentoo', 'XP', 'Vista', 'FreeBSD', 'OpenBSD') }

        dropdown.should { have 3.groupItems }
        dropdown.should { have groupItems('linux', 'win32', 'BSD') }
        dropdown.should { have groupItems.containing('linux') }

        GroupItem group = dropdown.groupItems[0]
        group.should { have label('linux') }
        group.should { have items('Ubuntu', 'Fedora', 'Gentoo') }

        group = dropdown.groupItems[1]
        group.should { have label('win32') }
        group.should { have items('XP', 'Vista') }

        group = dropdown.groupItems[2]
        group.should { have label('BSD') }
        group.should { have 2.items }

        assert group.items.size == 2

        group.should { have items('FreeBSD', 'OpenBSD') }
    }
}
