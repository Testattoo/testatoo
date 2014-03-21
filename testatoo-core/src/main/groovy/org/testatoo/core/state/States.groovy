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
    static final MultiSelectable multiSelectable = new MultiSelectable()
    static final SingleSelectable singleSelectable = new SingleSelectable()
    static final Selected selected = new Selected()
    static final UnSelected unSelected = new UnSelected()

}
