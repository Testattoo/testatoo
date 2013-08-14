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
package org.testatoo.core.evaluator

import org.testatoo.core.Evaluator
import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DeferredEvaluator implements Evaluator {

    @Override
    Object getImplementation() { EvaluatorHolder.get().implementation }

    @Override
    String getName() { EvaluatorHolder.get().name }

    @Override
    void open(String url) { EvaluatorHolder.get().open(url) }

    @Override
    boolean isEnabled(Component component) { EvaluatorHolder.get().isEnabled component }

    @Override
    boolean isVisible(Component component) { EvaluatorHolder.get().isVisible component }

    @Override
    Boolean isChecked(Component component) { EvaluatorHolder.get().isChecked component }

    @Override
    String[] getElementsIds(String expr) { EvaluatorHolder.get().getElementsIds expr }

    @Override
    String getLabel(Component component) { EvaluatorHolder.get().getLabel component }

    @Override
    void reset(Component component) { EvaluatorHolder.get().reset component }

    @Override
    void setFocus(Component component) { EvaluatorHolder.get().focus = component }

    @Override
    void type(String text) { EvaluatorHolder.get().type(text) }

    @Override
    void click(Component component) { EvaluatorHolder.get().click component }

    @Override
    String getText(Component component) { EvaluatorHolder.get().getText component }

    @Override
    String getPlaceholder(Component component) { EvaluatorHolder.get().getPlaceholder component }

    @Override
    boolean isAvailable(Component component) { EvaluatorHolder.get().isAvailable component }

    @Override
    String getType(String id) { EvaluatorHolder.get().getType(id) }

    @Override
    String getTitle(Component component) { EvaluatorHolder.get().getTitle(component) }

    @Override
    String getReference(Component component) { EvaluatorHolder.get().getReference(component) }
}
