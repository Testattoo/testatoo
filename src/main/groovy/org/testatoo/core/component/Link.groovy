package org.testatoo.core.component

import org.testatoo.core.Component
import org.testatoo.core.support.TextSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class Link extends Component implements TextSupport {

    abstract String getReference()

}
