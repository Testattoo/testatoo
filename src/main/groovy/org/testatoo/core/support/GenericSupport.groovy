/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core.support

import org.testatoo.core.Component
import org.testatoo.core.ComponentException

import static org.testatoo.core.Testatoo.*
import static org.testatoo.core.input.MouseModifiers.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait GenericSupport implements Clickable, Draggable {

    boolean isEnabled() {
        !disabled
    }

    boolean isDisabled() {
        config.evaluator.check(id, "it.is(':disabled') || !!it.attr('disabled')")
    }

    boolean isAvailable() {
        !missing
    }

    boolean isMissing() {
        try {
            this.meta.idProvider.getMetaInfos()
            return false
        } catch (ComponentException ignored) {
            return true
        }
    }

    boolean isHidden() {
        config.evaluator.check(id, "it.is(':hidden')")
    }

    boolean isVisible() {
        !hidden
    }

    @Override
    void click() {
        config.evaluator.click(id, [LEFT, SINGLE])
    }

    @Override
    void rightClick() {
        config.evaluator.click(id, [RIGHT, SINGLE])
    }

    @Override
    void doubleClick() {
        config.evaluator.click(id, [LEFT, DOUBLE])
    }

    @Override
    IDragBuilder drag() {
        new DragBuilder()
    }

    public static class DragBuilder implements IDragBuilder {

        public void on(Component onto) {
            config.evaluator.dragAndDrop(this.id, onto.id)
        }
    }
}