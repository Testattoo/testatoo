package org.testatoo.experimental.dsl

import org.testatoo.core.EvaluatorHolder

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-04
 */
 class LegacyEvaluator implements Evaluator {
     @Override
     void open(String url) {
         EvaluatorHolder.get().open(url)
     }

     @Override
     boolean isVisible(IdSupport component) {
         return EvaluatorHolder.get().isVisible(component)
     }

     @Override
     String[] getElementsIds(String expr) {
         return EvaluatorHolder.get().elementsId(expr)
     }
 }
