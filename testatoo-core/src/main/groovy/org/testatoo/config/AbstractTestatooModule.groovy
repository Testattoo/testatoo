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
package org.testatoo.config

import org.testatoo.config.lifecycle.LifeCycleConfig
import org.testatoo.config.selenium.SeleniumServerBuilder
import org.testatoo.config.selenium.SeleniumServerConfig
import org.testatoo.config.selenium.SeleniumSessionBuilder
import org.testatoo.config.selenium.SeleniumSessionConfig

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

abstract class AbstractTestatooModule implements TestatooModule {

    private TestatooConfig testatooConfig

    @Override
    public void configure(TestatooConfig testatooConfig) throws Throwable {
        if (this.testatooConfig != null) {
            throw new IllegalStateException("Re-entrance not allowed")
        }

        if (testatooConfig == null) {
            throw new NullPointerException("testatooConfig")
        }

        try {
            this.testatooConfig = testatooConfig
            configure()
        } finally {
            this.testatooConfig = null
        }
    }

    /**
     * @see TestatooConfig#install(TestatooModule...)
     */
    protected final TestatooConfig install(TestatooModule... modules) throws Throwable {
        config().install(modules)
    }

    /**
     * @see TestatooConfig#lifecycle()
     */
    protected final LifeCycleConfig lifecycle() {
        testatooConfig.lifecycle()
    }

    /**
     * @return the {@link org.testatoo.config.TestatooConfig} configurator class
     */
    protected final TestatooConfig config() {
        testatooConfig
    }

    /**
     * @see org.testatoo.config.TestatooConfig#seleniumServers()
     */
    protected final SeleniumServerConfig seleniumServers() {
        testatooConfig.seleniumServers()
    }

    /**
     * @see org.testatoo.config.TestatooConfig#createSeleniumServer()
     */
    protected final SeleniumServerBuilder createSeleniumServer() {
        testatooConfig.createSeleniumServer()
    }

    /**
     * @see org.testatoo.config.TestatooConfig#seleniumSessions()
     */
    protected final SeleniumSessionConfig seleniumSessions() {
        testatooConfig.seleniumSessions()
    }

    /**
     * @see org.testatoo.config.TestatooConfig#createSeleniumSession()
     */
    protected final SeleniumSessionBuilder createSeleniumSession() {
        testatooConfig.createSeleniumSession()
    }

    /**
     * @see org.testatoo.config.TestatooConfig#createSeleniumSession()
     */
    protected final ConcurrentTestingConfig useConcurrentTesting() {
        testatooConfig.useConcurrentTesting()
    }

    protected final TestatooConfig useAnnotations() {
        testatooConfig.useAnnotations()
    }

    protected final int findFreePort() {
        testatooConfig.findFreePort()
    }

    protected abstract void configure() throws Throwable
}