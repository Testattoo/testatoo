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
package org.testatoo.config;

import org.testatoo.config.container.ContainerConfig;
import org.testatoo.config.container.ContainerType;
import org.testatoo.config.lifecycle.LifeCycleConfig;
import org.testatoo.config.selenium.SeleniumServerConfig;
import org.testatoo.config.selenium.SeleniumSessionBuilder;
import org.testatoo.config.selenium.SeleniumSessionConfig;
import org.testatoo.config.testatoo.SeleniumServerBuilder;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * Main entry point to access all configuration and builders of Testatoo
 */
public interface TestatooConfig {

    /**
     * Import other modules to configure Testatoo
     *
     * @param modules list of {@link org.testatoo.config.TestatooModule} to import
     * @return this
     */
    TestatooConfig install(TestatooModule... modules);

    TestatooConfig useAnnotations();

    /**
     * @return the container registry in this configuration
     */
    ContainerConfig containers();

    /**
     * @return the builder of Testatoo containers (jetty, tomcat, ...)
     */
    ContainerType createContainer();

    /**
     * @return the Selenium Server registry in this configuration
     */
    SeleniumServerConfig seleniumServers();

    /**
     * @return the builder of Selenium servers
     */
    SeleniumServerBuilder createSeleniumServer();

    /**
     * @return the life cycle configurator
     */
    LifeCycleConfig lifecycle();

    /**
     * @return the Selenium sessions registry in this configuration
     */
    SeleniumSessionConfig seleniumSessions();

    /**
     * @return the builder of Selenium sessions
     */
    SeleniumSessionBuilder createSeleniumSession();

    ConcurrentTestingConfig useConcurrentTesting();

    int findFreePort();
}
