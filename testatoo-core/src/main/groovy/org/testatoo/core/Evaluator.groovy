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
package org.testatoo.core

import org.testatoo.core.component.Component

public interface Evaluator {

    String DEFAULT_NAME = Evaluator.class.getName() + ".DEFAULT";

    /**
     * @return The implementation used by this Evaluator. This can be useful to recover the underlying implementation for specific cases
     */
    Object getImplementation();

    String getName()

    void open(String url)

    boolean isEnabled(Component component)

    boolean isVisible(Component component)

    Boolean isChecked(Component component);

    String[] getElementsIds(String expr)

    String getLabel(Component component)

    void reset(Component component)

    void setFocus(Component component)

    void type(String text)

    void click(Component component)

    String getText(Component component)

    String getPlaceholder(Component component)

    boolean isAvailable(Component component)

    String getType(String id)

    String getTitle(Component component)

    String getReference(Component component)
}