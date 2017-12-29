/**
 * Copyright © 2016 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo

import io.github.bonigarcia.wdm.ChromeDriverManager
import io.github.bonigarcia.wdm.EdgeDriverManager
import io.github.bonigarcia.wdm.FirefoxDriverManager
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.DefaultHandler
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.junit.rules.ExternalResource
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.testatoo.evaluator.webdriver.WebDriverEvaluator

import static org.testatoo.core.Testatoo.config

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class WebDriverConfig extends ExternalResource {
    public static String BASE_URL
    private static Server server

    @Override
    protected void before() throws Throwable {
        // Defined by JVM maven arguments
        final String browser = System.getProperty('browser') ?: 'Chrome'
        final boolean docker = Boolean.valueOf(System.getProperty('docker')) ?: false // -Ddocker=true
        final String ip = System.getProperty('ip') ?: '127.0.0.1' // -DIP=xxx.xxx.xxx.xxx

        BASE_URL = 'http://' + ip + ':8080/'

        startJetty()

        switch (browser) {
            case 'Firefox':
                println '================== Firefox Profile ==================='
                if (docker) {
                    WebDriver driver = new RemoteWebDriver(new URL('http://localhost:4444/wd/hub'), DesiredCapabilities.firefox())
                    config.evaluator = new WebDriverEvaluator(driver)
                } else {
                    FirefoxDriverManager.instance.setup()
                    config.evaluator = new WebDriverEvaluator(new FirefoxDriver())
                }
                break
            case 'Chrome':
                println '=================== Chrome Profile ==================='
                if (docker) {
                    WebDriver driver = new RemoteWebDriver(new URL('http://localhost:4444/wd/hub'), DesiredCapabilities.chrome())
                    config.evaluator = new WebDriverEvaluator(driver)
                } else {
                    ChromeDriverManager.instance.setup()
                    config.evaluator = new WebDriverEvaluator(new ChromeDriver())
                }
                break
            case 'Edge':
                println '==================== Edge Profile ===================='
                EdgeDriverManager.instance.setup()
                config.evaluator = new WebDriverEvaluator(new EdgeDriver())
                break
        }
    }

    @Override
    protected void after() {
        config.evaluator.close()
        server.stop()
    }

    private static void startJetty() {
        server = new Server(8080)
        ResourceHandler resource_handler = new ResourceHandler()

        resource_handler.directoriesListed = true
        resource_handler.welcomeFiles = ['index.html']
        resource_handler.resourceBase = 'src/test/webapp'

        HandlerList handlers = new HandlerList()
        handlers.handlers = [resource_handler, new DefaultHandler()]
        server.handler = handlers

        server.start()
    }
}