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

import org.junit.Test;
import org.testatoo.config.testatoo.Testatoo;

import java.util.BitSet;

import static org.junit.Assert.assertEquals;

public final class ModuleInstallTest {

    @Test
    public void test() throws Exception {
        final BitSet checks = new BitSet(5);

        Testatoo.configure(new AbstractTestatooModule() {
            @Override
            protected void configure() {
                assertEquals(checks.toString(), "{}");
                checks.set(0);
                install(new TestatooModule() {
                    @Override
                    public void configure(TestatooConfig config) {
                        assertEquals(checks.toString(), "{0}");
                        checks.set(1);
                    }
                });
                assertEquals(checks.toString(), "{0, 1}");
                checks.set(2);
            }
        }, new TestatooModule() {
            @Override
            public void configure(TestatooConfig config) {
                assertEquals(checks.toString(), "{0, 1, 2}");
                checks.set(3);
            }
        });

        assertEquals(checks.toString(), "{0, 1, 2, 3}");
    }

}
