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
package org.testatoo.core;

import org.testatoo.core.evaluator.EvaluatorHolder;

import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class By {

    public abstract String id();

    public abstract String id(Duration duration, Duration frequency);

    public abstract List<String> ids();

    public abstract List<String> ids(Duration duration, Duration frequency);

    public abstract String toString();

    public static By id(final String id) {
        if (id == null)
            throw new IllegalArgumentException("Cannot find component with a null id.");

        return new By() {
            @Override
            public String id() {
                return id(new Duration(2000, MILLISECONDS), new Duration(500, MILLISECONDS));
            }

            @Override
            public String id(Duration duration, Duration frequency) {
                return waitUntilId(id, duration, frequency);
            }

            @Override
            public List<String> ids() {
                return ids(new Duration(2000, MILLISECONDS), new Duration(500, MILLISECONDS));
            }

            @Override
            public List<String> ids(Duration duration, Duration frequency) {
                return Arrays.asList(waitUntilIds(id, duration, frequency));
            }

            @Override
            public String toString() {
                return "by id=" + id;
            }
        };
    }

    public static By $(final String jQueryExpression) {
        return $(jQueryExpression, new Duration(2, SECONDS));
    }

    public static By $(final String jQueryExpression, final Duration duration) {
        if (jQueryExpression == null)
            throw new IllegalArgumentException("Cannot find component when jQueryExpression is null.");

        return new By() {

            @Override
            public String id() {
                return id(duration, new Duration(500, MILLISECONDS));
            }

            @Override
            public String id(Duration duration, Duration frequency) {
                return waitUntilId("jquery:" + jQueryExpression(), duration, frequency);
            }

            @Override
            public List<String> ids() {
                return ids(duration, new Duration(500, MILLISECONDS));
            }

            @Override
            public List<String> ids(Duration duration, Duration frequency) {
                return Arrays.asList(waitUntilIds("jquery:" + jQueryExpression(), duration, frequency));
            }

            @Override
            public String toString() {
                return "by jQueryExpression=" + jQueryExpression();
            }

            private String jQueryExpression() {
                if (!jQueryExpression.startsWith("$")) {
                    return "$('" + jQueryExpression + "')";
                } else {
                    return jQueryExpression;
                }
            }
        };
    }

    @Deprecated
    public static By jQuery(final String jQueryExpression) {
        return $(jQueryExpression, new Duration(2, SECONDS));
    }

    @Deprecated
    public static By jQuery(final String jQueryExpression, final Duration duration) {
        return $(jQueryExpression, duration);
    }

    private static String waitUntilId(String expression, Duration duration, Duration frequency) {
        String[] ids = waitUntilIds(expression, duration, frequency);
        if (ids.length > 1)
            throw new ComponentException("Find more than one component defined by jQueryExpression=" + expression.substring(7));
        else
            return ids[0];
    }

    private static String[] waitUntilIds(String expression, Duration duration, Duration frequency) {
        Throwable ex = null;
        try {
            final long step = frequency.unit.toMillis(frequency.duration);
            for (long timeout = duration.unit.toMillis(duration.duration); timeout > 0 && !Thread.currentThread().isInterrupted(); timeout -= step, Thread.sleep(step)) {
                try {
                    return EvaluatorHolder.get().elementsId(expression);
                } catch (RuntimeException e) {
                    ex = e;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ex = e;
        }

        if (ex instanceof EvaluatorException) {
            if (expression.startsWith("jquery:"))
                throw new ComponentException("Cannot find component defined by jQueryExpression=" + expression.substring(7));
            else
                throw new ComponentException("Cannot find component defined by id=" + expression);
        }
        throw new RuntimeException("Unable to reach the condition in " + duration.duration + " " + duration.unit, ex);
    }
}