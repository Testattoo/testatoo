package presentation

import org.testatoo.core.component.Component
import org.testatoo.core.property.Text

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class TextMessage extends Component {
    TextMessage() {
        support Text, {
            Component c -> c.evaluator.getString("\$('#${id}').text()")
        }
    }
}
