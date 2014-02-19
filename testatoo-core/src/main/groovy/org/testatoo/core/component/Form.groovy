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

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Form extends Component {

    Form() {}

    void submit() {
        Button submit_button = evaluator.getMetaInfo("\$('#${id}').find('[type=submit]:first')")[0] as Button
        if (submit_button.is(new Available()))
            evaluator.click(submit_button.id)
        else
            throw new ComponentException('Cannot submit form without submit button')
    }

    void reset() {
        Button reset_button = evaluator.getMetaInfo("\$('#${id}').find('[type=reset]:first')")[0] as Button
        if (reset_button.is(new Available()))
            evaluator.click(reset_button.id)
        else
            throw new ComponentException('Cannot reset form without reset button')
    }

}
