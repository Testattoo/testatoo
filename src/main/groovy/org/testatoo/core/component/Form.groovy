package org.testatoo.core.component

import org.testatoo.core.Component
import org.testatoo.core.support.Resettable
import org.testatoo.core.support.Submissible
import org.testatoo.core.support.ValiditySupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
abstract class Form extends Component implements ValiditySupport, Resettable, Submissible {}
