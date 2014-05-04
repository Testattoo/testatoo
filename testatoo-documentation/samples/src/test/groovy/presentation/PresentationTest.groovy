/**
 * Copyright (C) 2013 Ovea (dev@ovea.com)
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
package presentation

import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.Testatoo
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.Testatoo.assertThat
import static org.testatoo.core.property.Properties.*
import static org.testatoo.core.state.States.*
import static presentation.property.Properties.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class PresentationTest {

    @Delegate
    private static Factory factory

    @BeforeClass
    public static void setup() {
        Testatoo.evaluator = new WebDriverEvaluator(new FirefoxDriver())
        evaluator.registerScripts(this.getClass().getResourceAsStream('/presentation/custom.js').text)
        open 'http://localhost:8080/presentation/index.html'
        factory = new Factory()
    }

    @AfterClass
    public static void tearDown() { evaluator.close() }

    @Test
    public void except_to_not_annoy_people() {
        assertThat presentation has 12.slides
    }

    @Test
    public void try_to_capture_the_audience_with_a_punching_title() {
        assertThat presentation has title.containing('Functional Tests With - Testatoo')
        // Most important Testatoo logo is here
        assertThat testatoo_logo is visible
    }

    @Test
    public void do_not_forget_the_creator_himself() {
        assertThat presentation has author('David Avenante')
        assertThat presentation has company('Ovea.inc')
    }

    @Test
    public void presentation_start_with_a_teaser() {
        open 'http://localhost:8080/presentation/index.html#2.0'

        assertThat {
            first_teaser.is(visible) and first_teaser.has(text('Testing'))
        }
    }

    @Test
    public void the_first_topic_discusses_how_the_tests_are_felt() {
        open 'http://localhost:8080/presentation/index.html#3.0'

        assertThat reproachesList has 5.items
        assertThat reproachesList has items('Testing is too hard', 'Testing is no fun', 'Testing is sloooow', 'There’s no time to test', 'Testing sucks!')
    }

    @Test
    public void second_teaser_is_about_thinking() {
        open 'http://localhost:8080/presentation/index.html#4.0'

        assertThat {
            second_teaser.is(visible) and second_teaser.has(text('Think again'))
        }
    }

    @Test
    public void the_second_topic_discusses_how_without_test_you_cannot_develop() {
        open 'http://localhost:8080/presentation/index.html#5.1'
        assertThat {
            first_warning.is(visible) and first_warning.has(text('You can’t fix what you can’t execute'))
        }

        open 'http://localhost:8080/presentation/index.html#5.2'
        assertThat {
            second_warning.is(visible) and second_warning.has(text('You can’t fix what you can’t debug'))
        }

        open 'http://localhost:8080/presentation/index.html#5.3'
        assertThat {
            third_warning.is(visible) and third_warning.has(text('You can’t fix what you can’t test'))
        }
    }

    @Test
    public void conclusion_on_testing() {
        open 'http://localhost:8080/presentation/index.html#6.0'

        assertThat {
            first_conclusion.is(visible) and first_conclusion.has(text('Testing is a development activity'))
        }

        open 'http://localhost:8080/presentation/index.html#7.0'

        assertThat {
            second_conclusion.is(visible) and second_conclusion.has(text('Not testing is painful & time consuming'))
            third_conclusion.is(visible) and third_conclusion.has(text('Testing eliminates this pain and sacrifice'))
        }
    }

    @Test
    public void last_but_not_the_least_conclusion() {
        open 'http://localhost:8080/presentation/index.html#8.0'

        assertThat {
            last_conclusion.is(visible) and last_conclusion.has(text('Testing rulez!'))
        }
    }

    @Test
    public void last_recap_for_lazy_brain() {
        open 'http://localhost:8080/presentation/index.html#9.0'

        assertThat {
            lens.is(visible) and lens.has(text('Testing lens recap'))
            recap.is(visible) and recap.has(text('Develop by testing (TDD / BDD)'))
        }

    }

//    @Test
//    public void



}
