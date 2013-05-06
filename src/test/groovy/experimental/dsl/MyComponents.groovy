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
package experimental.dsl

import org.testatoo.experimental.dsl.Button
import org.testatoo.experimental.dsl.Component
import org.testatoo.experimental.dsl.Testatoo
import org.testatoo.experimental.dsl.TextField

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-01
 */
class MyComponents {

    @Delegate
    private Testatoo testatoo = new Testatoo()

    Component login_view = $("#login_view")
    Component dashboard_view = $("#dashboard_view")
    TextField login_email = $("#login_form :input[name=\"email\"]") as TextField
    TextField login_password = $("#login_form :input[name=\"password\"]") as TextField
    Button login_button = $("#login_form button:first") as Button
    Button logout_button = $("[data-role=logout]:visible") as Button
    Component login_error_message = $("#login_form .alert-error")

}
