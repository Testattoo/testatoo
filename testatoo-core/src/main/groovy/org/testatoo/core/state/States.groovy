package org.testatoo.core.state

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class States {

    // States
    static final Enabled enabled = new Enabled()
    static final Disabled disabled = new Disabled()
    static final Visible visible = new Visible()
    static final Hidden hidden = new Hidden()
    static final Available available = new Available()
    static final Missing missing = new Missing()
    static final Checked checked = new Checked()
    static final Unchecked unchecked = new Unchecked()
    static final Empty empty = new Empty()
    static final Filled filled = new Filled()
}
