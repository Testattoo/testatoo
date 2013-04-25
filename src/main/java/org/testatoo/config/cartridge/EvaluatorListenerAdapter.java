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
package org.testatoo.config.cartridge;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * Adpater class of {@link org.testatoo.config.cartridge.EvaluatorListener}.
 * <p/>
 * We strongly recommend that classes which need to receive callback of
 * object startup extend this adapter class
 * insead of implementing {@link org.testatoo.config.cartridge.EvaluatorListener}.
 * <br>
 * This will allow us adding more events to the interface without impacting your code.
 *
 * @see org.testatoo.config.cartridge.EvaluatorListener
 */
public class EvaluatorListenerAdapter<T> implements EvaluatorListener<T> {
    @Override
    public void afterStart(T object) {
    }

    @Override
    public void afterStop(T object) {
    }

    @Override
    public void beforeStart(T object) {
    }

    @Override
    public void beforeStop(T object) {
    }
}
