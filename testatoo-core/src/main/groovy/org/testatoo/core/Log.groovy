package org.testatoo.core

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-08-14
 */
class Log {

    static boolean testatoo

    static void testatoo(String msg) { if (testatoo) println "[TESTATOO] [${Thread.currentThread().name}] ${msg}" }

    static boolean selenium

    static void selenium(String msg) { if (selenium) println "[SELENIUM] [${Thread.currentThread().name}] ${msg}" }
}
