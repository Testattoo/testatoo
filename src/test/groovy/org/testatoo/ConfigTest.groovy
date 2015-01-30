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
package org.testatoo

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testatoo.core.component.Component
import org.testatoo.core.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.state.States.getVisible

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ConfigTest {

    @Test
    public void can_obtain_the_underline_implementation() {
        try {
            WebDriver driver = new FirefoxDriver();
            evaluator = new WebDriverEvaluator(driver)

            assert evaluator.implementation instanceof WebDriver
            assert evaluator.implementation == driver
        } finally {
            evaluator.close()
        }
    }

    @Test
    public void extensions_are_auto_loaded() {
        try {
            WebDriver driver = new FirefoxDriver();
            evaluator = new WebDriverEvaluator(driver)

            open 'http://localhost:8080/selectors.html'

            MyCustomComponent myCustomComponent = $('#my-custom-component') as MyCustomComponent
            myCustomComponent.should { be visible }

        } finally {
            evaluator.close()
        }
    }

    @Test
    @Ignore
    public void can_register_a_script() {
        // TODO
        // test method @Override
//        void registerScripts(String... scripts) {
//            registeredScripts.addAll(scripts);
//        }

        // in WebDriver Evaluator
    }


    private class MyCustomComponent extends Component {
    }
}
