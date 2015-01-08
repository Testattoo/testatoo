/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core

import org.testatoo.core.evaluator.Evaluator

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

class Browser {

    private Evaluator evaluator

    Browser(Evaluator evaluator) {
        this.evaluator = evaluator
    }

    String getTitle() {
        evaluator.title
    }

    String getPageSource() {
        evaluator.pageSource
    }

    String getUrl() {
        evaluator.url
    }

    void open(String url) {
        evaluator.open(url)
    }

    Navigation getNavigate() {
        return new Navigation(evaluator)
    }
}
