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
import org.junit.runner.RunWith;
import org.testatoo.core.Testatoo;
import org.testatoo.core.component.TextField;

@RunWith(TestatooJunitRunner.class)
@TestatooModules(MyModule.class)
public final class DSLWithJunitAnnotationTest extends Testatoo {

    @Test
    public void test_1() throws Exception {
        open("/index.html");
        assertThat(new TextField("text_field").is(visible()));
    }

    @Test
    public void test_2() {
        open("/index.html");
        assertThat(new TextField("text_field").is(visible()));
    }

    @Test
    public void test_3() {
        throw new IllegalStateException("This test should be skipped by MethodInterceptor in module TestModule");
    }

}