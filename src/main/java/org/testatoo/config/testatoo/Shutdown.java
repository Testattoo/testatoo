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
package org.testatoo.config.testatoo;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class Shutdown {

    private static final Shutdown SHUTDOWN = new Shutdown();

    private final List<Callable> hooks = new CopyOnWriteArrayList<Callable>();

    private Shutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread("Testatoo Module cleaner") {
            @Override
            public void run() {
                for (Callable hook : hooks) {
                    try {
                        hook.call();
                    } catch (Exception ignored) {
                    }
                }
            }
        });
    }

    public static void addHook(Callable callable) {
        SHUTDOWN.hooks.add(callable);
    }

}
