package org.testatoo.core

import groovy.text.SimpleTemplateEngine
import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class By {

    private String expression
    private SimpleTemplateEngine templateEngine

    protected By(String expression, SimpleTemplateEngine templateEngine) {
        this.expression = expression
        this.templateEngine = templateEngine
    }

    public static By css(String expression) {
        String id = '${id}'
        new By("\\\$('#${id}').find('${expression}')", new SimpleTemplateEngine())
    }

    public static js(String expression) {
        new By(expression, null)
    }

    public String getExpression(Component component) {
        templateEngine ? templateEngine.createTemplate(expression).make([id: component.id]) : expression
    }

}
