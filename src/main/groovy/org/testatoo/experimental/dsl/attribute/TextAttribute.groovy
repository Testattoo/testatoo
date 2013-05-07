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
package org.testatoo.experimental.dsl.attribute

import org.testatoo.experimental.dsl.attribute.matcher.ContainingMatcher
import org.testatoo.experimental.dsl.attribute.matcher.EqualsToMatcher
import org.testatoo.experimental.dsl.Evaluator
import org.testatoo.experimental.dsl.IdSupport

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class TextAttribute implements Attribute {
    final Evaluator evaluator

    @Delegate
    private EqualsToMatcher.Matchers eq = EqualsToMatcher.matchers(this)

    @Delegate
    private ContainingMatcher.Matchers contains = ContainingMatcher.matchers(this)

    TextAttribute(Evaluator evaluator) { this.evaluator = evaluator }

    @Override
    String getValue(IdSupport component) { evaluator.getText(component) }

    @Override
    String toString() { "Text" }
}
