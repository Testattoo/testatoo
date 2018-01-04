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
package org.testatoo.core

import groovy.text.SimpleTemplateEngine
import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class By {
    private String expression
    private SimpleTemplateEngine templateEngine

    protected By(String expression, SimpleTemplateEngine templateEngine) {
        this.expression = expression
        this.templateEngine = templateEngine
    }

    static By css(String expression) {
        String id = '${id}'
        new By("\\\$('#${id}').find('${expression}')", new SimpleTemplateEngine())
    }

    static By js(String expression) {
        new By(expression, null)
    }

    String getExpression(Component component) {
        templateEngine ? templateEngine.createTemplate(expression).make([id: component.id()]) : expression
    }
}
