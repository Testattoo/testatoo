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
package org.testatoo.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testatoo.core.component.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class PropertyTest extends Testatoo {

    private Evaluator evaluator;
    private String id = "myId";

    @Before
    public void setUp() {
        evaluator = mock(Evaluator.class);
        when(evaluator.name()).thenReturn(Evaluator.DEFAULT_NAME);

        EvaluatorHolder.register(evaluator);
    }

    @After
    public void clean() {
        EvaluatorHolder.unregister(Evaluator.DEFAULT_NAME);
    }

    @Test
    public void test_label_support() {
        Radio radio = new Radio(id);
        when(evaluator.label(radio)).thenReturn("MyLabel");

        assertThat(radio.has(label()).equalsTo("MyLabel"));

        try {
            assertThat(radio.has(label()).equalsTo("OtherLabel"));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "Expected label: \"OtherLabel\" but was: \"MyLabel\"");
        }

        Component component = new Component(id);
        try {
            component.has(label()).equalsTo("MyLabel");
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "The component does not support Label");
        }
    }

    @Test
    public void test_value_support() {
        TextField textField = new TextField(id);
        when(evaluator.value(textField)).thenReturn("MyValue");

        assertThat(textField.has(value()).equalsTo("MyValue"));

        try {
            assertThat(textField.has(value()).equalsTo("OtherValue"));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "Expected value: \"OtherValue\" but was: \"MyValue\"");
        }

        Component component = new Component(id);
        try {
            component.has(value()).equalsTo("MyValue");
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "The component does not support Value");
        }
    }

    @Test
    public void test_text_support() {
        Button button = new Button(id);
        when(evaluator.text(button)).thenReturn("MyText");

        assertThat(button.has(text()).equalsTo("MyText"));

        try {
            assertThat(button.has(text()).equalsTo("OtherText"));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "Expected text: \"OtherText\" but was: \"MyText\"");
        }

        Component component = new Component(id);
        try {
            component.has(text()).equalsTo("MyText");
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "The component does not support Text");
        }
    }

    @Test
    public void test_title_support() {
        Panel panel = new Panel(id);
        when(evaluator.title(panel)).thenReturn("MyTitle");

        assertThat(panel.has(title()).equalsTo("MyTitle"));

        try {
            assertThat(panel.has(title()).equalsTo("OtherTitle"));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "Expected title: \"OtherTitle\" but was: \"MyTitle\"");
        }

        Component component = new Component(id);
        try {
            component.has(title()).equalsTo("MyTitle");
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "The component does not support Title");
        }
    }

    @Test
    public void test_reference_support() {
        Link link = new Link(id);
        when(evaluator.reference(link)).thenReturn("http://www.testatoo.org");

        assertThat(link.has(reference()).equalsTo("http://www.testatoo.org"));

        try {
            assertThat(link.has(reference()).equalsTo("http://www.testatoo.net"));
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "Expected reference: \"http://www.testatoo.net\" but was: \"http://www.testatoo.org\"");
        }

        Component component = new Component(id);
        try {
            component.has(reference()).equalsTo("http://www.testatoo.org");
        } catch (AssertionError e) {
            assertEquals(e.getMessage(), "The component does not support Reference");
        }
    }

    @Test
    public void test_length_support() {

    }

    @Test
    public void test_size_support() {

    }

    @Test
    public void test_property_support() {

    }

}
