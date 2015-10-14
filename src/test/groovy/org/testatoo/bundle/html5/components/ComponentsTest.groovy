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
package org.testatoo.bundle.html5.components

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.traits.CheckSupport
import org.testatoo.core.traits.LabelSupport
import org.testatoo.core.traits.TextSupport
import org.testatoo.core.traits.ValiditySupport
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentsTest {

    @BeforeClass
    public static void setup() {
        config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        visit 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { config.evaluator.close() }

    @Test
    public void article_should_have_expected_behaviours() {
        Article article = $('#article') as Article

        assert article.paragraphs.size() == 2
    }

    @Test
    public void aside_should_have_expected_behaviours() {
        Aside aside = $('#aside') as Aside

        assert aside.visible
    }

    @Test
    public void button_should_have_expected_behaviours() {
        // fields type=button
        Button button = $('#button') as Button

        assert Button in TextSupport
        // But override text
        assert button.text == 'Button'

        // fields type=submit
        button = $('#submit') as Button
        assert button.text == 'Submit'

        // fields type=reset
        button = $('#reset') as Button
        assert button.text == 'Reset'

        // button element
        button = $('#btn') as Button
        assert button.text == 'My Button Text'
    }

    @Test
    public void checkbox_should_have_expected_behaviours() {
        assert Checkbox in CheckSupport
        assert Checkbox in LabelSupport
    }

    @Test
    public void footer_should_have_expected_behaviours() {
        Footer footer = $('#footer') as Footer

        assert footer.visible
    }

    @Test
    public void form_should_have_expected_behaviours() {
        assert Form in ValiditySupport

        Form form = $('#form') as Form

        assert form.visible
    }

    @Test
    public void header_should_have_expected_behaviours() {
        Header header = $('#header') as Header

        assert header.visible
    }

    @Test
    public void heading_should_have_expected_behaviours() {
        assert Heading in TextSupport

        Heading h1 = $('#h1') as Heading
        assert h1.text == 'heading 1'

        Heading h2 = $('#h2') as Heading
        assert h2.text == 'heading 2'

        Heading h3 = $('#h3') as Heading
        assert h3.text == 'heading 3'

        Heading h4 = $('#h4') as Heading
        assert h4.text == 'heading 4'

        Heading h5 = $('#h5') as Heading
        assert h5.text == 'heading 5'

        Heading h6 = $('#h6') as Heading
        assert h6.text == 'heading 6'
    }

    @Test
    public void image_should_have_expected_behaviours() {
        Image image = $('#image') as Image

        assert image.source == 'http://localhost:8080/img/Montpellier.jpg'
    }

    @Test
    public void link_should_have_expected_behaviours() {
        Link link = $('#link') as Link

        assert Link in TextSupport
        assert link.reference == 'http://localhost:8080/dsl.html'
    }

    @Test
    public void panel_should_have_expected_behaviours() {
        Panel panel = $('#panel') as Panel

        assert panel.title == ''
    }

    @Test
    public void paragraph_should_have_expected_behaviours() {
        assert Paragraph in TextSupport

        Paragraph paragraph = $('#p_1') as Paragraph

        assert paragraph.text == 'Paragraph 1'
    }

    @Test
    public void radio_should_have_expected_behaviours() {
        assert Radio in LabelSupport
        assert Radio in CheckSupport
    }

    @Test
    public void section_should_have_expected_behaviours() {
        Section section = $('#section') as Section

        assert section.paragraphs.size() == 1
        assert section.articles.size() == 1
    }
}
