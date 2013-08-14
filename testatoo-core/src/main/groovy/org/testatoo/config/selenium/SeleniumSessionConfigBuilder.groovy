package org.testatoo.config.selenium

import com.thoughtworks.selenium.Selenium
import org.testatoo.config.Evaluator

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public interface SeleniumSessionConfigBuilder extends Evaluator<Selenium, SeleniumSessionConfigBuilder, SeleniumSessionConfig> {

    SeleniumSessionConfigBuilder withTimeout(int timeoutMilliseconds)

    SeleniumSessionConfigBuilder named(String sessionName)
}
