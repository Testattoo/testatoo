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
package org.testatoo.core.property

import org.testatoo.core.Evaluator
import org.testatoo.core.component.Component
import org.testatoo.core.property.matcher.ContainingMatcher
import org.testatoo.core.property.matcher.EqualsToMatcher

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class TextProperty extends PropertySkeleton {

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)
    @Delegate
    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)

    TextProperty(Evaluator evaluator) { super(evaluator) }

    @Override
    String value(Component component) { evaluator.getText(component)  }

    @Override
    String toString() { "Text" }
}
