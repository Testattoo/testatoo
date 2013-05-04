package org.testatoo.experimental.dsl

public interface Evaluator {
    void open(String url)

    boolean isVisible(IdSupport component)

    String[] getElementsIds(String expr)

    String getLabel(IdSupport component)

    void reset(IdSupport component)

    void setFocus(IdSupport component)

    void type(String text)

    void click(IdSupport component)
}