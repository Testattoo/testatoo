package bootstrap

import org.testatoo.core.component.Component
import org.testatoo.core.property.Value

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ProgressBar extends Component {
    public ProgressBar() {
        support Value, {
            Component c -> c.evaluator.getString("document.getElementById('${c.id}').style.width")
        }
    }
}