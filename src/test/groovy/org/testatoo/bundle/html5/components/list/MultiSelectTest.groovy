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
import org.testatoo.bundle.html5.list.MultiSelect
import org.testatoo.core.ComponentException
import org.testatoo.core.component.Item
import org.testatoo.core.component.ListBox

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class MultiSelectTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Test
    public void should_have_expected_behaviours() {
        assert MultiSelect in ListBox

        ListBox empty_multi_select = $('#empty_multi_select') as MultiSelect
        empty_multi_select.should { be empty }

        ListBox cities = $('#cities') as MultiSelect

        assert cities.label() == 'Cities list'
        assert cities.items().size() == 6
        assert cities.visibleItems().size() == 3

        assert cities

        Item montreal = cities.item('Montreal')
        Item quebec = cities.item('Quebec')
        Item montpellier = cities.item('Montpellier')
        Item newYork = cities.item('New York')
        Item casablanca = cities.item('Casablanca')
        Item munich = cities.item('Munich')

        assert montreal.selected()
        assert montpellier.enabled()
        assert cities.item('Montreal').selected()

        assert !quebec.selected()
        assert !quebec.enabled()
        assert !cities.item('Quebec').selected()

        assert !montpellier.selected()
        assert !cities.item('Montpellier').selected()

        assert !newYork.selected()
        assert !cities.item('New York').selected()

        assert !casablanca.selected()
        assert !cities.item('Casablanca').selected()

        assert !munich.selected()
        assert !cities.item('Munich').selected()

        assert cities.selectedItems().containsAll(montreal)

        cities.unselect(montreal)
        cities.select(newYork, munich)

        assert cities.selectedItems().containsAll(newYork, munich)

        cities.select('Montpellier', 'Montreal')
        assert cities.item('Montpellier').selected()
        assert cities.item('Montreal').selected()
        assert cities.selectedItems().containsAll(newYork, munich, montpellier, montreal)

        montreal.unselect()
        montpellier.unselect()

        assert !cities.item('Montreal').selected()
        assert !cities.item('Montpellier').selected()
        assert cities.item('New York').selected()
        assert cities.item('Munich').selected()

        cities.select(montreal, montpellier)
        assert cities.item('Montreal').selected()
        assert cities.item('Montpellier').selected()
        assert cities.item('New York').selected()
        assert cities.item('Munich').selected()

        montpellier.click() // Now just Montpellier is selected
        assert montpellier.selected()
        assert !montreal.selected()
        assert !newYork.selected()
        assert !munich.selected()

        try {
            quebec.select()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Option Quebec is disabled and cannot be selected'
        }

        try {
            quebec.unselect()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Option Quebec is disabled and cannot be unselected'
        }

        try {
            newYork.unselect()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Option New York is already unselected and cannot be unselected'
        }

        try {
            montpellier.select()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Option Montpellier is already selected and cannot be selected'
        }

        MultiSelect planets = $('#planets') as MultiSelect
        assert planets.visibleItems().size() == 5
        assert planets.groups().size() == 2
        assert planets.groups()[0].value() == 'Cat-1'
        assert planets.group('Cat-1').value() == 'Cat-1'

        Item venus = planets.item('Venus')
        Item saturn = planets.item('Saturn')

        assert planets.selectedItems().size() == 0
        planets.select('Venus', 'Saturn')

        assert planets.selectedItems().size() == 2
        assert planets.selectedItems().containsAll(venus, saturn)

        planets.unselect('Venus', 'Saturn')
        assert planets.selectedItems().size() == 0
    }
}
