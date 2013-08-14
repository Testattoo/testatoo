package org.testatoo.core.state

import org.testatoo.core.component.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-08-13
 */
interface StateEvaluator {
    boolean getState(Component c)
}
