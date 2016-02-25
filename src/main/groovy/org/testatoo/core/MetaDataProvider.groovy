package org.testatoo.core

import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
interface MetaDataProvider {

    MetaInfo getMetaInfo(Component c)
}