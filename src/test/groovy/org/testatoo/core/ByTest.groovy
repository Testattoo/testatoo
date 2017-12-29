package org.testatoo.core

import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.testatoo.core.component.Component

import static org.mockito.Matchers.any
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

@RunWith(JUnit4)
class ByTest {
    private static MetaDataProvider meta

    @BeforeClass
    static void before() {
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
}
