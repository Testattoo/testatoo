package org.testatoo.core

import org.testatoo.core.component.Component

import java.lang.annotation.Annotation

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Identifiers {

    static Map factories = [
        (IdentifiedByJs): { IdentifiedByJs annotation -> return annotation.value() },
        (IdentifiedByCss): { IdentifiedByCss annotation -> return "it.is('${annotation.value()}')" },
    ]

    static boolean hasIdentifier(Class<? extends Component> c) {
        return c.annotations.find { it.annotationType().isAnnotationPresent(Identifier) }
    }

    static String getIdentifyingExpression(Class<? extends Component> c) {
        Annotation annotation = c.annotations.find { it.annotationType().isAnnotationPresent(Identifier) }
        if (!annotation) {
            throw new IllegalStateException("Missing @Identifier annotation on type " + c.name)
        }
        Closure<String> handler = factories[annotation.annotationType()]
        if (!handler) {
            throw new IllegalStateException("Missing handler for annotation type " + annotation.annotationType().name)
        }
        return handler.call(annotation)
    }


}
