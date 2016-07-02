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
package org.testatoo.bundle.html5.components

import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.WebDriverConfig
import org.testatoo.bundle.html5.*
import org.testatoo.bundle.html5.heading.*
import org.testatoo.bundle.html5.input.InputTypeEmail
import org.testatoo.bundle.html5.input.InputTypePassword
import org.testatoo.category.UserAgent
import org.testatoo.core.CssIdentifier
import org.testatoo.core.ComponentException
import org.testatoo.core.component.*
import org.testatoo.core.support.property.TextSupport

import static org.junit.Assert.fail
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
@Category(UserAgent.All)
class ComponentsTest {
    @ClassRule
    public static WebDriverConfig driver = new WebDriverConfig()

    @BeforeClass
    public static void before() {
        visit 'http://localhost:8080/components.html'
    }

    @Test
    public void component_should_have_expected_common_behaviours() {
        assert Button in Button

        Button button = $('#button') as Button

        assert button.enabled()
        assert button.available()
        assert button.visible()

        button = $('#submit') as Button
        assert !button.enabled()

        Div panel = $('#hidden_panel') as Div
        assert !panel.visible()

        panel = $('#non_existing_id') as Div
        assert !panel.available()
    }

    @Test
    public void article_should_have_expected_behaviours() {
        assert Article in Component

        Article article = $('#article') as Article

        assert article.paragraphs.size() == 2
    }

    @Test
    public void aside_should_have_expected_behaviours() {
        assert Aside in Component

        Aside aside = $('#aside') as Aside

        assert aside.visible()
    }

    @Test
    public void button_should_have_expected_behaviours() {
        assert Button in Button

        // fields type=button
        Button button = $('#button') as Button

        assert Button in TextSupport
        // But override text
        assert button.text() == 'Button'

        // fields type=submit
        button = $('#submit') as Button
        assert button.text() == 'Submit'

        // fields type=reset
        button = $('#reset') as Button
        assert button.text() == 'Reset'

        // button element
        button = $('#btn') as Button
        assert button.text() == 'My Button Text'
    }

    @Test
    public void checkbox_should_have_expected_behaviours() {
        assert CheckBox in CheckBox

        CheckBox checkBox = $('#checkbox') as CheckBox
        assert checkBox.label() == 'Check me out'
        assert !checkBox.checked()
        checkBox.check()
        assert checkBox.checked()
        checkBox.uncheck()
        assert !checkBox.checked()
        checkBox.click()
        assert checkBox.checked()

        try {
            checkBox.check()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'CheckBox CheckBox:checkbox is already checked and cannot be checked'
        }

        try {
            checkBox.uncheck()
            checkBox.uncheck()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'CheckBox CheckBox:checkbox is already unchecked and cannot be unchecked'
        }

        checkBox = $('#disabled_checkbox') as CheckBox
        try {
            checkBox.check()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'CheckBox CheckBox:disabled_checkbox is disabled and cannot be checked'
        }

        try {
            checkBox.uncheck()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'CheckBox CheckBox:disabled_checkbox is disabled and cannot be unchecked'
        }
    }

    @Test
    public void footer_should_have_expected_behaviours() {
        assert Footer in Component

        Footer footer = $('#footer') as Footer

        assert footer.visible()
    }

    @Test
    public void form_should_have_expected_behaviours() {
        assert Form in Form

        Form form = $('#form') as Form
        InputTypeEmail email = $('#form [type=email]') as InputTypeEmail
        InputTypePassword password = $('#form [type=password]') as InputTypePassword
        Message message = $('#form .alert') as Message

        assert form.visible()
        // Cause password mandatory
        assert !form.valid()

        email.value('joe.blow@email.org')
        password.value('password666')
        assert email.value() == 'joe.blow@email.org'
        assert password.value() == 'password666'

        form.reset()

        assert email.value() == ''
        assert password.value() == ''

        assert message.title() == 'The form was submitted 0 time(s)'
        // Set the required password field before submitting
        password.value('password666')
        form.submit()
        assert message.title() == 'The form was submitted 1 time(s)'
    }

    @Test
    public void header_should_have_expected_behaviours() {
        assert Header in Component

        Header header = $('#header') as Header

        assert header.visible()
    }

    @Test
    public void heading_should_have_expected_behaviours() {
        assert H1 in Heading
        assert H2 in Heading
        assert H3 in Heading
        assert H4 in Heading
        assert H5 in Heading
        assert H6 in Heading

        H1 h1 = $('#h1') as H1
        assert h1.text() == 'heading 1'

        H2 h2 = $('#h2') as H2
        assert h2.text() == 'heading 2'

        H3 h3 = $('#h3') as H3
        assert h3.text() == 'heading 3'

        H4 h4 = $('#h4') as H4
        assert h4.text() == 'heading 4'

        H5 h5 = $('#h5') as H5
        assert h5.text() == 'heading 5'

        H6 h6 = $('#h6') as H6
        assert h6.text() == 'heading 6'
    }

    @Test
    public void image_should_have_expected_behaviours() {
        assert Img in Image

        Img image = $('#image') as Img

        assert image.reference() == 'http://localhost:8080/img/Montpellier.jpg'
    }

    @Test
    public void link_should_have_expected_behaviours() {
        assert A in Link

        A link = $('#link') as A

        assert link.text() == 'Link to dsl page'
        assert link.reference() == 'http://localhost:8080/dsl.html'
    }

    @Test
    public void panel_should_have_expected_behaviours() {
        assert Div in Panel

        Div panel = $('#panel') as Div

        assert panel.title() == ''
    }

    @Test
    public void paragraph_should_have_expected_behaviours() {
        assert Paragraph in Component
        assert Paragraph in TextSupport

        Paragraph paragraph = $('#p_1') as Paragraph

        assert paragraph.text() == 'Paragraph 1'
    }

    @Test
    public void radio_should_have_expected_behaviours() {
        assert Radio in Radio

        Radio radio = $('#radio_1') as Radio
        assert radio.label() == 'Radio checked'
        assert radio.checked()

        radio = $('#radio_2') as Radio
        assert radio.label() == 'Radio unchecked'
        assert !radio.checked()
        radio.check()
        assert radio.checked()
    }

    @Test
    public void section_should_have_expected_behaviours() {
        assert Section in Component

        Section section = $('#section') as Section

        assert section.paragraphs().size() == 1
        assert section.articles().size() == 1
    }

    @Test
    public void span_should_have_expected_behaviours() {
        assert Span in Component

        Span span = $('#span') as Span

        assert span.text() == 'A span'
    }

    @CssIdentifier('div')
    class Message extends Panel {
        @Override
        String title() {
            config.evaluator.eval(id(), "it.text()")
        }
    }
}
