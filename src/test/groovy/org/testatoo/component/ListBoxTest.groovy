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
import org.testatoo.core.component.ComponentException
import org.testatoo.core.component.list.GroupItem
import org.testatoo.core.component.list.ListBox
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*

/**
 * Created by david on 29/01/15.
 */
@RunWith(JUnit4)
class ListBoxTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void should_have_expected_behaviours() {
        ListBox listBox = $('#cities') as ListBox

        listBox.should { have 6.items }
        listBox.should { have items('Montreal', 'Quebec', 'Montpellier', 'New York', 'Casablanca', 'Munich') }

        listBox.should { have selectedItems('New York', 'Munich') }

        listBox.should { have 3.visibleItems }
        listBox.should { be multiSelectable }

        listBox.items[0].should { be enabled }
        listBox.items[1].should { be disabled }

        on listBox unselect 'New York'
        on listBox unselect 'Munich'

        on listBox select 'Montreal'
        on listBox select 'Montpellier'

        try {
            on listBox select 'Quebec'
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Item Quebec is disabled and cannot be selected'
        }

        listBox.should { have selectedItems('Montreal', 'Montpellier') }

        listBox = $('#planets') as ListBox
        listBox.should { be singleSelectable }

        listBox.should { have groupItems('Cat-1', 'Cat-2') }

        GroupItem group = listBox.groupItems[0]
        group.should { have label('Cat-1') }
        group.should { have 4.items }

        assert group.items.size == 4

        group.should { have items('Mercury', 'Venus', 'Earth', 'Mars') }
        group.items[0].should { have value('Mercury') }
    }
}
