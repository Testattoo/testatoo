package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class Ensure {

    private Ensure() {
    }

    static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message + " cannot be null !")
        }
    }

    static void require(Object o, String message) {
        if (o == null) {
            throw new IllegalStateException(message + " has not been set !")
        }
    }

    public static void notEmpty(String message, Iterable<?> it) {
        if (!it.iterator().hasNext()) {
            throw new IllegalArgumentException(message + " cannot be empty !")
        }
    }

    public static void notEmpty(String message, Object[] it) {
        if (it.length == 0) {
            throw new IllegalArgumentException(message + " cannot be empty !")
        }
    }
}