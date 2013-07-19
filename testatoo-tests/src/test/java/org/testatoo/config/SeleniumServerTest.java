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

import org.junit.Test;
import org.testatoo.config.testatoo.Testatoo;
import org.testatoo.selenium.server.SeleniumServer;
import org.testatoo.selenium.server.SeleniumServerFactory;

import javax.net.ServerSocketFactory;
import java.net.ServerSocket;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class SeleniumServerTest {

    @Test
    public void test() throws Throwable {

        Testatoo testatoo = Testatoo.configure(new AbstractTestatooModule() {
            @Override
            protected void configure() {
                Provider<SeleniumServer> provider = createSeleniumServer()
                        .port(4444)
                        .build();
                seleniumServers()
                        .registerProvider(provider)
                        .scope(Scope.TEST_CLASS)
                        .register(SeleniumServerFactory
                                .commandeLine("-port", "5555")
                                .create())
                        .scope(Scope.TEST_CLASS);

            }
        });

        assertTrue("Verify that port 4444 is free", isPortFree(4444));
        assertTrue("Verify that port 5555 is free", isPortFree(5555));

        testatoo.start();

        assertFalse("Verify that port 4444 is used", isPortFree(4444));
        assertFalse("Verify that port 5555 is used", isPortFree(5555));

        testatoo.stop();

        assertTrue("Verify that port 4444 is free", isPortFree(4444));
        assertTrue("Verify that port 5555 is free", isPortFree(5555));
    }

    private boolean isPortFree(int port) {
        try {
            ServerSocket server = ServerSocketFactory.getDefault().createServerSocket(port);
            server.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}