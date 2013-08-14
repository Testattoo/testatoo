package org.testatoo.config.selenium

import org.openqa.selenium.server.SeleniumServer
import org.testatoo.config.ScopedProviderRegistry

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public interface SeleniumServerConfig extends ScopedProviderRegistry<SeleniumServer, SeleniumServerConfig> {
}