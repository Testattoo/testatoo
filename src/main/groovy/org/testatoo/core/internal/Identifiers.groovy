/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core.internal

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner
import org.testatoo.core.ByCss
import org.testatoo.core.ComponentException
import org.testatoo.core.Identifier
import org.testatoo.core.component.Component

import java.lang.annotation.Annotation

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Identifiers {

    private static scanner = new FastClasspathScanner('org.testatoo.bundle')
    private static Map<Class, List<Class>> cachedComponents = new HashMap<>()

    static Map factories = [
        (ByCss): { ByCss annotation -> return "it.is('${annotation.value()}')" }
    ]

    static boolean hasIdentifier(Class<? extends Component> c) {
        return c.annotations.find { it.annotationType().isAnnotationPresent(Identifier) }
    }

    static String getIdentifyingExpression(Class<? extends Component> c) {
        Annotation annotation = c.declaredAnnotations.find { it.annotationType().isAnnotationPresent(Identifier) }
        if (!annotation) {
            annotation = c.annotations.find { it.annotationType().isAnnotationPresent(Identifier) }
        }
        if (!annotation) {
            throw new ComponentException("Missing @Identifier annotation on type " + c.name)
        }
        Closure<String> handler = factories[annotation.annotationType()]
        if (!handler) {
            throw new ComponentException("Missing handler for annotation type " + annotation.annotationType().name)
        }
        return handler.call(annotation)
    }

    static Map<Class, String> findSelectorsFor(Class clazz) {
        Map<Class, String> selectors = new HashMap<>()

        if(!cachedComponents.get(clazz)) {
            List<Class> matchingClasses = new ArrayList<>();
            scanner.matchSubclassesOf(clazz, { c -> matchingClasses.add(c) }).scan()
            cachedComponents.put(clazz, matchingClasses)
        }

        cachedComponents.get(clazz).each {
            Annotation annotation = it.declaredAnnotations.find { it.annotationType().isAnnotationPresent(Identifier) }
            selectors.put(it, annotation.value())
        }
        selectors
    }
}
