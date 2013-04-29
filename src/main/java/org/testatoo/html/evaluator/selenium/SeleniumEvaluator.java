/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.html.evaluator.selenium;

import com.thoughtworks.selenium.Selenium;
import org.testatoo.core.Evaluator;
import org.testatoo.core.EvaluatorException;
import org.testatoo.core.component.Component;
import org.testatoo.core.component.Page;
import org.testatoo.core.nature.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.UUID;

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
        return !Boolean.valueOf(evaljQuery("$('#" + component.id() + "').is(':disabled');"));
    }

    @Override
    public Boolean isFocused(Component component) {
        return Boolean.valueOf(evaljQuery("$('#" + component.id() + "').is(':focus');"));
    }

    @Override
    public Boolean isChecked(Checkable checkable) {
        Component component = (Component) checkable;
        return Boolean.valueOf(attribute(component, "checked"));
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
        Component component = (Component) valueSupport;
        return attribute(component, "value");
    }

    @Override
    public String text(TextSupport textSupport) {
        Component component = (Component) textSupport;
        String nodeName = nodename(component);
        if (nodeName.equalsIgnoreCase("input")) {
            return attribute(component, "value");
        }
        return evaljQuery("$('#" + component.id() + "').text();");
    }

    @Override
    public String title(TitleSupport titleSupport) {
        if (titleSupport instanceof Page) {
            return evaljQuery("$(document).find('title').text();");
        }
        return attribute((Component) titleSupport, "title");
    }

    @Override
    public String reference(ReferenceSupport referenceSupport) {
        return attribute((Component) referenceSupport, "href");
    }

    @Override
    public String selectedValue(Selectable selectable) {
        return null;
    }

    @Override
    public String attribute(Component component, String attribute) {
        String attributeValue;
        attributeValue = evaljQuery("$('#" + component.id() + "').prop('" + attribute + "');");

        if (attributeValue.equals("null"))
            return "";

        return attributeValue;
    }

    @Override
    public Boolean contains(Component container, Component component) {
        return Integer.valueOf(evaljQuery("$('#" + container.id() + "').has('#" + component.id() + "').length;")) > 0;
    }

    @Override
    public String[] elementsId(String expression) {
        if (!expression.startsWith("jquery:")) {
            expression = "jquery:$('#" + expression.replace(".", "\\\\.") + "')";
        }

        if (!Boolean.valueOf(evaljQuery(expression.substring(7) + ".length > 0"))) {
            throw new EvaluatorException("Cannot find component defined by the jquery expression : " + expression.substring(7));
        }

        String[] resultId = extractId(expression);
        for (int i = 0; i < resultId.length; i++) {
            String id = resultId[i];
            if (id.equals("")) {
                id = UUID.randomUUID().toString();
                evaljQuery("$(" + expression.substring(7) + "[" + i + "]).attr('id', '" + id + "')");
                resultId[i] = id;
            }
        }
        return resultId;
    }

    private String nodename(Component component) {
        return evaljQuery("$('#" + component.id() + "').prop('nodeName')");
    }

    private String[] extractId(String expression) {
        if (expression.startsWith("jquery:")) {
            expression = expression.substring(7, expression.length());
            return parseCSV(evaljQuery("[]; " + expression + ".each(function(){window.testatoo_tmp.push($(this).attr('id') ? $(this).attr('id') : 'undefined')});"));
        }
        return null;
    }

    private static String[] parseCSV(String input) {
        String[] splitedInput = input.split(",");
        for (int i = 0; i < splitedInput.length; i++) {
            if (splitedInput[i].equalsIgnoreCase("undefined"))
                splitedInput[i] = "";
        }
        return splitedInput;
    }

    private String evaljQuery(String expression) {
        selenium.runScript("if(window.tQuery){(function($, jQuery){window.testatoo_tmp=" + expression + ";})(window.tQuery, window.tQuery);}else{window.testatoo_tmp='__TQUERY_MISSING__';}");
        String s = selenium.getEval("window.testatoo_tmp");
        if ("__TQUERY_MISSING__" .equals(s)) {
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