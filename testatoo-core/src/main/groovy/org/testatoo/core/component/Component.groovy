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

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Component {

    private Map<Class<? extends Property>, PropertyEvaluator> _supportedProperties = new IdentityHashMap<>()
    Meta _meta
    String type

    Component() {
        type = Type.UNDEFINED
        _meta = new Meta()
    }

    Component(Evaluator evaluator, IdProvider idProvider) {
        type = Type.UNDEFINED
        _meta = new Meta(
            evaluator: evaluator,
            idProvider: idProvider
        )
    }

    void support(Class<? extends Property>... types) {
        _supportedProperties = (types as List).collectEntries { [(it): PropertyEvaluator.DEFAULT] }
    }

    void type(String type) { this.type = type }

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

    boolean supports(Property property) { _supportedProperties.containsKey(property.class) }

    static class Meta {
        String _id
        Evaluator evaluator
        IdProvider idProvider

        String getId(Component c) throws ComponentException {
            if (!_id) {
                String _id = idProvider.getValue(evaluator)
                String t = evaluator.getType(_id)
                if (t != c.type) {
                    throw new ComponentException('Expected type: ' + c.type + ' for component ' + c + ' but was:' + t)
                }
                this._id = _id
            }
            return _id
        }
    }

    /**
     * @author David Avenante (d.avenante@gmail.com)
     */
    static class Type {

        static final String ALERTBOX = 'Alertbox'
        static final String BUTTON = 'Button'
        static final String CHECKBOX = 'CheckBox'
        static final String COMBOBOX = 'ComboBox'
        static final String DROPDOWN = 'DropDown'
        static final String IMAGE = 'Image'
        static final String LINK = 'Link'
        static final String LISTBOX = 'ListBox'
        static final String PANEL = 'Panel'
        static final String PASSWORDFIELD = 'PasswordField'
        static final String RADIO = 'Radio'
        static final String TEXTFIELD = 'TextField'
        static final String DATAGRID = 'DATAGRID'
        static final String CELL = 'Cell'
        static final String COLUMN = 'Column'
        static final String ROW = 'Row'
        static final String UNDEFINED = 'Undefined'

    }
}
