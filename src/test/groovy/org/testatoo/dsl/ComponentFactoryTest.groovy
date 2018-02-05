package org.testatoo.dsl

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.ComponentException
import org.testatoo.core.Evaluator
import org.testatoo.core.MetaDataProvider
import org.testatoo.core.MetaInfo
import org.testatoo.core.component.*
import org.testatoo.core.component.field.*

import static org.junit.Assert.fail
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentFactoryTest {
    private static MetaDataProvider meta
    private static MetaInfo metaInfo = new MetaInfo(id: 'id', node: 'node')

    @BeforeClass
    static void before() {
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(metaInfo)
        config.evaluator = mock(Evaluator)
    }

    @Test
    void should_have_generic_components_factory() {
        config.evaluator = mock(Evaluator)

        when(config.evaluator.metaInfo("\$('ButtonStub')")).thenReturn([metaInfo])
        Button button = button 'Ok'
        button.should { have text('Ok') }

        when(config.evaluator.metaInfo("\$('RadioStub')")).thenReturn([metaInfo])
        Radio radio = radio 'Radio Label'
        radio.should { have label('Radio Label') }

        when(config.evaluator.metaInfo("\$('CheckBoxStub')")).thenReturn([metaInfo])
        CheckBox checkBox = checkbox 'Checkbox Label'
        checkBox.should { have label('Checkbox Label') }

        when(config.evaluator.metaInfo("\$('DropDownStub')")).thenReturn([metaInfo])
        Dropdown dropdown = dropdown 'DropDown Label'
        dropdown.should { have label('DropDown Label') }

        when(config.evaluator.metaInfo("\$('ListBoxStub')")).thenReturn([metaInfo])
        ListBox listBox = listBox 'ListBox Label'
        listBox.should { have label('ListBox Label') }

        when(config.evaluator.metaInfo("\$('GroupStub')")).thenReturn([metaInfo])
        Group group = group 'Group Value'
        group.should { have value('Group Value') }

        when(config.evaluator.metaInfo("\$('ItemStub')")).thenReturn([metaInfo])
        Item item = item 'Item Value'
        item.should { have value('Item Value') }

        when(config.evaluator.metaInfo("\$('HeadingStub')")).thenReturn([metaInfo])
        Heading heading = heading 'Heading Text'
        heading.should { have text('Heading Text') }

        when(config.evaluator.metaInfo("\$('PanelStub')")).thenReturn([metaInfo])
        Panel panel = panel 'Panel Title'
        panel.should { have title('Panel Title') }

        when(config.evaluator.metaInfo("\$('LinkStub')")).thenReturn([metaInfo])
        Link link = link 'Link Text'
        link.should { have text('Link Text') }

        when(config.evaluator.metaInfo("\$('PasswordFieldStub')")).thenReturn([metaInfo])
        PasswordField password = passwordField 'Password Field Label'
        password.should { have label('Password Field Label') }

        password = passwordField 'Password Field Placeholder'
        password.should { have placeholder('Password Field Placeholder') }

        when(config.evaluator.metaInfo("\$('TextFieldStub')")).thenReturn([metaInfo])
        TextField text = textField 'Text Field Label'
        text.should { have label('Text Field Label') }

        text = textField 'Text Field Placeholder'
        text.should { have placeholder('Text Field Placeholder') }

        when(config.evaluator.metaInfo("\$('SearchFieldStub')")).thenReturn([metaInfo])
        SearchField search = searchField 'Search Field Label'
        search.should { have label('Search Field Label') }

        search = searchField 'Search Field Placeholder'
        search.should { have placeholder('Search Field Placeholder') }

        when(config.evaluator.metaInfo("\$('EmailFieldStub')")).thenReturn([metaInfo])
        EmailField email = emailField 'Email Field Label'
        email.should { have label('Email Field Label') }

        email = emailField 'Email Field Placeholder'
        email.should { have placeholder('Email Field Placeholder') }

        when(config.evaluator.metaInfo("\$('URLFieldStub')")).thenReturn([metaInfo])
        URLField url = urlField 'URL Field Label'
        url.should { have label('URL Field Label') }

        url = urlField 'URL Field Placeholder'
        url.should { have placeholder('URL Field Placeholder') }

        when(config.evaluator.metaInfo("\$('NumberFieldStub')")).thenReturn([metaInfo])
        NumberField number = numberField 'Number Field Label'
        number.should { have label('Number Field Label') }

        number = numberField 'Number Field Placeholder'
        number.should { have placeholder('Number Field Placeholder') }

        when(config.evaluator.metaInfo("\$('RangeFieldStub')")).thenReturn([metaInfo])
        RangeField range = rangeField 'Range Field Label'
        range.should { have label('Range Field Label') }

        range = rangeField 'Range Field Placeholder'
        range.should { have placeholder('Range Field Placeholder') }

        when(config.evaluator.metaInfo("\$('DateFieldStub')")).thenReturn([metaInfo])
        DateField date = dateField 'Date Field Label'
        date.should { have label('Date Field Label') }

        date = dateField 'Date Field Placeholder'
        date.should { have placeholder('Date Field Placeholder') }

        when(config.evaluator.metaInfo("\$('ColorFieldStub')")).thenReturn([metaInfo])
        ColorField color = colorField 'Color Field Label'
        color.should { have label('Color Field Label') }

        color = colorField 'Color Field Placeholder'
        color.should { have placeholder('Color Field Placeholder') }

        when(config.evaluator.metaInfo("\$('DateTimeFieldStub')")).thenReturn([metaInfo])
        DateTimeField dateTime = dateTimeField 'DateTime Field Label'
        dateTime.should { have label('DateTime Field Label') }

        dateTime = dateTimeField 'DateTime Field Placeholder'
        dateTime.should { have placeholder('DateTime Field Placeholder') }

        when(config.evaluator.metaInfo("\$('MonthFieldStub')")).thenReturn([metaInfo])
        MonthField month = monthField 'Month Field Label'
        month.should { have label('Month Field Label') }

        month = monthField 'Month Field Placeholder'
        month.should { have placeholder('Month Field Placeholder') }

        when(config.evaluator.metaInfo("\$('PhoneFieldStub')")).thenReturn([metaInfo])
        PhoneField phone = phoneField 'Phone Field Label'
        phone.should { have label('Phone Field Label') }

        phone = phoneField 'Phone Field Placeholder'
        phone.should { have placeholder('Phone Field Placeholder') }

        when(config.evaluator.metaInfo("\$('TimeFieldStub')")).thenReturn([metaInfo])
        TimeField time = timeField 'Time Field Label'
        time.should { have label('Time Field Label') }

        time = timeField 'Time Field Placeholder'
        time.should { have placeholder('Time Field Placeholder') }

        when(config.evaluator.metaInfo("\$('WeekFieldStub')")).thenReturn([metaInfo])
        WeekField week = weekField 'Week Field Label'
        week.should { have label('Week Field Label') }

        week = weekField 'Week Field Placeholder'
        week.should { have placeholder('Week Field Placeholder') }
    }

    @Test
    void should_throw_an_error_when_component_not_found() {
        config.evaluator = mock(Evaluator)

        when(config.evaluator.metaInfo("\$('EmailFieldStub')")).thenReturn([metaInfo])
        try {
            emailField 'Email Field Invalid Label'
            fail()
        } catch (ComponentException e) {
            assert e.message == "Unable to find class org.testatoo.core.component.field.EmailField with label or placeholder equals to 'Email Field Invalid Label'"
        }
    }
}
