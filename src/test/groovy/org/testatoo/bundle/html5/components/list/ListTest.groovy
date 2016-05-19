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
package org.testatoo.bundle.html5.components.list

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.list.Ol
import org.testatoo.bundle.html5.list.Ul
import org.testatoo.core.ComponentException
import org.testatoo.core.component.ListView

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ListTest {
    @ClassRule
    public static WebDriverConfig driver =  new WebDriverConfig()

    @BeforeClass
    public static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Test
    public void should_have_expected_behaviours() {
        assert Ul in ListView

        Ul ul = $('#unordered_list') as Ul

        assert ul.items().size() == 5
        assert ul.items()[0].value() == 'Item 1'
        assert ul.item('Item 4').value() == 'Item 4'
        assert ul.items()[3].equals(ul.items()[4])
        assert ul.items()[3].toString() == 'Item 4'

        assert Ol in ListView
        Ol ol = $('#ordered_list') as Ol

        assert ol.items().size() == 5
        assert ol.items()[0].value() == 'Item 11'
        assert ol.item('Item 44').value() == 'Item 44'

        // Html5 list items can't be selected
        try {
            ol.items()[0].selected()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            !ol.items()[0].selected()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            ol.items()[0].select()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Li Item 11 cannot be selected (Unsupported Operation)'
        }

        try {
            ol.items()[0].unselect()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Li Item 11 cannot be unselected (Unsupported Operation)'
        }
    }
}
