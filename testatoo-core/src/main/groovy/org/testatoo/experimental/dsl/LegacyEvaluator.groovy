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
package org.testatoo.experimental.dsl

import org.testatoo.core.EvaluatorHolder
import org.testatoo.core.input.Click
import org.testatoo.experimental.dsl.component.IdSupport

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class LegacyEvaluator implements Evaluator {

    @Override
    void open(String url) { EvaluatorHolder.get().open(url) }

    @Override
    boolean isEnabled(IdSupport component) { EvaluatorHolder.get().isEnabled(component) }

    @Override
    boolean isVisible(IdSupport component) { EvaluatorHolder.get().isVisible(component) }

    @Override
    String[] getElementsIds(String expr) { EvaluatorHolder.get().elementsId(expr) }

    @Override
    String getLabel(IdSupport component) { EvaluatorHolder.get().label(component) }

    @Override
    void reset(IdSupport component) { EvaluatorHolder.get().reset(component) }

    @Override
    void setFocus(IdSupport component) { EvaluatorHolder.get().focusOn(component) }

    @Override
    void type(String text) { EvaluatorHolder.get().type(text) }

    @Override
    void click(IdSupport component) { EvaluatorHolder.get().click(component, Click.left) }

    @Override
    String getText(IdSupport component) { EvaluatorHolder.get().text(component) }

    @Override
    String getPlaceholder(IdSupport component) { EvaluatorHolder.get().placeholder(component) }

    @Override
    boolean isAvailable(IdSupport component) { EvaluatorHolder.get().available(component) }
}
