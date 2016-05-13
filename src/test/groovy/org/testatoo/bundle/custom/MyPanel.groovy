package org.testatoo.bundle.custom

import org.testatoo.core.ByCss
import org.testatoo.core.component.Panel

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss("div[data-type=custom-panel]")
class MyPanel extends Panel {

    @Override
    String title() {
        return config.evaluator.eval(id(), "it.find('span:first').text()")
    }
}
