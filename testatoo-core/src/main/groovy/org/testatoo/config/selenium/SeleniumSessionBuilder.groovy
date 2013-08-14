package org.testatoo.config.selenium

import com.thoughtworks.selenium.CommandProcessor
import com.thoughtworks.selenium.Selenium
import org.testatoo.config.ProviderBuilder

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public interface SeleniumSessionBuilder extends ProviderBuilder<Selenium> {

    SeleniumSessionBuilder serverHost(String host)

    SeleniumSessionBuilder serverPort(int port)

    SeleniumSessionBuilder website(String website)

    SeleniumSessionBuilder browser(String browser)

    SeleniumSessionBuilder from(CommandProcessor commandProcessor)
}
