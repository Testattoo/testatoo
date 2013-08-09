package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

class EventListener {

    final int priority

    EventListener(Priority p) {
        this.priority = p.next
    }

    void on(Event event) throws Throwable {
        switch (event) {
            case Event.START:
                onStart();
                break;
            case Event.STOP:
                onStop();
                break;
        }
    }

    void onStart() throws Throwable {
    }

    void onStop() throws Throwable {
    }
}