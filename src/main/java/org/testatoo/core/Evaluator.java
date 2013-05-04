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

import org.testatoo.core.component.Component;
import org.testatoo.core.component.TextField;
import org.testatoo.core.input.Click;
import org.testatoo.core.input.Key;
import org.testatoo.core.input.KeyModifier;
import org.testatoo.core.nature.Checkable;
import org.testatoo.core.nature.LabelSupport;
import org.testatoo.core.nature.ReferenceSupport;
import org.testatoo.core.nature.Selectable;
import org.testatoo.core.nature.TextSupport;
import org.testatoo.core.nature.TitleSupport;
import org.testatoo.core.nature.ValueSupport;
import org.testatoo.experimental.dsl.IdSupport;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public interface Evaluator<T> {

    String DEFAULT_NAME = Evaluator.class.getName() + ".DEFAULT";

    /**
     * @return The implementation used by this Evaluator. This can be useful to recover the underlying implementation for specific cases
     */
    T implementation();

    String name();

    /**
     * To open the page.
     *
     * @param url the url corresponding to the page we want to open
     */
    void open(String url);

    /**
     * To know if a given component is visible
     *
     * @param component the component we want to know if visible
     * @return True if the component is visible
     */
    Boolean isVisible(IdSupport component);

    /**
     * To know if a given component is enabled
     *
     * @param component the component we want to know if enabled
     * @return True if the component is enabled
     */
    Boolean isEnabled(Component component);

    /**
     * To know if a given component ise focused
     *
     * @param component the component
     * @return True if the component is focused
     */
    Boolean isFocused(Component component);

    /**
     * To know if a checkable element is checked
     *
     * @param checkable an element that can be check (ex : radioButton, checkBox)
     * @return True if the element is checked
     */
    Boolean isChecked(Checkable checkable);

    /**
     * To get the label of an element with label
     *
     * @param labelSupport an element that can have a associated label
     * @return the label associated to the element
     */
    String label(LabelSupport labelSupport);

    /**
     * To get the value displayed in a field
     *
     * @param valueSupport the field we want the value
     * @return the string displayed in the field
     */
    String value(ValueSupport valueSupport);

    /**
     * To get the text displayed on a TextSupport component
     *
     * @param textSupport component
     * @return the displayed text
     */
    String text(TextSupport textSupport);

    /**
     * To get the title of a given element
     *
     * @param titleSupport an element that can have a title
     * @return the title of the element
     */
    String title(TitleSupport titleSupport);

    /**
     * To get the reference of a given component
     *
     * @param referenceSupport the component
     * @return the reference of the link
     */
    String reference(ReferenceSupport referenceSupport);

    /**
     * To get the choosen values in a dropdown or combobox list
     *
     * @param selectable a Component that represents a list
     * @return the strings selected in the list
     */
    String selectedValue(Selectable selectable);

    /**
     * To get the attribute value of a component
     *
     * @param component a Component
     * @param attribute the attribute name
     * @return the attribute value
     */
    String attribute(Component component, String attribute);

    /**
     * To know if a given container contains a given component
     *
     * @param container an element with container properties (ex : window, panel,..)
     * @param component the component we want to know if contained in the container
     * @return True if the component is contained in the container
     */
    Boolean contains(Component container, Component component);

    /**
     * To get the ids of the html elements corresponding to a given path
     *
     * @param expression to locate the html elements
     * @return the list of ids of the html elements
     */
    String[] elementsId(String expression);

    /**
     * To reset a TextField
     *
     * @param textField the text field to reset
     */
    void reset(TextField textField);

    /**
     * To get the focus on the specified component
     *
     * @param component the component to focus
     */
    void focusOn(Component component);

    /**
     * To type a text
     *
     * @param text the text to type
     */
    void type(String text);

    /**
     * To press a Key
     *
     * @param key the Key to press
     */
    void press(Key key);

    /**
     * To KeyDown a KeyModifier
     *
     * @param keyModifier the keyModifier to keyDown
     */
    void keyDown(KeyModifier keyModifier);

    /**
     * To release a KeyModifier
     *
     * @param keyModifier the keyModifier to release
     */
    void release(KeyModifier keyModifier);

    /**
     * To release all KeyModifier
     */
    void release();

    /**
     * To click on a given component
     *
     * @param component the component we want to click on
     * @param which     button is click
     */
    void click(Component component, Click which);

    /**
     * To double-click on a given component
     *
     * @param component the component we want to double-click on
     */
    void doubleClick(Component component);

    /**
     * To move the mouse cursor over a component
     *
     * @param component the component we want to move the mouse cursor over
     */
    void mouseOver(Component component);

    /**
     * To move the mouse cursor out of a component
     *
     * @param component the component we want to move the mouse cursor out
     */
    void mouseOut(Component component);

    /**
     * To do a "drag and drop" operation
     *
     * @param from the component we drag
     * @param to   the component we drop on
     */
    void dragAndDrop(Component from, Component to);

    String evaluate(String expression);

}
