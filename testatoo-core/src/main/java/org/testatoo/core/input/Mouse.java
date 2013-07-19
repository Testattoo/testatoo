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
package org.testatoo.core.input;

import org.testatoo.core.EvaluatorHolder;
import org.testatoo.core.component.Component;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public abstract class Mouse {

    /**
     * To click on the component
     *
     * @param component the target component to click
     */
    public static void clickOn(Component component) {
        EvaluatorHolder.get().click(component, Click.left);
    }

    /**
     * To right click on the component
     *
     * @param component the target component to right click
     */
    public static void rightClickOn(Component component) {
        EvaluatorHolder.get().click(component, Click.right);
    }

    /**
     * To double-click on the component
     *
     * @param component the target component to double-click
     */
    public static void doubleClickOn(Component component) {
        EvaluatorHolder.get().doubleClick(component);
    }

    /**
     * To move the mouse cursor over the component
     *
     * @param component the target component to mouse over
     */
    public static void mouseOverOn(Component component) {
        EvaluatorHolder.get().mouseOver(component);
    }


    /**
     * To move the mouse cursor out of the component
     *
     * @param component the target component to mous out
     */
    public static void mouseOutOf(Component component) {
        EvaluatorHolder.get().mouseOut(component);
    }

    /**
     * To start Drag a component
     *
     * @param component the component on wich we want to drag
     * @return Dragger and object to perform the drag and drop
     */
    public static Dragger drag(Component component) {
        return new Dragger(component);
    }
}
