package org.testatoo.experimental.dsl

import org.testatoo.core.EvaluatorHolder
import org.testatoo.core.input.Click

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
class LegacyEvaluator implements Evaluator {

    @Override
    void open(String url) { EvaluatorHolder.get().open(url) }

    @Override
    boolean isVisible(IdSupport component) { EvaluatorHolder.get().isVisible(component) }

    @Override
    String[] getElementsIds(String expr) { EvaluatorHolder.get().elementsId(expr) }

    @Override
    String getLabel(IdSupport component) { EvaluatorHolder.get().label(component) }

    @Override
    void reset(IdSupport component) { EvaluatorHolder.get().reset(component) }

    @Override
    void setFocus(IdSupport component) { EvaluatorHolder.get().focusOn(component) }

    @Override
    void type(String text) { EvaluatorHolder.get().type(text) }

    @Override
    void click(IdSupport component) { EvaluatorHolder.get().click(component, Click.left) }

    @Override
    String getText(IdSupport component) { EvaluatorHolder.get().label(component) }

}
