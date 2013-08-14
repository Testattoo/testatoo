package org.testatoo.core.evaluator

import org.testatoo.core.Evaluator
import org.testatoo.core.component.Component

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class DeferredEvaluator implements Evaluator {

    @Override
    Object getImplementation() { EvaluatorHolder.get().implementation }

    @Override
    String getName() { EvaluatorHolder.get().name }

    @Override
    void open(String url) { EvaluatorHolder.get().open(url) }

    @Override
    boolean isEnabled(Component component) { EvaluatorHolder.get().isEnabled component }

    @Override
    boolean isVisible(Component component) { EvaluatorHolder.get().isVisible component }

    @Override
    Boolean isChecked(Component component) { EvaluatorHolder.get().isChecked component }

    @Override
    String[] getElementsIds(String expr) { EvaluatorHolder.get().getElementsIds expr }

    @Override
    String getLabel(Component component) { EvaluatorHolder.get().getLabel component }

    @Override
    void reset(Component component) { EvaluatorHolder.get().reset component }

    @Override
    void setFocus(Component component) { EvaluatorHolder.get().focus = component }

    @Override
    void type(String text) { EvaluatorHolder.get().type(text) }

    @Override
    void click(Component component) { EvaluatorHolder.get().click component }

    @Override
    String getText(Component component) { EvaluatorHolder.get().getText component }

    @Override
    String getPlaceholder(Component component) { EvaluatorHolder.get().getPlaceholder component }

    @Override
    boolean isAvailable(Component component) { EvaluatorHolder.get().isAvailable component }

    @Override
    String getType(String id) { EvaluatorHolder.get().getType(id) }

    @Override
    String getTitle(Component component) { EvaluatorHolder.get().getTitle(component) }

    @Override
    String getReference(Component component) { EvaluatorHolder.get().getReference(component) }
}
