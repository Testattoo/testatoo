package org.testatoo.core

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class GroovyExtensions {

    public static boolean asBoolean(Block block) {
        Testatoo.run(block)
        return true
    }

}
