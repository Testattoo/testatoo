package presentation

import org.testatoo.core.component.Component
import org.testatoo.core.property.Text

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Teaser extends Component {

    Teaser() {
        support Text, {
            Component c -> c.evaluator.getString("\$('#${id}').text()")
        }
    }

}
