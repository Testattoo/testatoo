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
import org.testatoo.core.nature.SizeSupport;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
public interface Selection<T> extends Iterable<T> {

    /**
     * Returns the first element in {@code iterable} that satisfies the given predicate.
     *
     * @throws java.util.NoSuchElementException
     *  if no element in {@code iterable} matches the given predicate
     */
    T find(Predicate<? super T> predicate) throws NoSuchElementException;

    Selection<T> filter(Predicate<? super T> predicate);

    <O> Selection<O> transform(Function<? super T, ? extends O> function);

    <O> Selection<O> transform(Class<? extends O> type);

    T unique() throws NoSuchElementException;

    T first() throws NoSuchElementException;

    T last() throws NoSuchElementException;

    T get(int index) throws NoSuchElementException;

    Selection<T> sort(Comparator<? super T> comparator);

    boolean isEmpty();

    Integer size();

    boolean contains(Object o);

    boolean containsAll(Iterable<?> other);
}