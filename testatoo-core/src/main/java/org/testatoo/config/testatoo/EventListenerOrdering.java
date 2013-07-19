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
package org.testatoo.config.testatoo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

enum EventListenerOrdering implements EventListenerSorter {

    BY_PRIORITY(new Comparator<EventListener>() {
        @Override
        public int compare(EventListener o1, EventListener o2) {
            return o1.priority() > o2.priority() ? 1 : o1.priority() == o2.priority() ? 0 : -1;
        }
    }),

    BY_PRIORITY_REVERSE(new Comparator<EventListener>() {
        @Override
        public int compare(EventListener o1, EventListener o2) {
            return o1.priority() > o2.priority() ? -1 : o1.priority() == o2.priority() ? 0 : 1;
        }
    });

    private final Comparator<EventListener> comparator;

    private EventListenerOrdering(Comparator<EventListener> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<EventListener> sort(List<EventListener> listeners) {
        Collections.sort(listeners, comparator);
        return listeners;
    }
}
