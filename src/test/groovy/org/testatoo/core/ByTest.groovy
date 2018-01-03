/**
 * Copyright © 2017 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Component

import static org.mockito.Mockito.*
import static org.testatoo.core.Testatoo.config

@RunWith(JUnit4)
class ByTest {
    private static MetaDataProvider meta

    @BeforeClass
    static void before() {
        config.evaluator = mock(Evaluator)
        meta = mock(MetaDataProvider)
        when(meta.metaInfo(any(Component))).thenReturn(new MetaInfo(id: 'id', node: 'node'))
    }

    @Test
    void should_generate_expected_expression() {
        By by =  By.css('option')

        Component cmp = new Component()
        cmp.meta = meta

        assert by.getExpression(cmp) == "\$('#id').find('option')"

        by =  By.js('some_js_expression')

        assert by.getExpression(cmp) == "some_js_expression"
    }

    @Test
    void should_be_able_to_find_sub_components() {
        Component cmp = new Component()
        cmp.meta = meta
        when(config.evaluator.metaInfo("\$('#id').find('sub_expression')")).thenReturn([
                new MetaInfo(id: '1', node: 'node'),
                new MetaInfo(id: '2', node: 'node')
        ])

        List<Component> subs = cmp.find(By.css('sub_expression'))
        assert subs.size() == 2
    }
}
