package org.testatoo.config.annotation

import java.lang.annotation.*;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Implementation {
}