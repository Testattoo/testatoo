/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.config.testatoo;

import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.Selenium;
import org.testatoo.config.Provider;
import org.testatoo.config.selenium.SeleniumSessionBuilder;

import static org.testatoo.config.testatoo.Ensure.notNull;
import static org.testatoo.config.testatoo.Ensure.require;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultSeleniumSessionBuilder implements SeleniumSessionBuilder {

    private CommandProcessor commandProcessor;
    private String serverHost;
    private String website;
    private int port;
    private String browser;

    @Override
    public Provider<Selenium> build() {
        return new Provider<Selenium>() {
            @Override
            public Selenium get() {
                if (commandProcessor == null) {
                    require(serverHost, "Selenium server host");
                    require(port, "Selenium server port");
                    require(browser, "Browser command");
                    require(website, "Target website");
                    commandProcessor = new HttpCommandProcessor(serverHost, port, browser, website);
                }
                return new DefaultSelenium(commandProcessor);
            }
        };
    }

    @Override
    public SeleniumSessionBuilder from(CommandProcessor commandProcessor) {
        notNull(commandProcessor, "Selenium CommandProcessor");
        this.commandProcessor = commandProcessor;
        return this;
    }

    @Override
    public SeleniumSessionBuilder serverHost(String host) {
        notNull(host, "Selenium Server host");
        this.serverHost = host;
        return this;
    }

    @Override
    public SeleniumSessionBuilder serverPort(int port) {
        this.port = port;
        return this;
    }

    @Override
    public SeleniumSessionBuilder website(String website) {
        notNull(website, "Website");
        this.website = website;
        return this;
    }

    @Override
    public SeleniumSessionBuilder browser(String browser) {
        notNull(browser, "Browser");
        this.browser = browser;
        return this;
    }

}
