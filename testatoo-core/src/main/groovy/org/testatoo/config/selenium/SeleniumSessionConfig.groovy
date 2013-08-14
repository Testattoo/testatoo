package org.testatoo.config.selenium

import com.thoughtworks.selenium.Selenium
import org.testatoo.config.ScopedProviderRegistry

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public interface SeleniumSessionConfig extends ScopedProviderRegistry<Selenium, SeleniumSessionConfigBuilder> {
}
