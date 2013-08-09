package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

class EventListener {

    private final int priority

    EventListener(Priority priority) {
        this.priority = priority.next()
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

    public final int priority() {
        return priority
    }

    void onStart() throws Throwable {
    }

    void onStop() throws Throwable {
    }
}