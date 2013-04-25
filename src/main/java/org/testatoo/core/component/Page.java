package org.testatoo.core.component;

import org.testatoo.core.nature.TitleSupport;

/**
 * This class allows the testing of page.
 *
 * @author David Avenante (d.avenante@gmail.com)
 */
public class Page extends Component implements TitleSupport {

    public Page(String id) {
        super(id);
    }
}
