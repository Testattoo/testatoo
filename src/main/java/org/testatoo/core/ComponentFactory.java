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

import com.google.common.base.Function;
import org.testatoo.core.component.Component;

import java.lang.reflect.InvocationTargetException;

import static org.testatoo.core.By.$;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class ComponentFactory {

    public static <T extends Component> T component(Class<T> componentType, String id) {
        if (id.startsWith("$"))
            return component(componentType, $(id));
        else
            return component(componentType, By.id(id));
    }

    public static <T extends Component> T component(Class<T> componentType, By by) {
        return loadComponent(componentType, by.id());
    }

    public static Selection<? extends Component> components(By by) {
        return components(Component.class, by);
    }

    public static <T extends Component> Selection<T> components(final Class<T> componentType, By by) {
        return ListSelection.from(by.ids()).transform(new Function<String, T>() {
            @Override
            public T apply(String id) {
                return loadComponent(componentType, id);
            }
        });
    }

    private static Evaluator evaluator() {
        return EvaluatorHolder.get();
    }

    private static <T extends Component> T loadComponent(Class<T> componentType, String id) {
        Class cmpType = componentType;

        try {
            return (T) cmpType.getConstructor(String.class).newInstance(id);
        } catch (Exception e) {
            if (e.getCause() instanceof ComponentException)
                throw (ComponentException) e.getCause();
            try {
                return componentType.getConstructor(Evaluator.class, String.class).newInstance(evaluator(), id);
            } catch (InvocationTargetException ite) {
                throw new ComponentException(ite.getTargetException().getMessage(), ite.getTargetException());
            } catch (Exception e1) {
                throw new ComponentException(e1.getMessage(), e1);
            }
        }
    }

}
