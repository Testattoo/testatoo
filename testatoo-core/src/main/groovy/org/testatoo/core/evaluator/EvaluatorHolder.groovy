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
package org.testatoo.core.evaluator
/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public final class EvaluatorHolder {

    private static ThreadLocal<Evaluator> EVALUATOR = new ThreadLocal<>();

    private EvaluatorHolder() {
    }

    public static void register(Evaluator evaluator) {
        if (evaluator == null) {
            throw new IllegalArgumentException("Evaluator cannot be null")
        }
        EVALUATOR.set(evaluator)
    }

    public static Evaluator get() {
        EVALUATOR.get()
    }

}