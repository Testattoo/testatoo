package org.testatoo.core.property

import org.testatoo.core.component.Component

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-08-13
 */
interface PropertyEvaluator {

    String getValue(Component c)


    PropertyEvaluator DEFAULT = {} as PropertyEvaluator


}
