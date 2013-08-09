package org.testatoo.config

import org.testatoo.config.lifecycle.EvaluatorListener

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public interface Evaluator<T, E1, E2> {

    /**
     * Add a callback to be executed after the startup of the implementation (Selenium, WebDriver, ...)
     *
     * @param listener The callback
     * @return this
     * @see org.testatoo.config.lifecycle.EvaluatorListener
     */
    E1 add(EvaluatorListener<T> listener)

}