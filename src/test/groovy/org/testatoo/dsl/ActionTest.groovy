package org.testatoo.dsl

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.bundle.stub.CheckBoxStub
import org.testatoo.bundle.stub.FormStub
import org.testatoo.bundle.stub.ItemStub
import org.testatoo.bundle.stub.ListBoxStub
import org.testatoo.bundle.stub.field.RangeFieldStub
import org.testatoo.bundle.stub.field.TextFieldStub
import org.testatoo.core.ComponentException
import org.testatoo.core.Evaluator
import org.testatoo.core.MetaDataProvider
import org.testatoo.core.MetaInfo
import org.testatoo.core.Testatoo
import org.testatoo.core.component.*
import org.testatoo.core.component.field.RangeField
import org.testatoo.core.component.field.TextField

import static org.junit.Assert.fail
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ActionTest {
    private static MetaDataProvider meta
    private static MetaInfo metaInfo = new MetaInfo(id: 'id', node: 'node')

    @BeforeClass
    static void before() {
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(metaInfo)
        config.evaluator = mock(Evaluator)
    }

    @Test
    void should_be_able_to_visit() {
        config.evaluator = mock(Evaluator)

        verify(config.evaluator, times(0)).open('http://myUrl')
        visit('http://myUrl')
        verify(config.evaluator, times(1)).open('http://myUrl')
    }

    @Test
    void should_be_able_to_type_text() {
        config.evaluator = mock(Evaluator)

        verify(config.evaluator, times(0)).type(['data'])
        type('data')
        verify(config.evaluator, times(1)).type(['data'])
    }

    @Test
    void should_be_able_to_fill() {
        TextField field = spy(new TextFieldStub())
        field.meta = meta

        fill field with 'Some value'
        verify(field, times(1)).value('Some value')
    }

    @Test
    void should_be_able_to_set() {
        RangeField range = spy(new RangeFieldStub())
        range.meta = meta

        set range to 10
        verify(range, times(1)).value(10)
    }

    @Test
    void should_have_on_as_placeholder() {
        CheckBox checkBox = spy(new CheckBoxStub())
        assert on(checkBox).is(checkBox)
    }

    @Test
    void should_be_able_to_submit_and_reset_form() {
        Form form = spy(new FormStub())
        form.meta = meta

        Testatoo.reset form // Explicit call to forbid Mockito reset method call
        verify(form, times(1)).reset()

        submit form
        verify(form, times(1)).submit()
    }

    @Test
    void should_be_able_to_select_items_in_components_containing_items() {
        ListBox listBox = spy(new ListBoxStub())
        listBox.meta = meta

        Item item_1 = spy(new ItemStub())
        item_1.meta = meta

        Item item_2 = spy(new ItemStub())
        item_2.meta = meta

        doReturn([item_1, item_2]).when(listBox).items()
        listBox.select(item_1)
        verify(item_1, times(1)).click()
        verify(item_2, times(0)).click()

        reset(item_1)
        reset(item_2)
        doReturn(true).when(item_1).selected()

        listBox.unselect(item_1)
        verify(item_1, times(1)).click()
        verify(item_2, times(0)).click()

        reset(item_1)
        reset(item_2)
        doReturn('Item_1').when(item_1).value()
        doReturn('Item_2').when(item_2).value()

        listBox.select('Item_2')
        verify(item_1, times(0)).click()
        verify(item_2, times(1)).click()

        reset(item_1)
        reset(item_2)
        doReturn('Item_1').when(item_1).value()
        doReturn('Item_2').when(item_2).value()

        listBox.unselect('Item_1', 'Item_2')
        verify(item_1, times(1)).click()
        verify(item_2, times(1)).click()
    }

    @Test
    void should_throw_an_error_when_action_on_component_does_not_correspond_to_its_state() {
        CheckBox checkbox = spy(new CheckBoxStub())
        checkbox.meta = meta

        doReturn(false).when(checkbox).enabled()
        try {
            check checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is disabled and cannot be checked')
        }

        doReturn(true).when(checkbox).enabled()
        doReturn(true).when(checkbox).checked()
        try {
            check checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is already checked and cannot be checked')
        }

        doReturn(false).when(checkbox).enabled()
        try {
            uncheck checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is disabled and cannot be unchecked')
        }

        doReturn(true).when(checkbox).enabled()
        doReturn(false).when(checkbox).checked()
        try {
            uncheck checkbox
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is already unchecked and cannot be unchecked')
        }

        ListBox listBox = spy(new ListBoxStub())
        listBox.meta = meta

        Item item_1 = spy(new ItemStub())
        item_1.meta = meta

        doReturn([item_1]).when(listBox).items()
        doReturn(false).when(item_1).enabled()

        try {
            listBox.select(item_1)
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is disabled and cannot be selected')
        }

        reset(item_1)
        doReturn(true).when(item_1).enabled()
        doReturn(true).when(item_1).selected()

        try {
            listBox.select(item_1)
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is already selected and cannot be selected')
        }

        reset(item_1)
        doReturn(false).when(item_1).enabled()
        try {
            listBox.unselect(item_1)
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is disabled and cannot be deselected')
        }

        reset(item_1)
        doReturn(true).when(item_1).enabled()
        doReturn(false).when(item_1).selected()

        try {
            listBox.unselect(item_1)
            fail()
        } catch (ComponentException e) {
            assert e.message.endsWith('is already unselected and cannot be deselected')
        }
    }
}
