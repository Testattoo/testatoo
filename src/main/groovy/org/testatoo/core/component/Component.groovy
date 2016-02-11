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
package org.testatoo.core.component

import org.hamcrest.Matcher
import org.testatoo.core.ComponentException
import org.testatoo.core.IdProvider
import org.testatoo.core.MetaInfo
import org.testatoo.core.Testatoo
import org.testatoo.core.internal.Identifiers
import org.testatoo.core.internal.jQueryIdProvider
import org.testatoo.core.support.Clickable
import org.testatoo.core.support.Draggable
import org.testatoo.core.support.IDragBuilder

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Component implements Clickable, Draggable {

    final Queue<Matcher> BLOCKS = new LinkedList<>()

    CachedMetaData meta = new CachedMetaData()

    Component() {}

    Component(IdProvider idProvider) {
        this()
        meta = new CachedMetaData(idProvider: idProvider)
    }

    String getId() throws ComponentException { meta.getMetaInfo(this).id }

    boolean isEnabled() {
        throw new ComponentException('Unsupported Operation')
    }

    boolean isDisabled() {
        throw new ComponentException('Unsupported Operation')
    }

    boolean isAvailable() {
        throw new ComponentException('Unsupported Operation')
    }

    boolean isMissing() {
        throw new ComponentException('Unsupported Operation')
    }

    boolean isHidden() {
        throw new ComponentException('Unsupported Operation')
    }

    boolean isVisible() {
        throw new ComponentException('Unsupported Operation')
    }

    boolean contain(Component... components) {
        throw new ComponentException('Unsupported Operation')
    }

    boolean display(Component... components) {
        throw new ComponentException('Unsupported Operation')
    }

    @Override
    void click() {
        throw new ComponentException('Unsupported Operation')
    }

    @Override
    void rightClick() {
        throw new ComponentException('Unsupported Operation')
    }

    @Override
    void doubleClick() {
        throw new ComponentException('Unsupported Operation')
    }

    @Override
    IDragBuilder drag() {
        throw new ComponentException('Unsupported Operation')
    }

    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false
        Component component = (Component) o
        if (id != component.id) return false
        return true
    }

    @Override
    int hashCode() { id.hashCode() }

    @Override
    String toString() {
        String str
        try {
            str = id
        } catch (ComponentException ignored) {
            str = meta.metaInfo
        }
        getClass().simpleName + ":${str}"
    }

    Object asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            Component c = (Component) clazz.newInstance()
            c.meta = this.meta
            return c
        }
        return super.asType(clazz)
    }

    protected <T extends Component> List<T> find(String expression, Class<T> type = Component) {
        Testatoo.config.evaluator.getMetaInfo("\$('#${id}').find('${expression}')").collect { it.asType(type) } as List<T>
    }

    protected static <T extends Component> List<T> findjs(String expression, Class<T> type = Component) {
        Testatoo.config.evaluator.getMetaInfo(expression).collect { it.asType(type) } as List<T>
    }

    static class CachedMetaData {

        @Delegate
        private MetaInfo metaInfo

        IdProvider idProvider

        MetaInfo getMetaInfo(Component c) {
            if (!metaInfo) {
                MetaInfo info = idProvider.getMetaInfos()[0]
                if (c.class != Component) {
                    String identifyingExpr = Identifiers.getIdentifyingExpression(c.class)
                    if (!(Testatoo.config.evaluator.check(info.id, identifyingExpr))) {
                        Class<Component> type = Testatoo.config.componentTypes.find {
                            Testatoo.config.evaluator.check(info.id, Identifiers.getIdentifyingExpression(it))
                        }
                        throw new ComponentException("Expected a ${c.class.simpleName} for component with id '${info.id}', but was: ${type?.simpleName ?: 'unknown'}")
                    }
                }
                metaInfo = info
            }
            return metaInfo
        }
    }

    static Component $(String jQuery, long timeout = 2000) { new Component(new jQueryIdProvider(jQuery, timeout, true)) }
}
