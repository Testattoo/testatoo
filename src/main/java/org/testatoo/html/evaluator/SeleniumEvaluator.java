package org.testatoo.html.evaluator;

import com.thoughtworks.selenium.Selenium;
import org.testatoo.config.Evaluator;
import org.testatoo.core.component.Button;
import org.testatoo.core.component.Component;
import org.testatoo.core.component.Link;
import org.testatoo.core.nature.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
public class SeleniumEvaluator implements Evaluator {

    private final Properties _props = new Properties();

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

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selenium implementation() {
        return selenium;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String pageId() {
        return PAGE_ID;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean existComponent(String id) {
        if (id.equals(PAGE_ID)) {
            return true;
        }
        // Cannot use jQuery cause only present after page loaded (not the case when existComponent is a page)
        return selenium.isElementPresent("id=" + id);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String text(TextSupport textSupport) {
        Component component = (Component) textSupport;
        String nodeName = nodename(component);
        if (nodeName.equalsIgnoreCase("input")) {
            return attribute(component.id(), Attribute.value);
        }
        return nodeTextContent(component);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String icon(IconSupport iconSupport) {
        Component component = (Component) iconSupport;
        String nodeName = nodename(component);
        if (nodeName.equalsIgnoreCase("input")) {
            return attribute(component.id(), Attribute.src);
        }
        if (nodeName.equalsIgnoreCase("button")) {
            // the button tag is used
            try {
                return attribute(elementsId("jquery:$('#" + component.id() + " img')")[0], Attribute.src);
            } catch (Exception e) {
                // No icon available
                return "";
            }
        }
        return "";
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean isVisible(Component component) {
        return component instanceof AbstractWindow
                || selenium.isVisible(component.id());
//            || (Boolean.valueOf(evaluate("window.tQuery('#" + component.id() + "').is(':visible') || !(window.tQuery('#" + component.id() + "').is(':hidden')" +
//            " || window.tQuery('#" + component.id() + "').css('visibility') == 'hidden' || window.tQuery('#" + component.id() + "').css('display') == 'none')")));
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean isEnabled(Component component) {
        return !Boolean.valueOf(evaljQuery("$('#" + component.id() + "').is(':disabled');"))
                && !Boolean.valueOf(evaljQuery("$('#" + component.id() + "').prop('readonly') == true;"));
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void check(Checkable checkable) {
        if (!checkable.isChecked())
            click((Component) checkable, Click.left);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean isChecked(Checkable checkable) {
        return Boolean.valueOf(attribute(((Component) checkable).id(), Attribute.checked));
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void unCheck(org.testatoo.core.component.CheckBox checkbox) {
        if (checkbox.isChecked())
            click(checkbox, Click.left);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String value(ValueSupport valueSupport) {
        if (valueSupport instanceof AbstractTextField) {
            return selenium.getValue(((Component) valueSupport).id());
        }
        if (valueSupport instanceof Cell) {
            return nodeTextContent(((Component) valueSupport));
        }

        return attribute(((Component) valueSupport).id(), Attribute.value);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String source(org.testatoo.core.component.Image image) {
        return attribute(image.id(), Attribute.src);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String label(LabelSupport labelSupport) {
        try {
            Component label = new Component(this, $("label[for=" + ((Component) labelSupport).id() + "]").id(this));
            return nodeTextContent(label);
        } catch (ComponentException e) {
            try {
                Component label = new Component(this, $("$('#" + ((Component) labelSupport).id() + "').prev('label')").id(this));
                return nodeTextContent(label);
            } catch (ComponentException ex) {
                try {
                    Component label = new Component(this, $("$('#" + ((Component) labelSupport).id() + "').parent()").id(this));
                    return nodeTextContent(label);
                } catch (ComponentException exp) {
                    return "";
                }

            }
        }
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Integer maxLength(AbstractTextField textfield) {
        if (attribute(textfield.id(), Attribute.maxlength).equals(""))
            return Integer.MAX_VALUE;
        return Integer.valueOf(attribute(textfield.id(), Attribute.maxlength));
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void reset(AbstractTextField textField) {
        evaljQuery("$('#" + textField.id() + "').val('')");
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void selectFilePath(String filePath, FileDialog fileDialog) {
        throw new EvaluatorException("Not available for security constraints");
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String selectedFilePath(FileDialog fileDialog) {
        throw new EvaluatorException("Not available for security constraints");
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void unselect(String value, ListModel listModel) {
        Select select = findEmbeddedSelect(listModel);
        String[] values = parseCSV(evaljQuery("window.tQuery.map(window.tQuery('#" + select.id() + " :selected'), function(e) { return window.tQuery(e).text(); });"));
        for (Option option : select.options()) {
            List<String> selectedValues = Arrays.asList(values);
            if (option.content().equals(value)) {
                if (selectedValues.contains(option.content())) {
                    evaljQuery("$('#" + option.id() + "').prop('selected', false)");
                    evaljQuery("$('#" + select.id() + "').simulate('change');");
                }
            }
        }
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void unselectAll(ListModel listModel) {
        Select select = findEmbeddedSelect(listModel);
        String[] values = parseCSV(evaljQuery("window.tQuery.map(window.tQuery('#" + select.id() + " :selected'), function(e) { return window.tQuery(e).val(); });"));
        for (Option option : select.options()) {
            List<String> selectedValues = Arrays.asList(values);
            if (selectedValues.contains(option.value())) {
                evaljQuery("$('#" + option.id() + "').prop('selected', false)");
                evaljQuery("$('#" + select.id() + "').simulate('change')");
            }
        }
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void select(String value, ListModel listModel) {
        Select select = findEmbeddedSelect(listModel);
        for (Option option : select.options()) {
            if (option.content().equals(value)) {
                evaljQuery("$('#" + option.id() + "').prop('selected', 'selected');");
                // Use fix for IE
                evaljQuery("null; if (window.tQuery.browser.msie) {window.tQuery('#" + select.id() + "').simulate('click');} " +
                        "else {window.tQuery('#" + select.id() + "').simulate('change');}");
            }
        }
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean contains(Container container, Component... component) {
        boolean containsAllElements = true;
        for (Component cmp : component) {
            if (!selenium.isElementPresent("//*[@id='" + ((Component) container).id() + "']//*[@id='" + cmp.id() + "']")) {
                containsAllElements = false;
            }
        }
        return containsAllElements;
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String label(Option option) {
        return attribute(option.id(), Attribute.label);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String label(OptionGroup optionGroup) {
        return attribute(optionGroup.id(), Attribute.label);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean selected(Option option) {
        String id = option.id();
        return existComponent(id) && (attribute(id, Attribute.selected).toLowerCase().equalsIgnoreCase("selected")
                || attribute(id, Attribute.selected).toLowerCase().equals("true"));
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Boolean hasFocus(Component component) {
        return currentFocusedComponent != null && currentFocusedComponent.equals(component);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String title(TitleSupport titleSupport) {
        if (titleSupport instanceof Page) {
            return selenium.getTitle();
        }

        if (titleSupport instanceof FileDialog) {
            return "";
        }

        if (titleSupport instanceof Column) {
            return nodeTextContent((Component) titleSupport);
        }
        return attribute(((Component) titleSupport).id(), Attribute.title);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String message(AlertBox alertbox) {
        throw new ComponentException("Alertbox is not implemented in HTML4 cartridge");
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selection<Column> columns(DataGrid datagrid) {
        String query = "$('#" + datagrid.id() + " thead tr:last th')";
        int numberOfColumns = Integer.valueOf(evaljQuery(query + ".length"));

        List<Column> columns = new ArrayList<Column>();
        for (int rowNum = 0; rowNum < numberOfColumns; rowNum++) {
            Column column = new Column(this, elementsId("jquery:$(" + query + "[" + rowNum + "])")[0]);
            columns.add(column);
        }
        return ListSelection.from(columns);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public Selection<Row> rows(DataGrid datagrid) {
        List<Row> rows = new ArrayList<Row>();

        String query = "$('#" + datagrid.id() + " tbody tr')";
        int numberOfRows = Integer.valueOf(evaljQuery(query + ".length"));

        for (int rowNum = 0; rowNum < numberOfRows; rowNum++) {
            Row row = new Row(this, elementsId("jquery:$(" + query + "[" + rowNum + "])")[0]);
            rows.add(row);
        }
        return ListSelection.from(rows);
    }

    @Override
    public Selection<Cell> cells(CellContainer cellContainer) {
        List<Cell> cells = new ArrayList<Cell>();

        if (cellContainer instanceof Row) {
            String query = "$('#" + ((Component) cellContainer).id() + " td')";
            int numberOfCells = Integer.valueOf(evaljQuery(query + ".length"));

            for (int cellNum = 0; cellNum < numberOfCells; cellNum++) {
                Cell cell = new Cell(this, elementsId("jquery:$(" + query + "[" + cellNum + "])")[0]);
                cells.add(cell);
            }
        }

        if (cellContainer instanceof Column) {
            // Find column number
            String query = "$('#" + ((Component) cellContainer).id() + "').parent().find('th')";
            int numberOfColumns = Integer.valueOf(evaljQuery(query + ".length"));

            int selectedColumnNum = 0;
            boolean columnNumFind = false;

            for (int colNum = 0; colNum < numberOfColumns; colNum++) {
                if (elementsId("jquery:$(" + query + "[" + colNum + "])")[0].equals(((Component) cellContainer).id())) {
                    selectedColumnNum = colNum;
                    columnNumFind = true;
                }
            }

            if (!columnNumFind) {
                throw new EvaluatorException("Unable to find the Column");
            }

            query = "$('#" + ((Component) cellContainer).id() + "').parents('table').find('tbody tr')";
            int numberOfRows = Integer.valueOf(evaljQuery(query + ".length"));

            for (int rowNum = 0; rowNum < numberOfRows; rowNum++) {
                Cell cell = new Cell(this, elementsId("jquery:$($(" + query + "[" + rowNum + "]).find('td')[" + selectedColumnNum + "])")[0]);
                cells.add(cell);
            }
        }
        return ListSelection.from(cells);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public ComponentType componentType(String id) {
        return ComponentType.valueOf(evaljQuery("$('#" + id + "').componentType()"));
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void click(Component component, Click which) {
        try {
            setFocus(component);
            if (which == Click.right) {
                evaljQuery("$('#" + component.id() + "').simulate('rightclick')");
            } else {
                // If component is link we need to open the expected target
                // Not sure but some Browser seems have a security check to not open page on js event
                if (component instanceof Link && !((Link) component).reference().equals("#")) {
                    selenium.click(component.id());
                } else {
                    evaljQuery("$('#" + component.id() + "').simulate('click')");
                }
            }
        } catch (Exception e) {
            // Continue... if the click change page
        }
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void doubleClick(Component component) {
        evaljQuery("$('#" + component.id() + "').simulate('dblclick')");
        setFocus(component);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void mouseOver(Component component) {
        evaljQuery("$('#" + component.id() + "').simulate('mouseover')");
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void mouseOut(Component component) {
        evaljQuery("$('#" + component.id() + "').simulate('mouseout')");
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void dragAndDrop(Component from, Component to) {
        evaljQuery("$('#" + from.id() + "').simulate('dragTo', {'target': $('#" + to.id() + "')})");
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void focusOn(Component component) {
        click(component, Click.left);
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void type(String text) {
        String keyModifier = keyModifier();
        if (currentFocusedComponent != null) {
            for (byte charCode : text.getBytes()) {
                if (isIe()) {
                    evaljQuery("$('#" + currentFocusedComponent.id() + "')" +
                            ".val($('#" + currentFocusedComponent.id() + "').val() + String.fromCharCode(" + charCode + "));");
                }
                evaljQuery("$('#" + currentFocusedComponent.id() + "')" +
                        ".val($('#" + currentFocusedComponent.id() + "').val() + String.fromCharCode(" + charCode + "));");
            }
        } else {
            for (char charCode : text.toCharArray()) {
                evaljQuery("($.browser.mozilla) ? $(window.document).simulate('type', {keyCode: " + keyboardLayout.convert(charCode) + keyModifier + "}) : $(window.document).simulate('type', {charCode: " + keyboardLayout.convert(charCode) + keyModifier + "})");
            }
        }
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void press(Key key) {
        typeKey(key.code());
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public void close(AbstractWindow window) {
        throw new ComponentException("Close window is not implemented in HTML4 cartridge");
    }

    /**
     * @see org.testatoo.core.Evaluator
     */
    @Override
    public String reference(Link link) {
        if (attribute(link.id(), Attribute.href).equals(""))
            return "#";
        return attribute(link.id(), Attribute.href);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public void open(String url) {
        selenium.open(url);
        currentFocusedComponent = null;
        release();
    }

    /* Attributes don't work with jQuery prop method */
    final private static Set<Attribute> specialsAttributes = new HashSet<Attribute>() {{
        add(Attribute.style);
        add(Attribute.action);
        add(Attribute.href);
        add(Attribute.src);
        add(Attribute.accept);
        add(Attribute.classid);
        add(Attribute.longdesc);
        add(Attribute.cellhalign);
        add(Attribute.cellvalign);
    }};

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public String attribute(String id, Attribute attribute) {
        String attributeValue;
        if (specialsAttributes.contains(attribute))
            attributeValue = evaljQuery("$('#" + id + "').attr('" + attribute + "');");
        else
            attributeValue = evaljQuery("$('#" + id + "').prop('" + attribute + "');");

        if (attributeValue.equals("null"))
            return "";

        return attributeValue;
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Boolean exist(String id, Attribute attribute) {
        return !evaljQuery("$('#" + id + "[" + attribute + "]')").isEmpty();
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public String pageSource() {
        return selenium.getHtmlSource();
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<OptionGroup> optionGroups(Select select) {
        List<OptionGroup> optionGroups = new ArrayList<OptionGroup>();
        try {
            for (String id : $("#" + select.id() + " optgroup").ids(this)) {
                optionGroups.add(new OptionGroup(this, id));
            }
        } catch (ComponentException e) {
            return ListSelection.from(optionGroups);
        }
        return ListSelection.from(optionGroups);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Option> options(Select select) {
        List<Option> options = new ArrayList<Option>();
        try {
            for (String id : $("#" + select.id() + " option").ids(this)) {
                options.add(new Option(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(options);
        }
        return ListSelection.from(options);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Option> selectedOptions(Select select) {
        try {
            String[] selectedIds = elementsId("jquery:$('#" + select.id() + " option:selected')");
            List<Option> options = new ArrayList<Option>();
            for (String id : selectedIds) {
                options.add(new Option(this, id));
            }
            return ListSelection.from(options);
        } catch (EvaluatorException se) {
            return ListSelection.empty();
        }
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Option> options(OptionGroup optionGroup) {
        List<Option> options = new ArrayList<Option>();
        try {
            for (String id : $("#" + optionGroup.id() + " option").ids(this)) {
                options.add(new Option(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(options);
        }
        return ListSelection.from(options);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Param> params(Object object) {
        List<Param> params = new ArrayList<Param>();
        try {
            for (String id : $("#" + object.id() + " param").ids(this)) {
                params.add(new Param(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(params);
        }
        return ListSelection.from(params);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Area> areas(Map map) {
        List<Area> areas = new ArrayList<Area>();
        try {
            for (String id : $("#" + map.id() + " area").ids(this)) {
                areas.add(new Area(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(areas);
        }
        return ListSelection.from(areas);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Col> cols(Colgroup colgroup) {
        List<Col> cols = new ArrayList<Col>();
        try {
            for (String id : $("#" + colgroup.id() + " col").ids(this)) {
                cols.add(new Col(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(cols);
        }
        return ListSelection.from(cols);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Caption caption(Table table) {
        return new Caption(this, $("#" + table.id() + " caption").id(this));
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public String content(Component component) {
        return nodeTextContent(component);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public THead thead(Table table) {
        return new THead(this, $("#" + table.id() + " thead").id(this));
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public TBody tbody(Table table) {
        return new TBody(this, $("#" + table.id() + " tbody").id(this));
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public TFoot tfoot(Table table) {
        return new TFoot(this, $("#" + table.id() + " tfoot").id(this));
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Tr> tr(Component component) {
        List<Tr> tableRows = new ArrayList<Tr>();
        try {
            for (String id : $("#" + component.id() + " tr").ids(this)) {
                tableRows.add(new Tr(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(tableRows);
        }
        return ListSelection.from(tableRows);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Td> td(Tr tr) {
        List<Td> td = new ArrayList<Td>();
        try {
            for (String id : $("#" + tr.id() + " td").ids(this)) {
                td.add(new Td(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(td);
        }
        return ListSelection.from(td);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Th> th(Tr tr) {
        List<Th> th = new ArrayList<Th>();
        try {
            for (String id : $("#" + tr.id() + " th").ids(this)) {
                th.add(new Th(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(th);
        }
        return ListSelection.from(th);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Col> cols(Table table) {
        List<Col> cols = new ArrayList<Col>();
        try {
            for (String id : $("#" + table.id() + " col").ids(this)) {
                cols.add(new Col(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(cols);
        }
        return ListSelection.from(cols);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Selection<Colgroup> colgroups(Table table) {
        List<Colgroup> colgroups = new ArrayList<Colgroup>();
        try {
            for (String id : $("#" + table.id() + " colgroup").ids(this)) {
                colgroups.add(new Colgroup(this, id));
            }
        } catch (EvaluatorException e) {
            return ListSelection.from(colgroups);
        }
        return ListSelection.from(colgroups);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public String content(Option option) {
        return nodeTextContent(option);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public void submit(Form form) {
        evaljQuery("$('#" + form.id() + "').submit()");
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public void reset(Form form) {
        click(getResetButton(form), Click.left);
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public Boolean isReadOnly(Field field) {
        return !selenium.isEditable(field.id());
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
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

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public HtmlElementType htmlElementType(String id) {
        return HtmlElementType.valueOfIgnoreCase(evaljQuery("$('#" + id + "').htmlType()"));
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public String nodeTextContent(Component component) {
        return selenium.getText(component.id());
    }

    /**
     * @see org.testatoo.cartridge.html4.HtmlEvaluator
     */
    @Override
    public String nodename(Component component) {
        return evaljQuery("$('#" + component.id() + "').prop('nodeName')");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SeleniumHtmlEvaluator");
        sb.append("{location='").append(selenium.getLocation()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String evaluate(String expression) {
        return evaljQuery(expression);
    }

    // -------------- Private ----------------------
    private String[] extractId(String expression) {
        if (expression.startsWith("jquery:")) {
            expression = expression.substring(7, expression.length());
            return parseCSV(evaljQuery("[]; " + expression + ".each(function(){window.testatoo_tmp.push($(this).attr('id') ? $(this).attr('id') : 'undefined')});"));
        }
        return null;
    }

    private void setFocus(Component component) {
        if (component instanceof Link || component instanceof Area || component instanceof Button
                || component instanceof Object || component instanceof ListModel || component instanceof Field) {
            evaljQuery("$('#" + component.id() + "').focus()");
            currentFocusedComponent = component;
        }
    }

    private void typeKey(int keyCode) {
        String keyModifier = keyModifier();
        evaljQuery("($.browser.webkit) ? $(window.document).simulate('type', {charCode: " + keyCode + keyModifier + "}) : $('body').simulate('type', {keyCode: " + keyCode + keyModifier + "})");
    }

    private String keyModifier() {
        if (!pressedKeyModifier.isEmpty()) {
            List<String> options = new ArrayList<String>();
            if (pressedKeyModifier.contains(CONTROL)) {
                options.add("ctrlKey : true");
            }
            if (pressedKeyModifier.contains(SHIFT)) {
                options.add("shiftKey : true");
            }
            if (pressedKeyModifier.contains(ALT)) {
                options.add("altKey : true");
            }

            String result = "";
            for (String option : options) {
                result = result + ", " + option;
            }
            return result;
        } else {
            return "";
        }
    }

    private static String[] parseCSV(String input) {
        String[] splitedInput = input.split(",");
        for (int i = 0; i < splitedInput.length; i++) {
            if (splitedInput[i].equalsIgnoreCase("undefined"))
                splitedInput[i] = "";
        }
        return splitedInput;
    }

    private Button getResetButton(Form form) {
        return new Button(this, $("#" + form.id() + " :reset").id(this));
    }

    private Select findEmbeddedSelect(ListModel listModel) {
        try {
            if (listModel instanceof Select) {
                return (Select) listModel;
            } else {
                ListBox listBox = (ListBox) listModel;
                java.lang.reflect.Field fields[] = listBox.getClass().getDeclaredFields();
                for (java.lang.reflect.Field field : fields) {
                    field.setAccessible(true);
                    if (field.getName().equals("select")) {
                        return (Select) field.get(listBox);
                    }
                }
            }
        } catch (Exception e) {
            // Nop
        }
        throw new EvaluatorException("Unable to identify the type of ListModel");
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
            throw new IllegalStateException("Internal error occured when trying to load custom scripts : " + e.getMessage(), e);
        }
    }

    private boolean isIe() {
        if (!_props.containsKey("IE")) {
            _props.setProperty("IE", evaluate("$.browser.msie"));
        }
        return Boolean.valueOf(_props.getProperty("IE"));
    }
}