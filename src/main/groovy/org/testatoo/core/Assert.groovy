package org.testatoo.core

import java.lang.annotation.*

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@interface Assert {
    String value()
}
