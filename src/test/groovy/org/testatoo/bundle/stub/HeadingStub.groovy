package org.testatoo.bundle.stub

import org.testatoo.core.CssIdentifier
import org.testatoo.core.component.Heading

@CssIdentifier('HeadingStub')
class HeadingStub extends Heading {
    String text() {
        return 'Heading Text'
    }
}
