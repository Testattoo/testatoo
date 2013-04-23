/**
 * Copyright (C) 2013 Ovea <dev@testatoo.org>
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
        assertThat(checkBox.is(not(checked())));

        assertThat(checkBox.has(label()).equalsTo("Check me out"));
    }

    @Test
    public void test_link() {
        open("/index.html");

        Link link = new Link("link");
//        assertThat(link.is(enabled()));
        assertThat(link.is(visible()));

//        assertThat(link.has(text("Link to index")));
//        assertThat(link.has(reference("/index.html")));


    }






//        Link link = new Link(id);
//        Panel panel = new Panel(id);
//        PasswordField passwordField = new PasswordField(id);
//        Radio radio = new Radio(id);
//        TextField textField = new TextField(id);



}
