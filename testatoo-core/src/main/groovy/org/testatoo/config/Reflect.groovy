package org.testatoo.config

import java.lang.reflect.Field;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class Reflect {

    private Reflect() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Object o, String fieldName) {
        try {
            Field f = o.getClass().getDeclaredField(fieldName)
            f.setAccessible(true)
            return (T) f.get(o)
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e.message, e)
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.message, e)
        }
    }
}