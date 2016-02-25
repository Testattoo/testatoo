package org.testatoo.helper

import org.testatoo.core.MetaDataProvider
import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class MouseFakeComponent extends Component {

    String id

    MouseFakeComponent(MetaDataProvider metaData) { super(metaData) }

    @Override
    void click() {}

    @Override
    void rightClick() {}

    @Override
    void doubleClick() {}

//    @Override
//    DragBuilder drag() {
//        return new DragBuilder() {
//            @Override
//            void on(Component onto) {
//            }
//        }
//    }
}
