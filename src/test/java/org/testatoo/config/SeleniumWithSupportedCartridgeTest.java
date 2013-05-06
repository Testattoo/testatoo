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

import com.ovea.tajin.server.Server;
import com.thoughtworks.selenium.Selenium;
import org.junit.Test;
import org.testatoo.config.testatoo.Testatoo;
import org.testatoo.core.component.TextField;

public final class SeleniumWithSupportedCartridgeTest extends org.testatoo.core.Testatoo {

    @Test
    public void test() throws Throwable {

        Testatoo testatoo = Testatoo.configure(new AbstractTestatooModule() {
            @Override
            protected void configure() {
                install(commonModule());

                Provider<Selenium> provider = createSeleniumSession()
                        .website("http://localhost:7896/")
                        .browser("*googlechrome")
                        .serverHost("localhost")
                        .serverPort(4444)
                        .build();

                seleniumSessions()
                        .registerProvider(provider)
                        .scope(Scope.TEST_CLASS)
                        .withTimeout(20000);

            }
        });

        testatoo.start();
        open("/index.html");
        assertThat(new TextField("text_field").is(visible()));
        testatoo.stop();
    }

    private TestatooModule commonModule() {
        return new AbstractTestatooModule() {
            @Override
            protected void configure() {
                containers().registerProvider(createContainer()
                    .implementedBy(Server.JETTY9)
                    .webappRoot("src/test/webapp")
                    .port(7896)
                    .build())
                        .scope(Scope.TEST_CLASS);
                seleniumServers().registerProvider(createSeleniumServer()
                    .port(4444)
                    .build())
                        .scope(Scope.TEST_CLASS);
            }
        };
    }
}