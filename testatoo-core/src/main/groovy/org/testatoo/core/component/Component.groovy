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
package org.testatoo.core.component

import org.testatoo.core.*
import org.testatoo.core.property.Property
import org.testatoo.core.property.PropertyEvaluator
import org.testatoo.core.property.matcher.PropertyMatcher
import org.testatoo.core.state.State
import org.testatoo.core.state.StateEvaluator

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Component {

    private static final PropertyEvaluator DEFAULT_PE = {} as PropertyEvaluator
    private static final StateEvaluator DEFAULT_SE = {} as StateEvaluator

    private Map<Class<? extends Property>, PropertyEvaluator> _supportedProperties = new IdentityHashMap<>()
    private Map<Class<? extends State>, StateEvaluator> _supportedStates = new IdentityHashMap<>()

    Meta _meta = new Meta()
    String type = Type.UNDEFINED

    Component() {}

    Component(Evaluator evaluator, IdProvider idProvider) {
        _meta = new Meta(
            evaluator: evaluator,
            idProvider: idProvider
        )
    }

    String getId() throws ComponentException { _meta.getId(this) }

    Evaluator getEvaluator() { _meta.evaluator }

    Block is(State matcher) { block 'is', matcher }

    Block has(PropertyMatcher matcher) { block 'has', matcher }

    Block click() { Blocks.block "click on ${this}", { evaluator.click(this) } }

    private block(String type, Matcher m) { Blocks.block "matching ${this} ${type} ${m}", { m.matches(this) } }

    @Override
    String toString() { getClass().simpleName + ":${id ?: '<unresolved>'}" }

    Object asType(Class clazz) {
        if (Component.isAssignableFrom(clazz)) {
            Component c = (Component) clazz.newInstance()
            c._meta = this._meta
            return c
        }
        return super.asType(clazz)
    }

    void type(String type) { this.type = type }

    void support(Class<?>... types) {
        for (Class<?> type : types) {
            if (Property.isAssignableFrom(type)) {
                support(type as Class<? extends Property>, DEFAULT_PE)
            } else if (State.isAssignableFrom(type)) {
                support(type as Class<? extends State>, DEFAULT_SE)
            }
        }
    }

    void support(Class<? extends Property> type, PropertyEvaluator e) {
        _supportedProperties.put(type, e)
    }

    void support(Class<? extends State> type, StateEvaluator e) {
        _supportedStates.put(type, e)
    }

    void support(Class<?> type, Closure<?> c) {
        if (Property.isAssignableFrom(type)) {
            _supportedProperties.put(type as Class<? extends Property>, c as PropertyEvaluator)
        } else if (State.isAssignableFrom(type)) {
            _supportedStates.put(type as Class<? extends State>, c as StateEvaluator)
        }
    }

    String getValue(Property property) {
        PropertyEvaluator pe = _supportedProperties.get(property.class)
        if (pe == null) {
            throw new ComponentException("Component ${this} does not support property ${property.class.simpleName}")
        }
        return (pe == DEFAULT_PE ? property.evaluator : pe).getValue(this)
    }

    boolean getState(State state) {
        StateEvaluator se = _supportedStates.get(state.class)
        if (se == null) {
            throw new ComponentException("Component ${this} does not support state ${state.class.simpleName}")
        }
        return (se == DEFAULT_SE ? state.evaluator : se).getState(this)
    }

    static class Meta {
        String _id
        Evaluator evaluator
        IdProvider idProvider

        String getId(Component c) throws ComponentException {
            if (!_id) {
                String _id = idProvider.getValue(evaluator)
                String t = evaluator.getType(_id)
                if (t != c.type) {
                    throw new ComponentException("Expected type '${c.type}' for component ${c.class.simpleName} with id '${_id}' but was '${t}' ")
                }
                this._id = _id
            }
            return _id
        }
    }

}
