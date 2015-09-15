package org.testatoo.core.property.textmatching

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class NegatedTextMatcher implements TextMatcher {

    final TextMatcher matcher

    NegatedTextMatcher(TextMatcher matcher) {
        this.matcher = matcher
    }

    boolean matches(String text) {
        !matcher.matches(text)
    }
}
