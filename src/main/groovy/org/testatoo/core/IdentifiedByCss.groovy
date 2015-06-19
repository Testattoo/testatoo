package org.testatoo.core

import java.lang.annotation.*

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Identifier
@interface IdentifiedByCss {
    String value()
}
