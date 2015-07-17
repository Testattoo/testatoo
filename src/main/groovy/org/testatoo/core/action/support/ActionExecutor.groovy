package org.testatoo.core.action.support

import org.testatoo.core.action.Action

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
interface ActionExecutor {
    void execute(Action action)
}
