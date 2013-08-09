package org.testatoo.config.lifecycle

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * Adpater class of {@link EvaluatorListener}.
 * <p/>
 * We strongly recommend that classes which need to receive callback of
 * object startup extend this adapter class
 * insead of implementing {@link EvaluatorListener}.
 * <br>
 * This will allow us adding more events to the interface without impacting your code.
 *
 * @see EvaluatorListener
 */
class EvaluatorListenerAdapter<T> implements EvaluatorListener<T> {
    @Override
    public void afterStart(T object) {
    }

    @Override
    public void afterStop(T object) {
    }

    @Override
    public void beforeStart(T object) {
    }

    @Override
    public void beforeStop(T object) {
    }
}