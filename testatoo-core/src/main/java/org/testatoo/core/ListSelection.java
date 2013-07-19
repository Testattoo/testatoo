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
package org.testatoo.core;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
public final class ListSelection<T> implements Selection<T> {

    @SuppressWarnings({"unchecked"})
    private static final ListSelection EMPTY = new ListSelection(new ArrayList());

    private final List<T> list;

    public static <T> Selection<T> compose(Iterable<? extends Iterable<? extends T>> collections) {
        return new ListSelection<T>(collections);
    }

    public static <T> Selection<T> compose(Iterable<? extends T>... collections) {
        return compose(Arrays.asList(collections));
    }

    @SuppressWarnings({"unchecked"})
    public static <T> Selection<T> from(Iterable<? extends T> collection) {
        return compose(Arrays.asList(collection));
    }

    public static <T> Selection<T> of(T... elements) {
        return from(Arrays.asList(elements));
    }

    @SuppressWarnings({"unchecked"})
    public static <T> Selection<T> empty() {
        return EMPTY;
    }

    private ListSelection(Iterable<? extends Iterable<? extends T>> collections) {
        this.list = Lists.newArrayList(Iterables.concat(collections));
    }

    public final boolean isEmpty() {
        return list.isEmpty();
    }

    public final int size() {
        return list.size();
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public boolean containsAll(Iterable<?> other) {
        for (Object o : other)
            if (!list.contains(o))
                return false;
        return true;
    }

    public T find(Predicate<? super T> predicate) throws NoSuchElementException {
        return Iterables.find(this, predicate);
    }

    public Selection<T> filter(Predicate<? super T> predicate) {
        return ListSelection.from(Iterables.filter(this, predicate));
    }

    public <O> Selection<O> transform(Function<? super T, ? extends O> function) {
        return ListSelection.from(Iterables.transform(this, function));
    }

    @Override
    public <O> Selection<O> transform(Class<? extends O> type) {
        return filter(Predicates.instanceOf(type)).transform(Selector.to(type));
    }

    public T unique() throws NoSuchElementException {
        if (size() != 1)
            throw new NoSuchElementException("Selection size is " + size());
        return list.iterator().next();
    }

    public final Iterator<T> iterator() {
        return list.iterator();
    }

    public T get(int index) {
        if (index >= size())
            throw new NoSuchElementException("Position " + index + " overlaps list size: " + size());
        return list.get(index);
    }

    public Selection<T> sort(Comparator<? super T> comparator) {
        ArrayList<? extends T> list = new ArrayList<T>(this.list);
        Collections.sort(list, comparator);
        return ListSelection.from(list);
    }

    public T first() {
        if (list.isEmpty())
            throw new NoSuchElementException("List is empty");
        return get(0);
    }

    public T last() {
        if (list.isEmpty())
            throw new NoSuchElementException("List is empty");
        return get(size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSelection that = (ListSelection) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}