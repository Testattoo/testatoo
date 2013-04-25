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
package org.testatoo.config.container;

import com.ovea.tajin.server.ContainerConfiguration;
import org.testatoo.config.ProviderBuilder;

import java.io.File;
import java.net.URL;
import java.util.Properties;

public interface ContainerBuilder extends ProviderBuilder<ContainerInfo> {

    ContainerBuilder serverClassPath(String serverClassPath);

    ContainerBuilder serverClassPath(File... paths);

    ContainerBuilder serverClassPath(URL... locations);

    ContainerBuilder webappClassPath(String webappClassPath);

    ContainerBuilder webappClassPath(File... paths);

    ContainerBuilder webappClassPath(URL... locations);

    ContainerBuilder webappRoot(String webappRoot);

    ContainerBuilder webappRoot(File webappRoot);

    ContainerBuilder context(String contextPath);

    ContainerBuilder port(int port);

    ContainerBuilder add(ContainerConfiguration settings);

    ContainerBuilder add(Properties settings);

    ContainerBuilder set(String property, String value);

    ContainerBuilder set(com.ovea.tajin.server.Properties property, String value);

    ContainerBuilder clear(String property);
}
