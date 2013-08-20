package org.testatoo.core

import groovy.transform.Immutable

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-08-20
 */
@Immutable
class MetaInfo {
    String type
    String node
    String id

    @Override
    String toString() { "${type} ${id} (${node})" }
}
