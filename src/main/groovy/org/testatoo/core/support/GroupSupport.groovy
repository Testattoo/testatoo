package org.testatoo.core.support

import org.testatoo.core.component.Group

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface GroupSupport {

    List<Group> groups()

    Group group(String value)
}
