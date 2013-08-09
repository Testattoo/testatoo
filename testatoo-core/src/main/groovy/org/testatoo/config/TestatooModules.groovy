package org.testatoo.config

import java.lang.annotation.*

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * This annotation can be put on your test classes which uses the
 * {@link org.testatoo.config.TestatooJunitRunner} Junit Runner.
 * <p/>
 * It's goal is to define the list of {@link org.testatoo.config.TestatooModule}
 * to load for configuring Testatoo for your test.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
@interface TestatooModules {
    /**
     * Specify which module you want to use for configuring Testatoo. It can
     * be a list of module or only one module, since from one module you can
     * install/import other ones as needed.
     *
     * @return the list of {@link org.testatoo.config.TestatooModule}
     */
    Class<? extends TestatooModule>[] value()
}