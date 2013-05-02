package com.guestfull.dashboard.web;

import com.guestfull.dashboard.web.component.View;

import static org.testatoo.core.By.$;
import static org.testatoo.core.ComponentFactory.component;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Views {

    public static View login_view() {
        return component(View.class, $("#login_view"));
    }

    public static View dashboard_view() {
        return component(View.class, $("#dashboard_view"));
    }
}
