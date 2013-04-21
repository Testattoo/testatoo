/**
 * Copyright (C) 2013 Ovea <dev@testatoo.org>
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
import org.testatoo.config.selenium.SeleniumServerBuilder;
import org.testatoo.config.selenium.SeleniumServerConfig;
import org.testatoo.config.selenium.SeleniumSessionBuilder;
import org.testatoo.config.selenium.SeleniumSessionConfig;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public abstract class AbstractTestatooModule implements TestatooModule {

    private TestatooConfig testatooConfig;

    @Override
    public void configure(TestatooConfig testatooConfig) {
        if (this.testatooConfig != null) {
            throw new IllegalStateException("Re-entrance not allowed");
        }

        if (testatooConfig == null) {
            throw new NullPointerException("testatooConfig");
        }

        try {
            this.testatooConfig = testatooConfig;
            configure();
        } finally {
            this.testatooConfig = null;
        }
    }

    /**
     * @see TestatooConfig#install(TestatooModule...)
     */
    protected final TestatooConfig install(TestatooModule... modules) {
        return config().install(modules);
    }

    /**
     * @see TestatooConfig#lifecycle()
     */
    protected final LifeCycleConfig lifecycle() {
        return testatooConfig.lifecycle();
    }

    /**
     * @return the {@link org.testatoo.config.TestatooConfig} configurator class
     */
    protected final TestatooConfig config() {
        return testatooConfig;
    }

    /**
     * @see org.testatoo.config.TestatooConfig#containers()
     */
    protected final ContainerConfig containers() {
        return testatooConfig.containers();
    }

    /**
     * @see org.testatoo.config.TestatooConfig#createContainer()
     */
    protected final ContainerType createContainer() {
        return testatooConfig.createContainer();
    }

    /**
     * @see org.testatoo.config.TestatooConfig#seleniumServers()
     */
    protected final SeleniumServerConfig seleniumServers() {
        return testatooConfig.seleniumServers();
    }

    /**
     * @see org.testatoo.config.TestatooConfig#createSeleniumServer()
     */
    protected final SeleniumServerBuilder createSeleniumServer() {
        return testatooConfig.createSeleniumServer();
    }

    /**
     * @see org.testatoo.config.TestatooConfig#seleniumSessions()
     */
    protected final SeleniumSessionConfig seleniumSessions() {
        return testatooConfig.seleniumSessions();
    }

    /**
     * @see org.testatoo.config.TestatooConfig#createSeleniumSession()
     */
    protected final SeleniumSessionBuilder createSeleniumSession() {
        return testatooConfig.createSeleniumSession();
    }

    /**
     * @see org.testatoo.config.TestatooConfig#createSeleniumSession()
     */
    protected final ConcurrentTestingConfig useConcurrentTesting() {
        return testatooConfig.useConcurrentTesting();
    }

    protected final TestatooConfig useAnnotations() {
        return testatooConfig.useAnnotations();
    }

    protected final int findFreePort() {
        return testatooConfig.findFreePort();
    }

    protected abstract void configure();
}
