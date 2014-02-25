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
package experimental.dsl.todo

import org.junit.Ignore
import org.junit.Test

import org.testatoo.core.component.Button

import static org.testatoo.core.Testatoo.$

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class CustomComponentTest {

    @Test
    @Ignore
    public void new_dsl() {
        Button button = $('#button') as Button
        MyProp myProp = new MyProp()
        MyState myState = new MyState()

        // are / have extension
        assert [button].are(enabled)

        // custom components
        MyPanel myPanel = $('#panel') as MyPanel
        // existing property, evaluation overriden
        assert myPanel.has(title.equalsTo('temp2')).and(myPanel.has(myProp.equalsTo('temp1')))
        // new property
        assert myPanel.is(myState)
    }
}
