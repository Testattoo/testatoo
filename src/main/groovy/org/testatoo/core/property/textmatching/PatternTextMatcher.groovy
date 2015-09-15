package org.testatoo.core.property.textmatching

import java.util.regex.Pattern

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class PatternTextMatcher implements TextMatcher {
    final Pattern pattern

    PatternTextMatcher(CharSequence pattern) {
        this.pattern = Pattern.compile(pattern.toString(), Pattern.DOTALL)
    }

    boolean matches(String text) {
        text ==~ pattern
    }
}