# Keyboard control

Testatoo provide the possibility to simulate actions on the keyboard.
Only US English keyboard is managed for the moment.

## Press on a key

    import static org.testatoo.core.input.Key.*;
    
    Keyboard.press(F1);		//Press on key F1
    Keyboard.press(NUMPAD3);	//Press on number 3 on the numpad
    Keyboard.press(TAB);	//Press on tab key

## Keep a key pressed and release all keys

Keep a key pressed

    import org.testatoo.core.input.Keyboard;
    import static org.testatoo.core.input.KeyModifier.*;
    ...
    Keyboard.keyDown(CONTROL);

Release all keys

    Keyboard.release();

## List of managed keys

    F1
    F2
    F3
    F4
    F5
    F6
    F7
    F8
    F9
    F10
    F11
    F12

    NUMPAD0
    NUMPAD1
    NUMPAD2
    NUMPAD3
    NUMPAD4
    NUMPAD5
    NUMPAD6
    NUMPAD7
    NUMPAD8
    NUMPAD9

    BACKSPACE
    TAB
    ENTER
    PAUSE
    CAPS_LOCK
    ESCAPE
    SPACE
    PAGE_UP
    PAGE_DOWN
    END
    HOME
    LEFT_ARROW
    UP_ARROW
    RIGHT_ARROW
    DOWN_ARROW
    INSERT
    DELETE
    MULTIPLY
    ADD
    SUBTRACT
    DIVIDE
    NUM_LOCK
    SCROLL_LOCK
    SEMI_COLON
    DECIMAL_POINT
    EQUAL
    COMMA
    DASH
    PERIOD
    FORWARD_SLASH
    GRAVE_ACCENT
    OPEN_BRACKET
    BACK_SLASH
    CLOSE_BRACKET
    SINGLE_QUOTE