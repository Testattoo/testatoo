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

import com.ovea.tajin.server.ContainerConfiguration;
import org.testatoo.config.Provider;
import org.testatoo.config.container.ContainerBuilder;
import org.testatoo.config.container.ContainerInfo;

import java.io.File;
import java.net.URL;
import java.util.Properties;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultContainerBuilder implements ContainerBuilder {

    private final ContainerConfiguration containerConfiguration = ContainerConfiguration.create();
    private final String containerClass;

    DefaultContainerBuilder(String containerClass) {
        this.containerClass = containerClass;
    }

    @Override
    public Provider<ContainerInfo> build() {
        return new Provider<ContainerInfo>() {
            @Override
            public ContainerInfo get() {
                return new ContainerInfo( containerConfiguration, containerClass);
            }
        };
    }

    /* DELEGATES */

    @Override
    public ContainerBuilder serverClassPath(String serverClassPath) {
        containerConfiguration.serverClassPath(serverClassPath);
        return this;
    }

    @Override
    public ContainerBuilder serverClassPath(File... paths) {
        containerConfiguration.serverClassPath(paths);
        return this;
    }

    @Override
    public ContainerBuilder serverClassPath(URL... locations) {
        containerConfiguration.serverClassPath(locations);
        return this;
    }

    @Override
    public ContainerBuilder webappClassPath(String webappClassPath) {
        containerConfiguration.webappClassPath(webappClassPath);
        return this;
    }

    @Override
    public ContainerBuilder webappClassPath(File... paths) {
        containerConfiguration.webappClassPath(paths);
        return this;
    }

    @Override
    public ContainerBuilder webappClassPath(URL... locations) {
        containerConfiguration.webappClassPath(locations);
        return this;
    }

    @Override
    public ContainerBuilder webappRoot(String webappRoot) {
        containerConfiguration.webappRoot(webappRoot);
        return this;
    }

    @Override
    public ContainerBuilder webappRoot(File webappRoot) {
        containerConfiguration.webappRoot(webappRoot);
        return this;
    }

    @Override
    public ContainerBuilder context(String contextPath) {
        containerConfiguration.context(contextPath);
        return this;
    }

    @Override
    public ContainerBuilder port(int port) {
        containerConfiguration.port(port);
        return this;
    }

    @Override
    public ContainerBuilder add(ContainerConfiguration settings) {
        containerConfiguration.add(settings);
        return this;
    }

    @Override
    public ContainerBuilder add(Properties settings) {
        containerConfiguration.add(settings);
        return this;
    }

    @Override
    public ContainerBuilder set(String property, String value) {
        containerConfiguration.set(property, value);
        return this;
    }

    @Override
    public ContainerBuilder set(com.ovea.tajin.server.Properties property, String value) {
        containerConfiguration.set(property, value);
        return this;
    }

    @Override
    public ContainerBuilder clear(String property) {
        containerConfiguration.clear(property);
        return this;
    }

    @Override
    public String toString() {
        return containerConfiguration.toString();
    }
}
