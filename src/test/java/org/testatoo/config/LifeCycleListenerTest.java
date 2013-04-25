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

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.testatoo.config.testatoo.Testatoo;

import java.util.BitSet;

import static org.junit.Assert.assertEquals;

public final class LifeCycleListenerTest {

    @Test
    public void test() throws Throwable {
        final BitSet checks = new BitSet(4);

        Testatoo testatoo = Testatoo.configure(new AbstractTestatooModule() {
            @Override
            protected void configure() {
                lifecycle()
                        .onStart(new Runnable() {
                            @Override
                            public void run() {
                                assertEquals(checks.toString(), "{}");
                                checks.set(0);
                            }
                        })
                        .onStart(new Runnable() {
                            @Override
                            public void run() {
                                assertEquals(checks.toString(), "{0}");
                                checks.set(1);
                            }
                        })
                        .onStop(new Runnable() {
                            @Override
                            public void run() {
                                assertEquals(checks.toString(), "{0, 1, 2}");
                                checks.set(3);
                            }
                        })
                        .onTest(new MethodInterceptor() {
                            @Override
                            public Object invoke(MethodInvocation invocation) throws Throwable {
                                System.out.println("Will run test method: " + invocation.getMethod());
                                return invocation.proceed();
                            }
                        });
            }
        });

        testatoo.start();

        assertEquals(checks.toString(), "{0, 1}");
        checks.set(2);

        testatoo.stop();

        assertEquals(checks.toString(), "{0, 1, 2, 3}");
    }

}