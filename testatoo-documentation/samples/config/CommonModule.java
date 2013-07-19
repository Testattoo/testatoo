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
package org.testatoo.config.env;

import org.testatoo.config.AbstractTestatooModule;
import org.testatoo.config.lifecycle.TestInterceptor;
import org.testatoo.config.lifecycle.TestInvocation;

final class CommonModule extends AbstractTestatooModule {

    @Override
    protected void configure() {
        lifecycle()
                .onTest(new TestInterceptor() {
                    @Override
                    public void invoke(TestInvocation invocation) throws Throwable {
                        if (!invocation.getMethod().getName().equals("test_3")) {
                            System.out.println("====> Running: " + invocation.getMethod());
                            invocation.proceed();
                        } else {
                            System.out.println("====> Skipping: " + invocation.getMethod());
                        }
                    }
                })
                .onStart(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("STARTING !!!");
                    }
                })
                .onStop(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("STOPPING !!!");
                    }
                });
    }
}