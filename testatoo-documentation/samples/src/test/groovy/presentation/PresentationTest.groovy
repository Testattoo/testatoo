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
    public void except_to_not_be_boring() {
        expect presentation has 17.slides
    }

    @Test
    public void try_to_capture_the_audience_with_a_punching_title() {
        expect presentation has title.containing('Functional Tests With Testatoo')
        // Most important Testatoo logo is here
        expect testatoo_logo is visible
    }

    @Test
    public void do_not_forget_the_creator_himself() {
        expect presentation has author('David Avenante')
        expect presentation has company('Ovea.inc')
    }

    @Test
    public void presentation_start_with_a_teaser() {
        open 'http://localhost:8080/presentation/index.html#2.0'
        expect {
            first_teaser.is(visible) and first_teaser.has(text('Testing'))
        }
    }

    @Test
    public void the_first_topic_discusses_how_the_tests_are_felt() {
        open 'http://localhost:8080/presentation/index.html#3.0'

        expect reproaches_list has 5.items
        expect reproaches_list has items(
            'Testing is too hard',
            'Testing is not fun',
            'Testing is sloooow',
            'There’s no time to test',
            'Testing sucks!')
    }

    @Test
    public void second_teaser_is_about_thinking() {
        open 'http://localhost:8080/presentation/index.html#4.0'
        expect {
            second_teaser.is(visible) and second_teaser.has(text('Think again'))
        }
    }

    @Test
    public void the_second_topic_discusses_how_without_test_you_cannot_develop() {
        open 'http://localhost:8080/presentation/index.html#5.1'

        expect rethink_list has 5.items
        expect rethink_list has items(
            'too hard: improve your skills',
            'not fun: be disciplined',
            'is sloooow: change your design',
            'no time: do it FIRST',
            'Your testing approach sucks!')
    }

    @Test
    public void conclusion_on_testing() {
        open 'http://localhost:8080/presentation/index.html#6.0'
        expect {
            first_conclusion.is(visible) and first_conclusion.has(text('Testing is a development activity'))
        }

        open 'http://localhost:8080/presentation/index.html#7.0'
        expect {
            second_conclusion.is(visible) and second_conclusion.has(text('Testing rulez!'))
        }
    }

    @Test
    public void last_recap_for_lazy_brain() {
        open 'http://localhost:8080/presentation/index.html#8.0'
        expect {
            lens.is(visible) and lens.has(text('Reminder'))
            recap.is(visible) and recap.has(text('Develop by test (TDD/BDD)'))
        }
    }

    @Test
    public void what_tests_are() {
        open 'http://localhost:8080/presentation/index.html#9.0'
        expect {
            philosophy_title.is(visible) and philosophy_title.has(text('TDD/BDD are not a Philosophy'))
        }

        open 'http://localhost:8080/presentation/index.html#11.0'
        expect {
            practice_title.is(visible) and practice_title.has(text('TDD/BDD are industrial practices'))
        }
    }

    @Test
    public void terms() {
        open 'http://localhost:8080/presentation/index.html#11.0'
        expect {
            term_title.is(visible) and term_title.has(text('Terms... Terms'))
        }

        open 'http://localhost:8080/presentation/index.html#12.0'
        expect terms_list has 3.items
        expect terms_list has items(
            'Unit Tests: Dev to Dev',
            'Business Tests: Dev to BA',
            'Functional tests: Dev - BA - Client')
    }

    @Test
    public void simpler_explanation() {
        open 'http://localhost:8080/presentation/index.html#13.0'
        expect {
            what_message.is(visible) and what_message.has(text('WHAT: business tests'))
        }

        open 'http://localhost:8080/presentation/index.html#14.0'
        expect {
            how_message.is(visible) and how_message.has(text('HOW: functional tests'))
        }
    }

    @Test
    public void the_specificity_of_functional_test() {
        open 'http://localhost:8080/presentation/index.html#15.0'

        expect {
            functional_tests_specificity_title.is(visible) and functional_tests_specificity_title.has(text('Functional tests have some specificities'))
        }

        open 'http://localhost:8080/presentation/index.html#16.0'
        expect functional_tests_specificity_list has 4.items
        expect functional_tests_specificity_list has items(
                'they are mainly UI tests',
                'they are hard to setup and configure',
                'the ROI is low',
                'they cannot be done FIRST!')
    }

    @Test
    public void presentation_end_with_a_teaser() {
        open 'http://localhost:8080/presentation/index.html#17.0'

        expect {
            last_teaser.is(visible) and last_teaser.has(text('Testatoo'))
        }
    }
}
