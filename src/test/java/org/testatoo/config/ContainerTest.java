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

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.ovea.tajin.server.ContainerConfiguration;
import com.ovea.tajin.server.Server;
import org.junit.Test;
import org.testatoo.config.container.ContainerInfo;
import org.testatoo.config.testatoo.Testatoo;

import javax.net.ServerSocketFactory;
import java.net.ServerSocket;

import static org.junit.Assert.*;

public final class ContainerTest {

    @Test
    public void test() throws Throwable {
        Testatoo testatoo = Testatoo.configure(new AbstractTestatooModule() {
            @Override
            protected void configure() {
                Provider<ContainerInfo> provider = createContainer()
                        .implementedBy(Server.JETTY9)
                        .webappRoot("src/test/webapp")
                        .context("/mycontext")
                        .port(7896)
                        .build();

                containers()
                        .register(provider.get())
                        .scope(Scope.TEST_CLASS)
                        .register(new ContainerInfo(ContainerConfiguration.create()
                                .webappRoot("src/test/webapp")
                                .context("/mycontext")
                                .port(7897), "com.ovea.tajin.server.Jetty9Container"))
                        .scope(Scope.TEST_CLASS);

            }
        });

        assertTrue("Verify that port 7896 is free", isPortFree(7896));
        assertTrue("Verify that port 7897 is free", isPortFree(7897));

        testatoo.start();

        assertFalse("Verify that port 7896 is used", isPortFree(7896));
        assertFalse("Verify that port 7897 is used", isPortFree(7897));

        verify("http://127.0.0.1:7896/mycontext/container.html");
        verify("http://127.0.0.1:7897/mycontext/container.html");

        testatoo.stop();

        assertTrue("Verify that port 7896 is free", isPortFree(7896));
        assertTrue("Verify that port 7897 is free", isPortFree(7897));
    }

    private void verify(String url) throws Exception {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse(url);
        assertEquals(resp.getTitle(), "Testatoo Rocks");
        assertTrue(resp.getText().contains("Testatoo Rocks"));
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
