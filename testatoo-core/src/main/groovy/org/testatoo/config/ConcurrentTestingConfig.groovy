package org.testatoo.config

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public interface ConcurrentTestingConfig {

    void threads(int nThreads)

    void distributeTestsAmongst(String... evaluatorNames)
}
