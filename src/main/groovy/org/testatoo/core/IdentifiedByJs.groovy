package org.testatoo.core

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Identifier
@interface IdentifiedByJs {
    String value()
}
