package org.testatoo.html.evaluator;

import com.thoughtworks.selenium.Selenium;
import org.testatoo.core.Evaluator;
import org.testatoo.core.component.Component;
import org.testatoo.core.nature.*;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class SeleniumEvaluator implements Evaluator {

    private final Selenium selenium;
    private final String name;
    private Component currentFocusedComponent;
    private static final String PAGE_ID = "_PAGE_ID_";

    /**
     * Class constructor specifying the used selenium engine
     *
     * @param name     of the evaluator
     * @param selenium the selenium engine
     */
    public SeleniumEvaluator(String name, Selenium selenium) {
        this.name = name;
        this.selenium = selenium;
    }

    public SeleniumEvaluator(Selenium selenium) {
        this(DEFAULT_NAME, selenium);
    }


    @Override
    public Object implementation() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public void open(String url) {
    }

    @Override
    public Boolean isVisible(Component component) {
        return null;
    }

    @Override
    public Boolean isEnabled(Component component) {
        return null;
    }

    @Override
    public Boolean isFocused(Component component) {
        return null;
    }

    @Override
    public Boolean isChecked(Checkable component) {
        return null;
    }

    @Override
    public String label(LabelSupport labelSupport) {
        return null;
    }

    @Override
    public String value(ValueSupport valueSupport) {
        return null;
    }

    @Override
    public String text(TextSupport textSupport) {
        return null;
    }

    @Override
    public String title(TitleSupport titleSupport) {
        return null;
    }

    @Override
    public String reference(ReferenceSupport referenceSupport) {
        return null;
    }
}