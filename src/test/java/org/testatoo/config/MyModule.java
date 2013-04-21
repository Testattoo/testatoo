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

import com.ovea.tajin.server.Server;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.testatoo.config.cartridge.TestatooCartridge;

// Package access is just to be sure testatoo can instanciate even
// Protected classes
final class MyModule extends AbstractTestatooModule {

    // Private access is just to be sure testatoo can instanciate even
    // Protected classes
    private MyModule() {
    }

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

        seleniumSessions()
                .registerProvider(createSeleniumSession()
                    .website("http://127.0.0.1:7896/")
                    .browser("*googlechrome")
                    .serverHost("127.0.0.1")
                    .serverPort(4444)
                    .build())
                .scope(Scope.TEST_CLASS)
                .withTimeout(20000)
                .inCartridge(TestatooCartridge.HTML4);

        lifecycle().onTest(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                if (!invocation.getMethod().getName().equals("test_3")) {
                    System.out.println("====> Running: " + invocation.getMethod());
                    return invocation.proceed();
                } else {
                    System.out.println("====> Skipping: " + invocation.getMethod());
                    return null;
                }
            }
        });
    }
}