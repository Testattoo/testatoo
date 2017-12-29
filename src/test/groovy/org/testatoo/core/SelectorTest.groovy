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

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentFactory
import org.testatoo.core.internal.Identifiers

import static org.junit.Assert.fail
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.spy
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class SelectorTest {
    @Before
    void before() {
        config.evaluator = mock(Evaluator)
        config.componentTypes.clear()
    }

    @Test
    void should_use_$_as_a_single_selector() {
        when(config.evaluator.metaInfo("\$('expression')")).thenReturn([new MetaInfo(id: '1', node: 'node')])

        Component button = $('expression') as Component
        assert button.enabled()
    }

    @Test
    void should_throw_an_error_if_single_selector_find_many_components() {
        when(config.evaluator.metaInfo("\$('expression')")).thenReturn([new MetaInfo(id: '1', node: 'node'),
                                                                 new MetaInfo(id: '2', node: 'node'),
                                                                 new MetaInfo(id: '3', node: 'node')])
        try {
            Component button = $('expression') as Component
            button.should { be enabled }
            fail()
        } catch (ComponentException e) {
            assert e.message == "Component defined by expression \$('expression') is not unique: got 3"
        }
    }

    @Test
    void should_throw_an_error_if_single_selector_return_not_expected_component_type() {
        config.componentTypes.add(CustomElement_1)
        config.componentTypes.add(CustomElement_2)

        when(config.evaluator.metaInfo("\$('expression')")).thenReturn([new MetaInfo(id: '1', node: 'node')])
        when(config.evaluator.check('1', "it.is('custom_1')")).thenReturn(false)
        when(config.evaluator.check('1', Identifiers.identifyingExpression(CustomElement_2))).thenReturn(true)

        try {
            CustomElement_1 element = $('expression') as CustomElement_1
            element.should { be enabled }
            fail()
        } catch (ComponentException e) {
            assert e.message.contains("Expected a CustomElement_1 for component with id '1', but was: CustomElement_2")
        }
    }

    @Test
    void should_have_at_least_an_Identifier_available_on_Component() {
        config.componentTypes.add(InvalidComponent)
        when(config.evaluator.metaInfo("\$('expression')")).thenReturn([new MetaInfo(id: '1', node: 'node')])

        // 1 - A component without identifier cannot be used
        try {
            InvalidComponent cmp = $('expression') as InvalidComponent
            cmp.should { be visible }
        } catch (ComponentException e) {
            assert e.message == 'Missing @Identifier annotation on type org.testatoo.core.SelectorTest$InvalidComponent'
        }

        config.componentTypes.add(BigButton)
        when(config.evaluator.metaInfo("\$('button')")).thenReturn([new MetaInfo(id: '2', node: 'BigButton')])
        when(config.evaluator.check('2', Identifiers.identifyingExpression(BigButton))).thenReturn(true)

        // 2 - If component don't have Identifier on it the Identifier on the superclass is used
        BigButton cmp = $('button') as BigButton
        cmp.should { be visible }
        assert cmp.big
    }

    @Test
    void should_use_$$_as_a_multi_selector() {
        when(config.evaluator.metaInfo("\$('.btn')")).thenReturn([new MetaInfo(id: '1', node: 'node'),
                                                                        new MetaInfo(id: '2', node: 'node'),
                                                                        new MetaInfo(id: '3', node: 'node')])

        List<CustomElement_1> elements = $$('.btn', CustomElement_1)
        assert elements.size() == 3
    }

    @Test
    void should_delegate_to_expected_factory() {
        ComponentFactory factorySpy = spy(new ComponentFactory())
        componentFactory = factorySpy

        button 'Button'
        verify(factorySpy).button('Button')

        radio 'Radio'
        verify(factorySpy).radio('Radio')

        checkbox 'Checkbox'
        verify(factorySpy).checkbox('Checkbox')

        textField 'Text'
        verify(factorySpy).textField('Text')

        passwordField 'Password'
        verify(factorySpy).passwordField('Password')

        searchField 'Search'
        verify(factorySpy).searchField('Search')

        emailField 'Email'
        verify(factorySpy).emailField('Email')

        urlField 'URL'
        verify(factorySpy).urlField('URL')

        numberField 'Number'
        verify(factorySpy).numberField('Number')

        rangeField 'Range'
        verify(factorySpy).rangeField('Range')

        colorField 'Color'
        verify(factorySpy).colorField('Color')

        dateField 'Date'
        verify(factorySpy).dateField('Date')

        dateTimeField 'DateTime'
        verify(factorySpy).dateTimeField('DateTime')

        monthField 'Month'
        verify(factorySpy).monthField('Month')

        phoneField 'Phone'
        verify(factorySpy).phoneField('Phone')

        timeField 'Time'
        verify(factorySpy).timeField('Time')

        weekField 'Week'
        verify(factorySpy).weekField('Week')

        link 'Link'
        verify(factorySpy).link('Link')

        dropdown 'Elements'
        verify(factorySpy).dropdown('Elements')

        listBox 'List'
        verify(factorySpy).listBox('List')

        item 'Item'
        verify(factorySpy).item('Item')

        group 'Group'
        verify(factorySpy).group('Group')

        heading 'Heading'
        verify(factorySpy).heading('Heading')

        panel 'Panel'
        verify(factorySpy).panel('Panel')
    }

    private class InvalidComponent extends Component {}

    private class BigButton extends CustomElement_1 {
        boolean isBig() { true }
    }

    @CssIdentifier('custom_1')
    class CustomElement_1 extends Component {}

    @CssIdentifier('custom_2')
    class CustomElement_2 extends Component {}
}