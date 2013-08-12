package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

import static org.testatoo.config.EventListenerOrdering.*

enum Event {

    START(BY_PRIORITY),
    STOP(BY_PRIORITY_REVERSE)

    private final EventListenerOrdering ordering

    private Event(EventListenerOrdering ordering) {
        this.ordering = ordering
    }

    public EventListenerOrdering ordering() {
        return ordering
    }
}
