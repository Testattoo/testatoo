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
package org.testatoo.config.annotation;

import org.testatoo.config.TestatooModule;

import java.lang.annotation.*;
/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * This annotation can be put on your test classes which uses the
 * {@link org.testatoo.config.junit.TestatooJunitRunner} Junit Runner
 * or {@link org.testatoo.config.junit.TestatooJunitTest} abstract test.
 * <p/>
 * It's goal is to define the list of {@link org.testatoo.config.TestatooModule}
 * to load for configuring Testatoo for your test.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Documented
public @interface TestatooModules {
    /**
     * Specify which module you want to use for configuring Testatoo. It can
     * be a list of module or only one module, since from one module you can
     * install/import other ones as needed.
     *
     * @return the list of {@link org.testatoo.config.TestatooModule}
     */
    Class<? extends TestatooModule>[] value();
}
