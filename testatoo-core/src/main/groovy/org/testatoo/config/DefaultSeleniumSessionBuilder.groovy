package org.testatoo.config

import com.thoughtworks.selenium.CommandProcessor
import com.thoughtworks.selenium.DefaultSelenium
import com.thoughtworks.selenium.HttpCommandProcessor
import com.thoughtworks.selenium.Selenium
import org.testatoo.config.selenium.SeleniumSessionBuilder

import static org.testatoo.config.Ensure.notNull
import static org.testatoo.config.Ensure.require

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultSeleniumSessionBuilder implements SeleniumSessionBuilder {

    private CommandProcessor commandProcessor
    private String serverHost
    private String website
    private int port
    private String browser

    @Override
    public Provider<Selenium> build() {
        return new Provider<Selenium>() {
            @Override
            public Selenium get() {
                if (commandProcessor == null) {
                    require(serverHost, "Selenium server host")
                    require(port, "Selenium server port")
                    require(browser, "Browser command")
                    require(website, "Target website")
                    commandProcessor = new HttpCommandProcessor(serverHost, port, browser, website)
                }
                return new DefaultSelenium(commandProcessor)
            }
        };
    }

    @Override
    public SeleniumSessionBuilder from(CommandProcessor commandProcessor) {
        notNull(commandProcessor, "Selenium CommandProcessor")
        this.commandProcessor = commandProcessor
        return this
    }

    @Override
    public SeleniumSessionBuilder serverHost(String host) {
        notNull(host, "Selenium Server host")
        this.serverHost = host
        return this
    }

    @Override
    public SeleniumSessionBuilder serverPort(int port) {
        this.port = port
        return this
    }

    @Override
    public SeleniumSessionBuilder website(String website) {
        notNull(website, "Website")
        this.website = website
        return this
    }

    @Override
    public SeleniumSessionBuilder browser(String browser) {
        notNull(browser, "Browser")
        this.browser = browser
        return this
    }

}