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
package org.testatoo

import org.junit.rules.ExternalResource
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.testatoo.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.config

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverConfig extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        String browser = System.getProperty('browser') ?: 'Firefox' // defined in the maven profile
        switch (browser) {
            case 'Firefox':
                println '=================== Firefox Profile ==================='
                System.setProperty('webdriver.gecko.driver', '/usr/local/bin/geckodriver')
                DesiredCapabilities cap = DesiredCapabilities.firefox()
                cap.setCapability('marionette', true)
                config.evaluator = new WebDriverEvaluator(new FirefoxDriver(cap))
                break
            case 'Chrome':
                println '=================== Chrome Profile ==================='
                System.setProperty('webdriver.chrome.driver', '/usr/local/bin/chromedriver')
                config.evaluator = new WebDriverEvaluator(new ChromeDriver())
                break
            case 'Edge':
                println '=================== Edge Profile ==================='
                System.setProperty('webdriver.edge.driver', 'C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe')
                config.evaluator = new WebDriverEvaluator(new EdgeDriver())
                break
        }
    }

    @Override
    protected void after() {
        config.evaluator.close()
    }
}