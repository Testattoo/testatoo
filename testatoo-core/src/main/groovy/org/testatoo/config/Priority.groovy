package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

enum Priority {

    SELENIUM_SERVER(10000),
    IMPLEMENTATION(20000),
    LIFECYCLE(30000),
    CONCURRENT(40000);

    private int start

    private Priority(int start) {
        this.start = start
    }

    public int next() {
        return start++
    }
}