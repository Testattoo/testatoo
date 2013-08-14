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
package org.testatoo.core.evaluator

import com.thoughtworks.selenium.Selenium
import org.testatoo.core.Evaluator
import org.testatoo.core.Log
import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentException
import org.testatoo.core.input.Click

//import org.testatoo.core.component.Page
//import org.testatoo.core.input.Key;
//import org.testatoo.core.input.KeyModifier;
//import org.testatoo.core.input.KeyboardLayout;
//import org.testatoo.core.input.i18n.USEnglishLayout;
//import org.testatoo.core.nature.Checkable;
//import org.testatoo.core.nature.ReferenceSupport;
//import org.testatoo.core.nature.Selectable;
//import org.testatoo.core.nature.TitleSupport;
//import org.testatoo.core.nature.ValueSupport

//import static org.testatoo.core.input.KeyModifier.ALT;
//import static org.testatoo.core.input.KeyModifier.CONTROL;
//import static org.testatoo.core.input.KeyModifier.SHIFT;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class SeleniumEvaluator implements Evaluator {

    private final Selenium selenium
    private final String name
    private final Properties _props = new Properties()
//    protected KeyboardLayout keyboardLayout = new USEnglishLayout();
//    protected final List<KeyModifier> pressedKeyModifier = new ArrayList<KeyModifier>();
    private Component focusedComponent;

    /**
     * Class constructor specifying the used selenium engine
     *
     * @param name of the evaluator
     * @param selenium the selenium engine
     */
    public SeleniumEvaluator(String name, Selenium selenium) {
        this.name = name
        this.selenium = selenium
    }

    public Selenium getImplementation() {
        return selenium;
    }

    public String getName() {
        return name;
    }

    @Override
    public void open(String url) {
        selenium.open(url)
        focusedComponent = null
//        release()
    }

    @Override
    public boolean isVisible(Component component) {
        return Boolean.valueOf(evaljQuery("\$('#" + component.id + "').is(':visible');"))
    }

    @Override
    Boolean isChecked(Component component) {
        return Boolean.valueOf(evaljQuery("\$('#" + component.id + "').is(':checked');"))
    }

    @Override
    public boolean isEnabled(Component component) {
        return !Boolean.valueOf(evaljQuery("\$('#" + component.id + "').is(':disabled');"))
    }

//    @Override
//    public Boolean isFocused(Component component) {
//        return Boolean.valueOf(evaljQuery("$('#" + component.id + "').is(':focus');"))
//    }
//
//    @Override
//    public Boolean isChecked(Component component) {
//        return Boolean.valueOf(attribute(component, "checked"))
//    }

    @Override
    public String getLabel(Component component) {
        if (Integer.valueOf(evaljQuery("\$('label[for=\"" + component.id + "\"]').length")) > 0) {
            return evaljQuery("\$('label[for=\"" + component.getId() + "\"]').text()")
        }

        if (Integer.valueOf(evaljQuery("\$('#" + component.id + "').prev('label').length")) > 0) {
            return evaljQuery("\$('#" + component.id + "').prev('label').text()")
        }
        return evaljQuery("\$('#" + component.id + "').parent().text()")
    }

    @Override
    public String getPlaceholder(Component component) {
        return attribute(component, "placeholder")
    }

//    @Override
//    public String value(ValueSupport valueSupport) {
//        Component component = (Component) valueSupport
//        return attribute(component, "value")
//    }

    @Override
    public String getText(Component component) {
        String nodeName = nodename(component)
        if (nodeName.equalsIgnoreCase("input")) {
            return attribute(component, "value")
        }
        return evaljQuery("\$('#" + component.getId() + "').text();")
    }

//    @Override
//    public String title(TitleSupport titleSupport) {
//        if (titleSupport instanceof Page) {
//            return evaljQuery("$(document).find('title').text();")
//        }
//        return attribute((Component) titleSupport, "title")
//    }

//    @Override
//    public String reference(ReferenceSupport referenceSupport) {
//        return attribute((Component) referenceSupport, "href")
//    }
//
//    @Override
//    public String selectedValue(Selectable selectable) {
//        return null
//    }

//    @Override
//    public Boolean contains(Component container, Component component) {
//        return Integer.valueOf(evaljQuery("\$('#" + container.id() + "').has('#" + component.id() + "').length;")) > 0
//    }

    @Override
    public String[] getElementsIds(String expression) {
        if (!expression.startsWith("jquery:")) {
            expression = "\$('#" + expression.replace(".", "\\\\.") + "')"
        } else {
            expression = expression.substring(7)
        }

        String expr = """(function() {
    var ids=[];
    ${expression}.each(function() {
        var id=\$(this).attr('id');
        if(!id) {
            id = 'gen-' + new Date().getTime() * Math.random();
            \$(this).attr('id', id);
        }
        ids.push(id);
    });
    return ids;
}());"""

        return evaljQuery(expr).split(",")
    }

    @Override
    public void reset(Component component) {
        evaljQuery("\$('#" + component.id + "').val('')")
    }

    @Override
    public void setFocus(Component component) {
        click(component);
    }

    @Override
    public void type(String text) {
        String keyModifier = keyModifier()
        if (focusedComponent != null) {
            for (byte charCode : text.getBytes()) {
                if (isIe()) {
                    evaljQuery("\$('#" + focusedComponent.getId() + "')" +
                        ".val(\$('#" + focusedComponent.getId() + "').val() + String.fromCharCode(" + charCode + "));")
                }
                evaljQuery("\$('#" + focusedComponent.getId() + "')" +
                    ".val(\$('#" + focusedComponent.getId() + "').val() + String.fromCharCode(" + charCode + "));")
            }
        } else {
            for (char charCode : text.toCharArray()) {
                evaljQuery("(\$.browser.mozilla) ? \$(window.document).simulate('type', {keyCode: " + keyboardLayout.convert(charCode) + keyModifier + "}) : \$(window.document).simulate('type', {charCode: " + keyboardLayout.convert(charCode) + keyModifier + "})")
            }
        }
    }

//    @Override
//    public void press(Key key) {
//        typeKey(key.code())
//    }
//
//    @Override
//    public void keyDown(KeyModifier keyModifier) {
//        pressedKeyModifier.add(keyModifier)
//    }
//
//    @Override
//    public void release(KeyModifier keyModifier) {
//        pressedKeyModifier.remove(keyModifier)
//    }
//
//    @Override
//    public void release() {
//        List<KeyModifier> keys = new ArrayList<KeyModifier>(pressedKeyModifier)
//        Collections.reverse(keys)
//        try {
//            for (KeyModifier keyModifier : keys) {
//                release(keyModifier)
//            }
//        } catch (Exception e) {
//            // Continue
//        }
//    }
//
    public void click(Component component, Click which = Click.LEFT) {
        try {
            setFocus(component);
            if (which == Click.RIGHT) {
                evaljQuery("\$('#" + component.id + "').simulate('rightclick')")
            } else {
                // If component is link we need to open the expected target
                // Not sure but some Browser seems have a security check to not open page on js event
//                if (component instanceof Link && !((Link) component).reference().equals("#")) {
//                    selenium.click(component.id());
//                } else {
                evaljQuery("\$('#" + component.id + "').simulate('click')")
//                }
            }
        } catch (Exception e) {
            // Continue... if the click change page
        }
    }

//    @Override
//    public void doubleClick(Component component) {
//        evaljQuery("$('#" + component.id() + "').simulate('dblclick')")
//        setFocus(component)
//    }
//
//    @Override
//    public void mouseOver(Component component) {
//        evaljQuery("$('#" + component.id() + "').simulate('mouseover')")
//    }
//
//    @Override
//    public void mouseOut(Component component) {
//        evaljQuery("$('#" + component.id() + "').simulate('mouseout')")
//    }
//
//    @Override
//    public void dragAndDrop(Component from, Component to) {
//        evaljQuery("$('#" + from.id() + "').simulate('dragTo', {'target': $('#" + to.id() + "')})")
//    }
//
//    @Override
//    public String evaluate(String expression) {
//        return evaljQuery(expression)
//    }

    @Override
    public boolean isAvailable(Component component) {
        try {
            component.id
            return true
        } catch (ComponentException e) {
            return false
        }
    }

    @Override
    String getType(String id) { evaljQuery("\$('#" + id + "').componentType()") }

    @Override
    String getTitle(Component component) {
        throw new UnsupportedOperationException()
    }

    @Override
    String getReference(Component component) {
        return evaljQuery("\$('#" + component.id + "').prop('href')")
    }

    private String nodename(Component component) {
        return evaljQuery("\$('#" + component.id + "').prop('nodeName')")
    }

    private String attribute(Component component, String attribute) {
        String attributeValue
        attributeValue = evaljQuery("\$('#" + component.id + "').prop('" + attribute + "');")
        if (attributeValue.equals("null"))
            return ""
        return attributeValue
    }

    private String keyModifier() {
        if (!pressedKeyModifier.isEmpty()) {
            List<String> options = new ArrayList<String>();
            if (pressedKeyModifier.contains(CONTROL)) {
                options.add("ctrlKey : true")
            }
            if (pressedKeyModifier.contains(SHIFT)) {
                options.add("shiftKey : true")
            }
            if (pressedKeyModifier.contains(ALT)) {
                options.add("altKey : true")
            }

            String result = ""
            for (String option : options) {
                result = result + ", " + option
            }
            return result
        } else {
            return ""
        }
    }

//    private void typeKey(int keyCode) {
//        String keyModifier = keyModifier()
//        evaljQuery("(\$.browser.webkit) ? \$(window.document).simulate('type', {charCode: " + keyCode + keyModifier + "}) : \$('body').simulate('type', {keyCode: " + keyCode + keyModifier + "})")
//    }

    private String evaljQuery(String expression) {

        String expr = """(function(\$, jQuery){
    if(!jQuery) return '__TQUERY_MISSING__';
    else return ${expression};
}(window.tQuery, window.tQuery));"""

        Log.selenium "EXECUTING: ${expr}"
        String v = selenium.getEval(expr)
        if (v == '__TQUERY_MISSING__') {
            selenium.runScript(load("tquery-1.7.2.js") + load("tquery-simulate.js") + load("tquery-util.js"))
            v = selenium.getEval(expr)
        }
        Log.selenium "RESULT: ${v}"
        return v;
    }

    private String load(String name) { getClass().getResource(name).text }

    private boolean isIe() {
        if (!_props.containsKey("IE")) {
            _props.setProperty("IE", evaljQuery("\$.browser.msie"))
        }
        return Boolean.valueOf(_props.getProperty("IE"))
    }
}