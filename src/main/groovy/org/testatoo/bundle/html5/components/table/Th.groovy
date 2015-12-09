package org.testatoo.bundle.html5.components.table

import org.testatoo.core.ByCss
import org.testatoo.core.component.datagrid.Cell
import org.testatoo.core.component.datagrid.Column

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('th')
class Th extends Column {

    @Override
    List<Td> getCells() {
        int index = config.evaluator.eval(id, "it.index() + 1") as int
        findjs("\$('#${id}').closest('table').find('tbody tr').find('td:nth-child(${index})')", Td)
    }

    @Override
    String getTitle() {
        config.evaluator.eval(id, "it.text().trim()")
    }
}
