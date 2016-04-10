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
package org.testatoo.core

import org.testatoo.core.component.Component
import org.testatoo.core.internal.CachedMetaData
import org.testatoo.core.internal.jQueryIdProvider

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Components<T extends Component> extends AbstractList<T> implements List<T> {

    private final MetaDataProvider _meta
    private final Class<T> _type;
    private List<T> _components

    public Components(Class<T> type, MetaDataProvider meta) {
        this._meta = meta
        this._type = type
    }

    private List<T> getComponents() {
        if (_components == null) {
            _components = _meta.metaInfos().collect {
                new Component(new CachedMetaData(idProvider: new jQueryIdProvider("#${it.id}", false))).asType(_type)
            } as List<T>
        }
        return _components
    }

    def <T extends Component> Components<T> of(Class<T> type) {
        return new Components(type, _meta)
    }

    // DELEGATES
    @Override
    int size() { components.size() }

    @Override
    boolean isEmpty() { components.isEmpty() }

    @Override
    boolean contains(Object o) { components.contains(o) }

    @Override
    Iterator<T> iterator() { components.iterator() }

    @Override
    Object[] toArray() { components.toArray() }

    def <T> T[] toArray(T[] a) { components.toArray(a) }

    @Override
    T get(int index) { components.get(index) }

    // UNSUPPORTED
    @Override
    boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException()
    }

    @Override
    boolean add(Component component) {
        throw new UnsupportedOperationException()
    }

    @Override
    boolean remove(Object o) {
        throw new UnsupportedOperationException()
    }

    @Override
    boolean addAll(Collection c) {
        throw new UnsupportedOperationException()
    }

    @Override
    boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException()
    }

    @Override
    boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException()
    }

    @Override
    void clear() {
        throw new UnsupportedOperationException()
    }

}
