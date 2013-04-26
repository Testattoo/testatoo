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
package org.testatoo.html;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testatoo.config.annotation.TestatooModules;
import org.testatoo.config.junit.TestatooJunitRunner;
import org.testatoo.core.Testatoo;
import org.testatoo.core.component.*;
import org.testatoo.html.conf.Module;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(Module.class)
public class ComponentTest extends Testatoo {

    @Test
    public void test_button() {
        open("/index.html");

        // input type=button
        Button button = new Button("button");
        assertThat(button.is(enabled()));
        assertThat(button.is(visible()));

        assertThat(button.has(text()).equalsTo("Button"));

        // input type=submit
        button = new Button("submit");
        assertThat(button.is(disabled()));
        assertThat(button.is(visible()));

        assertThat(button.has(text()).equalsTo("Submit"));

        // input type=reset
        button = new Button("reset");
        assertThat(button.is(enabled()));
        assertThat(button.is(visible()));

        assertThat(button.has(text()).equalsTo("Reset"));

        // button element
        button = new Button("btn");
        assertThat(button.is(enabled()));
        assertThat(button.is(visible()));

        assertThat(button.has(text()).equalsTo("My Button Text"));
    }

    @Test
    public void test_checkbox() {
        open("/index.html");

        CheckBox checkBox = new CheckBox("checkbox");
        assertThat(checkBox.is(enabled()));
        assertThat(checkBox.is(visible()));
//        assertThat(checkBox.is(not(checked())));

        assertThat(checkBox.has(label()).contains("Check me out"));
    }

    @Test
    public void test_link() {
        open("/index.html");

        Link link = new Link("link");
        //TODO Why is not enabled !!!!
//        assertThat(link.is(enabled()));
        assertThat(link.is(visible()));

        assertThat(link.has(text()).equalsTo("Link to index"));
        assertThat(link.has(reference()).contains("/index.html"));
    }

    @Test
    public void test_panel() {
        open("/index.html");

        Panel panel = new Panel("panel");
        assertThat(panel.is(enabled()));
        assertThat(panel.is(visible()));

        assertThat(panel.has(title()).equalsTo(""));
    }

    @Test
    public void test_passwordField() {
        open("/index.html");

        PasswordField passwordField = new PasswordField("password_field");

        assertThat(passwordField.is(enabled()));
        assertThat(passwordField.is(visible()));

        assertThat(passwordField.has(label()).equalsTo("Password"));
        assertThat(passwordField.has(value()).equalsTo("?art"));
    }

    @Test
    public void test_radio() {
        open("/index.html");

        Radio radio = new Radio("radio");
        assertThat(radio.is(enabled()));
        assertThat(radio.is(visible()));
        assertThat(radio.is(checked()));

        assertThat(radio.has(label()).contains("Radio label"));
    }

    @Test
    public void test_textField() {
        open("/index.html");

        TextField textField = new TextField("text_field");

        assertThat(textField.is(disabled()));
        assertThat(textField.is(visible()));

        assertThat(textField.has(label()).equalsTo("Email"));
        assertThat(textField.has(value()).equalsTo(""));
    }

    @Test
    public void test_page() {
        open("/index.html");

        assertThat(page().has(title()).equalsTo("Testatoo Rocks"));

        assertThat(page().contains(
                new Button("button"),
                new TextField("text_field"),
                new Radio("radio")
        ));
    }

}
