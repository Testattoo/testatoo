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
}
