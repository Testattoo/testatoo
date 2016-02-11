/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
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
package org.testatoo.core.component

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.ComponentException

import static org.junit.Assert.fail

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
@RunWith(JUnit4)
class ComponentTest {

    @Test
    public void should_have_generic_behaviours_not_implemented() {
        Component component = new Component()

        try {
            component.enabled
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.disabled
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.available
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.missing
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.visible
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.hidden
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.contain(null)
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.display(null)
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.click()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.doubleClick()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.rightClick()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }

        try {
            component.drag()
            fail()
        } catch (ComponentException e) {
            assert e.message == 'Unsupported Operation'
        }
    }
}
