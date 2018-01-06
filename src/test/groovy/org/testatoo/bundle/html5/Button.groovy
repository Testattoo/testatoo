/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.bundle.html5

import org.testatoo.core.CssIdentifier

import static org.testatoo.core.Testatoo.config

@CssIdentifier('button,input[type=submit],input[type=button],input[type=reset],input[type=image]')
class Button extends org.testatoo.core.component.Button {
    @Override
    String text() {
        config.evaluator.eval(this.id(), "it.is('input') ? it.val() : it.text().trim()")
    }
}
