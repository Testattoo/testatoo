package org.testatoo.bundle.html5.components.table

import org.testatoo.core.ByCss
import org.testatoo.core.component.datagrid.Cell

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('td')
class Td extends Cell {

    @Override
    String getValue() {
        config.evaluator.eval(id, "it.text().trim()")
    }
}
