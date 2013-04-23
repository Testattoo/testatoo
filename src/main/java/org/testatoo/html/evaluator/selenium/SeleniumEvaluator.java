package org.testatoo.html.evaluator.selenium;

import com.thoughtworks.selenium.Selenium;
import org.testatoo.core.Evaluator;
import org.testatoo.core.component.Component;
import org.testatoo.core.nature.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class SeleniumEvaluator implements Evaluator<Selenium> {

    private final Selenium selenium;
    private final String name;

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
    public Selenium implementation() {
        return selenium;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void open(String url) {
        selenium.open(url);
    }

    @Override
    public Boolean isVisible(Component component) {
        return Boolean.valueOf(evaljQuery("$('#" + component.id() + "').is(':visible');"));
    }

    @Override
    public Boolean isEnabled(Component component) {
        return Boolean.valueOf(evaljQuery("$('#" + component.id() + "').is(':enabled');"));
    }

    @Override
    public Boolean isFocused(Component component) {
        return null;
    }

    @Override
    public Boolean isChecked(Checkable checkable) {
        Component component = (Component) checkable;
        return Boolean.valueOf(evaljQuery("$('#" + component.id() + "').prop('checked');"));
    }

    @Override
    public String label(LabelSupport labelSupport) {
        Component component = (Component) labelSupport;
        if (Integer.valueOf(evaljQuery("$('label[for=\"" + component.id() + "\"]').length")) > 0) {
            return evaljQuery("$('label[for=\"" + component.id() + "\"]').text()");
        }

        if (Integer.valueOf(evaljQuery("$('#" + component.id() + "').prev('label').length")) > 0) {
            return evaljQuery("$('#" + component.id() + "').prev('label').text()");
        }
        // TODO clean string
        return evaljQuery("$('#" + component.id() + "').parent().text()");
    }

    @Override
    public String value(ValueSupport valueSupport) {
        return null;
    }

    @Override
    public String text(TextSupport textSupport) {
        Component component = (Component) textSupport;
        String nodeName = nodename(component);
        if (nodeName.equalsIgnoreCase("input")) {
            return evaljQuery("$('#" + component.id() + "').prop('value');");
        }
        return evaljQuery("$('#" + component.id() + "').text();");
    }

    @Override
    public String title(TitleSupport titleSupport) {
        return null;
    }

    @Override
    public String reference(ReferenceSupport referenceSupport) {
        Component component = (Component) referenceSupport;
        return evaljQuery("$('#" + component.id() + "').prop('href');");
    }

    private String nodename(Component component) {
        return evaljQuery("$('#" + component.id() + "').prop('nodeName')");
    }

    private String evaljQuery(String expression) {
        selenium.runScript("if(window.tQuery){(function($, jQuery){window.testatoo_tmp=" + expression + ";})(window.tQuery, window.tQuery);}else{window.testatoo_tmp='__TQUERY_MISSING__';}");
        String s = selenium.getEval("window.testatoo_tmp");
        if ("__TQUERY_MISSING__".equals(s)) {
            selenium.runScript(addScript("tquery-1.7.2.js") + addScript("tquery-simulate.js") + addScript("tquery-util.js"));
            selenium.runScript("if(window.tQuery){(function($, jQuery){window.testatoo_tmp=" + expression + ";})(window.tQuery, window.tQuery);}else{window.testatoo_tmp='__TQUERY_MISSING__';}");
            s = selenium.getEval("window.testatoo_tmp");
        }
        return s;
    }

    private String addScript(String name) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(Bootstraper.class.getResourceAsStream(name)));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } catch (IOException e) {
            throw new IllegalStateException("Internal error occurred when trying to load custom scripts : " + e.getMessage(), e);
        }
    }
}