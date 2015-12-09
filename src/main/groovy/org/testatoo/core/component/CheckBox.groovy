package org.testatoo.core.component

import org.testatoo.core.Component
import org.testatoo.core.support.CheckSupport
import org.testatoo.core.support.Checkable
import org.testatoo.core.support.LabelSupport
import org.testatoo.core.support.UnCheckable

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class CheckBox extends Component implements CheckSupport, LabelSupport, Checkable, UnCheckable {}
