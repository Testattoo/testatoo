/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.config.testatoo;

import com.thoughtworks.selenium.Selenium;
import org.testatoo.config.lifecycle.EvaluatorListener;
import org.testatoo.config.lifecycle.EvaluatorListenerAdapter;
import org.testatoo.config.selenium.SeleniumSessionConfigBuilder;
import org.testatoo.core.Evaluator;
import org.testatoo.core.evaluator.EvaluatorHolder;
import org.testatoo.html.evaluator.selenium.SeleniumEvaluator;

import java.util.ArrayList;
import java.util.List;

import static org.testatoo.config.testatoo.Ensure.notNull;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultSeleniumSessionConfigBuilder implements SeleniumSessionConfigBuilder {

    private final List<EvaluatorListener<Selenium>> listeners = new ArrayList<EvaluatorListener<Selenium>>();
    private final DefaultSeleniumSessionConfig seleniumSessionConfig;

    private int timeout = 60000;
    private String sessionName = Evaluator.DEFAULT_NAME;

    DefaultSeleniumSessionConfigBuilder(DefaultSeleniumSessionConfig seleniumSessionConfig) {
        this.seleniumSessionConfig = seleniumSessionConfig;
        listeners.add(new EvaluatorListenerAdapter<Selenium>() {

            @Override
            public void beforeStart(final Selenium session) {
                EvaluatorHolder.register(new SeleniumEvaluator(sessionName, session));
            }

            @Override
            public void afterStart(Selenium session) {
                session.setTimeout("" + timeout);
            }

            @Override
            public void beforeStop(final Selenium session) {
                EvaluatorHolder.unregister(sessionName);
            }
        });
    }

    @Override
    public SeleniumSessionConfigBuilder withTimeout(int timeoutMilliseconds) {
        this.timeout = timeoutMilliseconds;
        return this;
    }

    @Override
    public SeleniumSessionConfigBuilder named(String sessionName) {
        this.sessionName = sessionName;
        return this;
    }

    @Override
    public SeleniumSessionConfigBuilder add(EvaluatorListener<Selenium> listener) {
        notNull(listener, "Selenium session listener");
        listeners.add(listener);
        return this;
    }

    void fireAfterStart(Selenium session) {
        for (EvaluatorListener<Selenium> listener : listeners)
            listener.afterStart(session);
    }

    void fireBeforeStart(Selenium session) {
        for (EvaluatorListener<Selenium> listener : listeners)
            listener.beforeStart(session);
    }

    void fireBeforeStop(Selenium session) {
        for (EvaluatorListener<Selenium> listener : listeners)
            listener.beforeStop(session);
    }

    void fireAfterStop(Selenium session) {
        for (EvaluatorListener<Selenium> listener : listeners)
            listener.afterStop(session);
    }
}
