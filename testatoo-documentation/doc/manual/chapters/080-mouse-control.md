# Mouse control

Testatoo provides the possibility to simulate actions with the mouse

## Click & double click on component

Perform a click & a double click on a component:

    Mouse.clickOn(component);
    Mouse.doubleClickOn(textField);

## Right click

Perform a right click on a component:

    Mouse.rightClickOn(textField);

## Mouse over

It's possible to simulate the mouse passing over a component:

    Mouse.mouseOverOn(textField);

Logically, it's also possible to simulate the mouse leaving a component on witch it was:

    Mouse.mouseOutOf(textField);

## Drag and drop

It's possible to drag a component onto another component:

    Mouse.drag(draggableComponent).on(droppableComponent);
