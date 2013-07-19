# Actions

Actions provide the possibility to interact with the components

## Checkbox
    
    CheckBox checkBox = component(CheckBox.class, $("[name=checkboxName]"));
    assertThat(checkBox, is(not(checked())));
    check(checkBox);
    assertThat(checkBox, is(checked()));
    unCheck(checkBox);
    assertThat(checkBox, is(not(checked())));

## InputText

Replace text

    enter("email@noname.com", into(component(InputText.class, $("#email"))));

Add text

    type("email@noname", into(component(InputText.class, $("#textField"))));
    type(".com", into(component(InputText.class, $("#textField"))));

## Form

Reset form

    reset(component(Form.class, $("#myForm")));

Submit form

    submit(component(Form.class, $("#myForm")));

## Radio

    assertThat(radioYes, is(not(checked())));
    assertThat(radioNo, is(not(checked())));

    check(radioYes);
    assertThat(radioYes, is(checked()));
    assertThat(radioNo, is(not(checked())));

    check(radioNo);
    assertThat(radioYes, is(not(checked())));
    assertThat(radioNo, is(checked()));

## Select

    assertThat(selectPane(), has(5, options()));
    assertThat(it(), has(2, optionGroups()));
    and(containsValues("one", "two", "three"));
    and(it(), has(not(selectedValues())));

    on(selectPane()).select("20");
    assertThat(selectPane(), has(selectedValues("20")));

## Click

    clickOn(component());
    doubleClickOn(component());

## Drag mouse

    dragMouseOver(component());
    dragMouseOut(component());
