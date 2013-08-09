package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

enum Event {

    START(EventListenerOrdering.BY_PRIORITY),
    STOP(EventListenerOrdering.BY_PRIORITY_REVERSE)

    private final EventListenerOrdering ordering

    private Event(EventListenerOrdering ordering) {
        this.ordering = ordering
    }

    public EventListenerOrdering ordering() {
        return ordering
    }
}
