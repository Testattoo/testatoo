package org.testatoo.config

import org.testatoo.config.lifecycle.LifeCycleConfig
import org.testatoo.config.selenium.SeleniumServerBuilder
import org.testatoo.config.selenium.SeleniumServerConfig
import org.testatoo.config.selenium.SeleniumSessionBuilder
import org.testatoo.config.selenium.SeleniumSessionConfig

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * Main entry point to access all configuration and builders of Testatoo
 */
interface TestatooConfig {

    /**
     * Import other modules to configure Testatoo
     *
     * @param modules list of {@link org.testatoo.config.TestatooModule} to import
     * @return this
     */
    TestatooConfig install(TestatooModule... modules) throws Throwable

    TestatooConfig useAnnotations()

    /**
     * @return the Selenium Server registry in this configuration
     */
    SeleniumServerConfig seleniumServers()

    /**
     * @return the builder of Selenium servers
     */
    SeleniumServerBuilder createSeleniumServer()

    /**
     * @return the life cycle configurator
     */
    LifeCycleConfig lifecycle()

    /**
     * @return the Selenium sessions registry in this configuration
     */
    SeleniumSessionConfig seleniumSessions()

    /**
     * @return the builder of Selenium sessions
     */
    SeleniumSessionBuilder createSeleniumSession()

    ConcurrentTestingConfig useConcurrentTesting()

    int findFreePort()
}