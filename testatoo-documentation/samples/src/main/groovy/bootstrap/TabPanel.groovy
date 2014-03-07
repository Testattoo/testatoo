package bootstrap

import bootstrap.property.Tabs
import org.testatoo.core.component.*
import org.testatoo.core.property.*
import org.testatoo.core.state.*

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class TabPanel extends Component {

    TabPanel() {
        support Size, {
            Component c -> Integer.valueOf(c.evaluator.getString("\$('#${id}').find('a').length"))
        }
        support Tabs, {
            Component c -> c.evaluator.getMetaInfo("\$('#${id}').find('a')").collect { it as Tab }
        }
    }

    List<Tab> getTabs() {
        this.evaluator.getMetaInfo("\$('#${id}').find('a')").collect { it as Tab }
    }

    private class Tab extends Component {
        Tab() {
            support Title, {
                Component c -> c.evaluator.getString("\$('#${id}').text()")
            }
            support Selected, {
                Component c ->  Boolean.valueOf(c.evaluator.getString("\$('#${id}').closest('li').hasClass('active')"))
            }
            support UnSelected, {
                Component c ->  !Boolean.valueOf(c.evaluator.getString("\$('#${id}').closest('li').hasClass('active')"))
            }
        }

        Panel getPanel() {
            return $('#' + this.evaluator.getString("\$('#${id}').attr('href').substring(1)")) as Panel
        }
    }

}
