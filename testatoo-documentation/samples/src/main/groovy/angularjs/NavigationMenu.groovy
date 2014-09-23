/**
 * Copyright (C) 2013 Ovea (dev@ovea.com)
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
package angularjs

import org.testatoo.core.component.Component
import org.testatoo.core.component.Link
import org.testatoo.core.property.Items
import org.testatoo.core.property.Size
import org.testatoo.core.property.Title
import org.testatoo.core.state.Selected
import org.testatoo.core.state.UnSelected

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class NavigationMenu extends Component {

    NavigationMenu() {
        support Size, {
            Component c -> Integer.valueOf(c.evaluator.getString("\$('#${id}').find('a').length"))
        }

        support Items, {
            Component c -> c.evaluator.getMetaInfo("\$('#${id}').find('a')").collect { it as Item }
        }
    }

    List<Item> getItems() {
        this.evaluator.getMetaInfo("\$('#${id}').find('a')").collect { it as Item }
    }

    private class Item extends Link {

        Item() {
            support Title, {
                Component c -> c.evaluator.getString("\$('#${id}').text()").trim()
            }
            support Selected, {
                Component c ->  Boolean.valueOf(c.evaluator.getString("\$('#${id}').attr('class') === \$('#${id}').closest('nav').attr('class')"))
            }
            support UnSelected, {
                Component c ->  Boolean.valueOf(c.evaluator.getString("\$('#${id}').attr('class') !== \$('#${id}').closest('nav').attr('class')"))
            }
        }
    }

}

//$('.home').attr('class') === $('.home').parent().attr('class')
