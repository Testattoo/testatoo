/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
package org.testatoo.bundle.html5.component

import org.testatoo.core.component.Component
import org.testatoo.core.ComponentException
import org.testatoo.core.support.IDragBuilder

import static org.testatoo.core.Testatoo.getConfig
import static org.testatoo.core.input.MouseModifiers.DOUBLE
import static org.testatoo.core.input.MouseModifiers.LEFT
import static org.testatoo.core.input.MouseModifiers.RIGHT
import static org.testatoo.core.input.MouseModifiers.SINGLE

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
trait WebElement {

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

    boolean contain(Component... components) {
        List ret = config.evaluator.getJson("\$._contains('${id}', [${components.collect { "'${it.id}'" }.join(', ')}])")
        if (ret) {
            throw new ComponentException("Component ${this} does not contain expected component(s): ${components.findAll { it.id in ret }}");
        }
    }

    boolean display(Component... components) {
        List ret = config.evaluator.getJson("\$._contains('${id}', [${components.collect { "'${it.id}'" }.join(', ')}])")
        if (ret) {
            throw new ComponentException("Component ${this} does not display expected component(s): ${components.findAll { it.id in ret }}");
        } else {
            components.findAll { !it.visible }
        }
    }

    void click() {
        config.evaluator.click(id, [LEFT, SINGLE])
    }

    void rightClick() {
        config.evaluator.click(id, [RIGHT, SINGLE])
    }

    void doubleClick() {
        config.evaluator.click(id, [LEFT, DOUBLE])
    }

    IDragBuilder drag() {
        new DragBuilder(this)
    }

    public static class DragBuilder implements IDragBuilder {
        Component dragged

        public DragBuilder(Component dragged) {
            this.dragged = dragged
        }

        public void on(Component onto) {
            config.evaluator.dragAndDrop(dragged.id, onto.id)
        }
    }
}
