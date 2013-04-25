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

import org.testatoo.config.cartridge.EvaluatorListener;
import org.testatoo.config.cartridge.TestatooCartridge;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public interface Evaluator<T, E1, E2> {

    /**
     * Add a callback to be executed after the startup of the implementation (Selenium, WebDriver, ...)
     *
     * @param listener The callback
     * @return this
     * @see org.testatoo.config.cartridge.EvaluatorListener
     */
    E1 add(EvaluatorListener<T> listener);

    /**
     * Choose for wich cartridge to setup this implementation amongst supported cartridge.
     * <br>
     * If the cartridge is not supported (custom cartridge), you can use
     * {@link #add(org.testatoo.config.cartridge.EvaluatorListener)} to register
     * a callback to setup your {@link org.testatoo.core.Cartridge}
     *
     * @param cartridge The cartridge to build with this implementation
     * @return Fluent API ocnstruction...
     * @see org.testatoo.config.cartridge.TestatooCartridge
     */
    E2 inCartridge(TestatooCartridge cartridge);
}
