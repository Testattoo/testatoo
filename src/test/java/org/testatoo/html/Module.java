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
package org.testatoo.html;

import org.testatoo.config.AbstractTestatooModule;
import org.testatoo.config.lifecycle.TestListenerAdapter;

import java.lang.reflect.Method;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Module extends AbstractTestatooModule {

    @Override
    protected void configure() {
        if (System.getProperty("port") == null) {
            System.setProperty("port", "" + findFreePort());
        }

        install(new LocalModule());
        install(new ContainerModule());

        // TODO; NEW FEATURES TO DOCUMENT
        // 1. Ability to add listeners on test execution
        lifecycle().onTest(new TestListenerAdapter() {
            @Override
            public void onTest(Object instance, Method method) {
                System.out.println("===> Executing: " + method);
            }
        });

        // 2. Ability to add annotation support:
        // Exemple of currently supported annotation:
        // See SeleniumTest for usage
        useAnnotations();
    }
}