package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

interface EventListenerSorter {
    List<EventListener> sort(List<EventListener> listeners)
}
