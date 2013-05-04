package org.testatoo.experimental.dsl

public interface Evaluator {
    void open(String url)

    boolean isVisible(IdSupport component)

    String[] getElementsIds(String expr)

    String getLabel(IdSupport component)
}