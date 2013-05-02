package com.guestfull.dashboard.web;

import org.testatoo.core.component.Button;

import static org.testatoo.core.By.$;
import static org.testatoo.core.ComponentFactory.component;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class DashboardFactory {

    public static Button logout_button() {
        return component(Button.class, $("[data-role=logout]:visible"));
    }
}
