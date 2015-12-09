package org.testatoo.core.component

import org.testatoo.core.Component
import org.testatoo.core.support.CheckSupport
import org.testatoo.core.support.Checkable
import org.testatoo.core.support.LabelSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class Radio extends Component implements LabelSupport, CheckSupport, Checkable {}
