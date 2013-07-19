# Matchers

Matchers allow to verify content of the page

## 3.1 Sugar syntax

Any matchers only simplify the lisibility of tests

    is();
    has();

## With TextSupport

Assert that component contains text

    assertThat(simpleComponentWithText("myText"), containsText("Text"));

Two ways to assert that component contains exactly the text

    assertThat(simpleComponentWithText("myText"), containsExactlyText("myText"));
    assertThat(simpleTextFieldWithText("myText"), has(text("myText")));

## With LabelSupport

Component with label

    assertThat(componentWithLabel("myLabel"), has(label("myLabel")));

Component with empty label

    assertThat(componentWithoutLabel(), has(no(label())));
    assertThat(componentWithoutLabel(), has(emptyLabel()));
    assertThat(componentWithoutLabel(), has(label("")));

## With SizeSupport

    assertThat(componentWithSize(2), has(size(2)));

## With TitleSupport

    assertThat(componentWithTitle("myTitle"), has(title("myTitle")));

## With ValueSupport

    assertThat(componentWithValue("myValue"), has(value("myValue")));

## Checkable

    assertThat(checkableComponent(), is(checked()));

    assertThat(checkableComponent(), is(not(checked())));
    assertThat(checkableComponent(), is(unChecked()));

## Containers

    assertThat(page(), contains(component()));
    assertThat(emptyComponent(), not(contains(component())));

    assertThat(page(), displays(visibleComponent()));

## All components

    assertThat(existentComponent(), exist());

    assertThat(visibleComponent(), is(visible()));

##  Wait for assertion

To execute assertion on asynchronous action, you can use "waitUntil".

    waitUntil(page(), has(title("Exit page")));

Exemple with asynchronous change page :

    Component myComponent = myComponent(); 
    assertThat(page(), displays(myComponent));
    clickOn(outgoingLink());
    waitUntil(myComponent, not(exist()));

## Create custom matchers

Testatoo use Hamcrest library, so it's possible to use custom hamcrest matcher in Testatoo.
Hamcrest documentation is here : http://code.google.com/p/hamcrest/wiki/Tutorial

Usage :

    assertThat(componentSelection(), has(size(2)));

Matcher : 

    package org.testatoo.core.matcher;

    import org.hamcrest.*;
    import org.testatoo.core.nature.SizeSupport;

    public class SizeValue extends TypeSafeMatcher<SizeSupport> {

	private int expectedSize;

	@Override
	protected boolean matchesSafely(SizeSupport sizeSupport) {
	    return sizeSupport.size() == expectedSize;
	}

	@Override
	public void describeTo(Description description) {
	    description.appendText("size:" + expectedSize);
	}

	public SizeValue(int expectedSize) {
	    this.expectedSize = expectedSize;
	}

	public static Matcher<SizeSupport> size(int expectedSize) {
	    return new SizeValue(expectedSize);
	}
    }
