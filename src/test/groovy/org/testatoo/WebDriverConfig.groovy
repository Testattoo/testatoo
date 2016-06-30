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
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.testatoo.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.config

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverConfig extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        if (Boolean.valueOf(System.getProperty("CI"))) {
            switch (System.getProperty("Browser")) {
                case "Firefox":
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setEnableNativeEvents(true);
                    config.evaluator = new WebDriverEvaluator(new FirefoxDriver(profile));
                    break

                case "Chrome":
                    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
                    config.evaluator = new WebDriverEvaluator(new ChromeDriver())
                    break
            }
        } else {
//            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//            config.evaluator = new WebDriverEvaluator(new ChromeDriver())
            FirefoxProfile profile = new FirefoxProfile();
            profile.setEnableNativeEvents(true);
            config.evaluator = new WebDriverEvaluator(new FirefoxDriver(profile));
        }
    }

    @Override
    protected void after() {
        config.evaluator.close()
    }
}
