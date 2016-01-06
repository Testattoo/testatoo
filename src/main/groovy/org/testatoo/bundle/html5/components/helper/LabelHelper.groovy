/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
package org.testatoo.bundle.html5.components.helper

import org.testatoo.core.Component
import static org.testatoo.core.Testatoo.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class LabelHelper {

    private static final String expr =
            "function() {" +
                    "   var label = \$('label[for=' + it.attr('id') + ']');" +
                    "   if (label.length > 0) return label.text().trim();" +
                    "   var p = it.prev('label');" +
                    "   if (p.length > 0) return p.text();" +
                    "   return it.parent().text().trim();" +
                    "}()"

    static String getLabel(Component c) {
        config.evaluator.eval(c.id, expr).trim()
    }
}
