//The jQuery-ish Navigator API

// High level vs low level
@Inject webDriver ... selenium

// Installation & Usage


Javascript, AJAX and Dynamic Pages
 => Executing Arbitrary Code
 => Waiting

 9.3 Alert and Confirm Dialogs

 WebDriver currently does not handle the alert() and confirm() dialog windows. However, we can fake it through some Javascript magic as discussed on the WebDriver issue for this. Geb implements a workaround based on this solution for you. Note that this feature relies on making changes to the browser's window DOM object so may not work on all browsers on all platforms. At the time when WebDriver adds support for this functionality the underlying implementation of the following methods will change to use that which will presumably be more robust. Geb adds this functionality through the AlertAndConfirmSupport class that is mixed into Page and Module.

 The Geb methods prevent the browser from actually displaying the dialog, which is a good thing. This prevents the browser blocking while the dialog is displayed and causing your test to hang indefinitely.

 Unexpected alert() and confirm() calls can have strange results. This is due to the nature of how Geb handles this internally. If you are seeing strange results, you may want to run your tests/scripts against a real browser and watch what happens to make sure there aren't alert()s or confirm()s being called that you aren't expecting. To do this, you need to disable Geb's handling by changing your code to not use the methods below.

4 Interacting with content
4.1 The $ Function
4.1.1 CSS Selectors
4.4 Composition

# Usage

## goTo method

We can go to a page from the url (2 solutions)

    goTo("/Page.html");
    page().open("/Page.html");

## The selector : $

$ method will provide a pointer to a component, we can use [CSS3 selector](http://www.w3.org/TR/selectors/)

    $("#content .photo");	// Pointer to component with class name "photo" into component with id "content"

To get the real component, we use the "component" method:

    component(Panel.class, $("#content .photo"));

To get an unique component in the page :

    component(AlertBox.class);

To get all components of one type in the page:

    components(Div.class);

## Matchers

To verify the state of the page, we use matchers:

    assertThat(component(Panel.class, $("#content .photo")), is(visible()));

## Ajax

Ajax testing not require other stuff.

If component is not found immediately, other evaluations will be executed during one second.
If this time is not enought, we can define the maximum time to wait.

Exemple with 2 seconds :

    assertThat(component(Panel.class, $("#content .photo", max(2, SECONDS))), is(visible()));

Clearly:

    assertThat(photo()), is(visible()));

    private Panel photo(){
      return component(Panel.class, $("#content .photo", max(2, SECONDS)));
    }
