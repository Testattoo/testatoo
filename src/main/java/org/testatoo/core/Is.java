package org.testatoo.core;

import org.testatoo.core.component.Component;
import org.testatoo.core.state.State;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Is implements Runnable {

    private final State state;
    private final Component component;

    public Is(State state, Component component) {
        this.state = state;
        this.component = component;
    }

    @Override
    public void run() {
        state.check(component);
    }
}
