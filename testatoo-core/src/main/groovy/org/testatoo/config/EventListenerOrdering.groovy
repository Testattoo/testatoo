package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

enum EventListenerOrdering implements EventListenerSorter {

    BY_PRIORITY(new Comparator<EventListener>() {
        @Override
        public int compare(EventListener o1, EventListener o2) {
            return o1.priority() > o2.priority() ? 1 : o1.priority() == o2.priority() ? 0 : -1
        }
    }),

    BY_PRIORITY_REVERSE(new Comparator<EventListener>() {
        @Override
        public int compare(EventListener o1, EventListener o2) {
            return o1.priority() > o2.priority() ? -1 : o1.priority() == o2.priority() ? 0 : 1
        }
    });

    private final Comparator<EventListener> comparator

    private EventListenerOrdering(Comparator<EventListener> comparator) {
        this.comparator = comparator
    }

    @Override
    public List<EventListener> sort(List<EventListener> listeners) {
        Collections.sort(listeners, comparator)
        return listeners
    }
}