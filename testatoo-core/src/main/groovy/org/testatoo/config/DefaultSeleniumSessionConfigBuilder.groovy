package org.testatoo.config

import com.thoughtworks.selenium.Selenium
import org.testatoo.config.lifecycle.EvaluatorListener
import org.testatoo.config.lifecycle.EvaluatorListenerAdapter
import org.testatoo.config.selenium.SeleniumSessionConfigBuilder
import org.testatoo.core.Evaluator
import org.testatoo.core.evaluator.EvaluatorHolder
import org.testatoo.core.evaluator.SeleniumEvaluator

import static org.testatoo.config.Ensure.notNull

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultSeleniumSessionConfigBuilder implements SeleniumSessionConfigBuilder {

    private final List<EvaluatorListener<Selenium>> listeners = new ArrayList<EvaluatorListener<Selenium>>()
    private final DefaultSeleniumSessionConfig seleniumSessionConfig

    private int timeout = 60000
    private String sessionName = Evaluator.class.getName() + ".DEFAULT"

    DefaultSeleniumSessionConfigBuilder(DefaultSeleniumSessionConfig seleniumSessionConfig) {
        this.seleniumSessionConfig = seleniumSessionConfig

        listeners.add(new EvaluatorListenerAdapter<Selenium>() {

            @Override
            public void beforeStart(final Selenium session) {
                EvaluatorHolder.register(new SeleniumEvaluator(sessionName, session))
            }

            @Override
            public void afterStart(Selenium session) {
                session.setTimeout("" + timeout)
            }

            @Override
            public void beforeStop(final Selenium session) {
                EvaluatorHolder.unregister(sessionName)
            }
        });
    }

    @Override
    public SeleniumSessionConfigBuilder withTimeout(int timeoutMilliseconds) {
        this.timeout = timeoutMilliseconds
        return this
    }

    @Override
    public SeleniumSessionConfigBuilder named(String sessionName) {
        this.sessionName = sessionName
        return this
    }

    @Override
    public SeleniumSessionConfigBuilder add(EvaluatorListener<Selenium> listener) {
        notNull(listener, "Selenium session listener")
        listeners.add(listener)
        return this
    }

    void fireAfterStart(Selenium session) {
        for (EvaluatorListener<Selenium> listener : listeners)
            listener.afterStart(session)
    }

    void fireBeforeStart(Selenium session) {
        for (EvaluatorListener<Selenium> listener : listeners)
            listener.beforeStart(session)
    }

    void fireBeforeStop(Selenium session) {
        for (EvaluatorListener<Selenium> listener : listeners)
            listener.beforeStop(session)
    }

    void fireAfterStop(Selenium session) {
        for (EvaluatorListener<Selenium> listener : listeners)
            listener.afterStop(session)
    }
}
