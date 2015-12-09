package org.testatoo.bundle.html5.components.table

import org.testatoo.core.ByCss
import org.testatoo.core.component.datagrid.Row

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@ByCss('tr')
class Tr extends Row {

    @Override
    List<Td> getCells() {
        config.evaluator.getMetaInfo("\$('#${id}').find('td')").collect { it as Td }
    }

    @Override
    String getTitle() {
        config.evaluator.eval(id, "it.find('th:first').text().trim()")
    }
}
