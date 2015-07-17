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
package org.testatoo.bundle.html5

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator
import org.testatoo.core.state.States

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.property.Properties.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class StructureTest {

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        open 'http://localhost:8080/components.html'
    }

    @AfterClass
    public static void tearDown() { Testatoo.evaluator.close() }

    @Test
    public void heading_should_have_expected_behaviours() {
        Heading heading_1 = $('#heading_1') as Heading
        heading_1.should { have text('heading 1') }

        Heading heading_2 = $('#heading_2') as Heading
        heading_2.should { have text('heading 2') }

        Heading heading_3 = $('#heading_3') as Heading
        heading_3.should { have text('heading 3') }

        Heading heading_4 = $('#heading_4') as Heading
        heading_4.should { have text('heading 4') }

        Heading heading_5 = $('#heading_5') as Heading
        heading_5.should { have text('heading 5') }

        Heading heading_6 = $('#heading_6') as Heading
        heading_6.should { have text('heading 6') }
    }

    @Test
    public void paragraph_should_have_expected_behaviours() {
        Paragraph paragraph = $('#p_1') as Paragraph

        paragraph.should { have text('Paragraph 1') }
    }

    @Test
    public void article_should_have_expected_behaviours() {
        Article article = $('#article') as Article

        article.should {
            be States.enabled
            be States.visible
            have 2.paragraphs
        }
    }

    @Test
    public void aside_should_have_expected_behaviours() {
        Aside aside = $('#aside') as Aside

        aside.should {
            be States.enabled
            be States.visible
        }
    }

    @Test
    public void footer_should_have_expected_behaviours() {
        Footer footer = $('#footer') as Footer

        footer.should {
            be States.enabled
            be States.visible
        }
    }

    @Test
    public void header_should_have_expected_behaviours() {
        Header header = $('#header') as Header

        header.should {
            be States.enabled
            be States.visible
        }
    }

    @Test
    public void section_should_have_expected_behaviours() {
        Section section = $('#section') as Section

        section.should {
            be States.enabled
            be States.visible
            have 1.paragraphs
            have 1.articles
        }
    }
}
