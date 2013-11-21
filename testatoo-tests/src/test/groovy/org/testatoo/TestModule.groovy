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
package org.testatoo

import org.testatoo.config.AbstractTestatooModule
import org.testatoo.config.Scope
import org.testatoo.core.Log

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class TestModule extends AbstractTestatooModule {

    static int seleniumPort = -1;

    @Override
    protected void configure() {

        Log.selenium = true
        Log.testatoo = true

        if (seleniumPort == -1) {
            seleniumPort = findFreePort();
        }

        seleniumServers().registerProvider(createSeleniumServer()
            .setPort(seleniumPort)
            .setSingleWindow(true)
            .setAvoidProxy(true)
            .setHonorSystemProxy(false)
            .build())
            .scope(Scope.TEST_SUITE);

        seleniumSessions()
            .registerProvider(createSeleniumSession()
            .website("http://" + System.getProperty("host", "localhost") + ":" + System.getProperty("port", "8080"))
//            .browser("*googlechrome")
            .browser("*firefox")
            .serverHost("localhost")
            .serverPort(seleniumPort)
            .build())
            .scope(Scope.TEST_SUITE)
            .withTimeout(20000);

        useAnnotations()
    }
}
