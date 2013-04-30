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
package org.testatoo.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.testatoo.config.annotation.TestatooModules;
import org.testatoo.config.junit.TestatooJunitRunner;
import org.testatoo.core.component.*;
import org.testatoo.html.conf.Module;

import static org.junit.Assert.assertEquals;
import static org.testatoo.core.ComponentFactory.component;
import static org.testatoo.core.ComponentFactory.components;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(TestatooJunitRunner.class)
@TestatooModules(Module.class)
public class ComponentFactoryTest extends Testatoo {

    @Test
    public void can_obtain_component_through_the_factory() {
        open("/index.html");

        Button button = component(Button.class, By.id("button"));
        assertThat(button.is(visible()));

        Selection<Button> buttons = components(Button.class, By.$(".btn"));
        assertEquals(buttons.size(), 4);

        CheckBox checkBox = component(CheckBox.class, By.id("checkbox"));
        assertThat(checkBox.is(visible()));

        ComboBox comboBox;

        DropDown dropDown;

        Link link = component(Link.class, By.id("link"));
        assertThat(link.is(visible()));

        Panel panel = component(Panel.class, By.id("panel"));
        assertThat(panel.is(visible()));

        PasswordField passwordField = component(PasswordField.class, By.id("password_field"));
        assertThat(passwordField.is(visible()));

        Radio radio = component(Radio.class, By.id("radio"));
        assertThat(radio.is(visible()));

        TextField textField = component(TextField.class, By.id("text_field"));
        assertThat(textField.is(visible()));
    }


}
