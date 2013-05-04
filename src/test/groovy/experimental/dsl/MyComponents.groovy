package experimental.dsl

import org.testatoo.experimental.dsl.Component
import org.testatoo.experimental.dsl.Testatoo

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-01
 */
class MyComponents {

    @Delegate
    private Testatoo testatoo = new Testatoo()

    // components are initialzed once and can be reused
    // => only expression is kept, evaluation (to get id + id assignment) is done only when needed
    Component login_view = $("#login_view")
    Component dashboard_view = $("#dashboard_view")

    Map<String, Component> login_form = [
        email: $("#login_form :input[name=\"email\"]"),
        password: $("#login_form :input[name=\"password\"]"),
        login_button: $("#login_form button:first")
    ]

}
