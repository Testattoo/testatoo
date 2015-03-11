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
package org.testatoo.core.property

import org.testatoo.core.property.matcher.PropertyMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Properties {

    // Properties
    static final Placeholder placeholder = new Placeholder()
    static final PropertyMatcher placeholder(String expected) {placeholder.equalsTo(expected)}

    static final Label label = new Label()
    static final PropertyMatcher label(String expected) { label.equalsTo(expected) }

    static final Text text = new Text()
    static final PropertyMatcher text(String expected) { text.equalsTo(expected) }

    static final Value value = new Value()
    static final PropertyMatcher value(String expected) { value.equalsTo(expected) }

    static final Reference reference = new Reference()
    static final PropertyMatcher reference(String expected) { reference.equalsTo(expected) }

    static final Source source = new Source()
    static final PropertyMatcher source(String expected) { source.equalsTo(expected) }

    static final Title title = new Title()
    static final PropertyMatcher title(String expected) { title.equalsTo(expected) }

    static final Items items = new Items()
    static final PropertyMatcher items(String... expected) { items.equalsTo(expected) }

    static final GroupItems groupItems = new GroupItems()
    static final PropertyMatcher groupItems(String... expected) { groupItems.equalsTo(expected) }
    static final GroupItemsSize groupItemsSize = new GroupItemsSize()

    static final VisibleItemsSize visibleItemsSize = new VisibleItemsSize()

    static final ColumnSize columnSize = new  ColumnSize()

    static final Size size = new Size()
    static final PropertyMatcher size(Integer expected) { size.equalsTo(expected) }

    static final Minimum minimum = new Minimum()
    static final PropertyMatcher minimun(Number... expected) { minimum.equalsTo(expected) }
    static final PropertyMatcher minimun(String... expected) { minimum.equalsTo(expected) }

    static final Maximum maximum = new Maximum()
    static final PropertyMatcher maximum(Number... expected) { maximum.equalsTo(expected) }
    static final PropertyMatcher maximum(String... expected) { maximum.equalsTo(expected) }

    static final Step step = new Step()
    static final PropertyMatcher step(Number... expected) { step.equalsTo(expected) }

    static final Pattern pattern = new Pattern()
    static final PropertyMatcher pattern(String... expected) { pattern.equalsTo(expected) }

    static final SelectedItems selectedItems = new SelectedItems()
    static final PropertyMatcher selectedItems(String... expected) { selectedItems.equalsTo(expected) }

    static final ParagraphSize paragraphSize = new ParagraphSize()
    static final ArticleSize articleSize = new ArticleSize()
}
