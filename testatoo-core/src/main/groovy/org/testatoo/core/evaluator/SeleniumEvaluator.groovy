package org.testatoo.core.evaluator

import com.thoughtworks.selenium.Selenium
import org.testatoo.core.Evaluator
import org.testatoo.core.EvaluatorException
import org.testatoo.core.component.Component
import org.testatoo.core.component.ComponentException
//import org.testatoo.core.component.Page
import org.testatoo.core.input.Click
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
     * @param name     of the evaluator
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
        // TODO clean string
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
            expression = "jquery:\$('#" + expression.replace(".", "\\\\.") + "')"
        }

        if (!Boolean.valueOf(evaljQuery(expression.substring(7) + ".length > 0"))) {
            throw new EvaluatorException("Cannot find component defined by the jquery expression : " + expression.substring(7))
        }

        String[] resultId = extractId(expression);
        for (int i = 0; i < resultId.length; i++) {
            String id = resultId[i]
            if (id.equals("")) {
                id = UUID.randomUUID().toString()
                evaljQuery("\$(" + expression.substring(7) + "[" + i + "]).attr('id', '" + id + "')")
                resultId[i] = id
            }
        }
        return resultId
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

    private void typeKey(int keyCode) {
        String keyModifier = keyModifier()
        evaljQuery("(\$.browser.webkit) ? \$(window.document).simulate('type', {charCode: " + keyCode + keyModifier + "}) : \$('body').simulate('type', {keyCode: " + keyCode + keyModifier + "})")
    }

    private String[] extractId(String expression) {
        if (expression.startsWith("jquery:")) {
            expression = expression.substring(7, expression.length())
            return parseCSV(evaljQuery("[]; " + expression + ".each(function(){window.testatoo_tmp.push(\$(this).attr('id') ? \$(this).attr('id') : 'undefined')});"))
        }
        return null;
    }

    private static String[] parseCSV(String input) {
        String[] splitedInput = input.split(",")
        for (int i = 0; i < splitedInput.length; i++) {
            if (splitedInput[i].equalsIgnoreCase("undefined"))
                splitedInput[i] = ""
        }
        return splitedInput
    }

    private String evaljQuery(String expression) {
        selenium.runScript("if(window.tQuery){(function(\$, jQuery){window.testatoo_tmp=" + expression + ";})(window.tQuery, window.tQuery);}else{window.testatoo_tmp='__TQUERY_MISSING__';}")
        String s = selenium.getEval("window.testatoo_tmp")
        if ("__TQUERY_MISSING__".equals(s)) {
            selenium.runScript(addScript("tquery-1.7.2.js") + addScript("tquery-simulate.js") + addScript("tquery-util.js"))
            selenium.runScript("if(window.tQuery){(function(\$, jQuery){window.testatoo_tmp=" + expression + ";})(window.tQuery, window.tQuery);}else{window.testatoo_tmp='__TQUERY_MISSING__';}")
            s = selenium.getEval("window.testatoo_tmp")
        }
        return s;
    }

    private String addScript(String name) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(name)))
            StringBuilder builder = new StringBuilder()
            char[] buffer = new char[8192]
            int read
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read)
            }
            reader.close()
            return builder.toString()
        } catch (IOException e) {
            throw new IllegalStateException("Internal error occurred when trying to load custom scripts : " + e.message, e)
        }
    }

    private boolean isIe() {
        if (!_props.containsKey("IE")) {
            _props.setProperty("IE", evaljQuery("\$.browser.msie"))
        }
        return Boolean.valueOf(_props.getProperty("IE"))
    }
}