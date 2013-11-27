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
package org.testatoo.core.component

import org.testatoo.core.state.Available
import org.testatoo.core.state.Hidden
import org.testatoo.core.state.Missing
import org.testatoo.core.state.Visible

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Form extends Component {

    Form() {
        type Type.FORM
        support Available, Missing, Hidden, Visible
    }

    void submit() {
        Button submit_button = evaluator.getMetaInfo("\$('#${id}').find('[type=submit]:first')")[0] as Button
        evaluator.evalScript("testatoo.ext.simulate.click('${submit_button.id}')")
    }

    void reset() { evaluator.evalScript("\$('#${id}')[0].reset()") }

}
