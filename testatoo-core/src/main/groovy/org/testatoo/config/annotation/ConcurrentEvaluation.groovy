package org.testatoo.config.annotation

import java.lang.annotation.*;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.METHOD, ElementType.TYPE])
@Documented
@Inherited
public @interface ConcurrentEvaluation {
}