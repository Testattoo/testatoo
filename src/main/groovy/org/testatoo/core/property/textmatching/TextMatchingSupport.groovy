package org.testatoo.core.property.textmatching

import java.util.regex.Pattern

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

trait TextMatchingSupport {

//    boolean containing(Pattern pattern) {
//        return new PatternTextMatcher("(^|.+\\s)" + pattern.pattern() + "(\$|\\s.+)").matches()
//    }

    TextMatcher containing(CharSequence str) {
        new PatternTextMatcher("(^|.+\\s)" + Pattern.quote(str) + "(\$|\\s.+)")
    }


    TextMatcher equalsTo(CharSequence str) {
        println str
    }

//    TextMatcher startsWith(CharSequence str) {
//        new PatternTextMatcher(Pattern.quote(str) + ".*")
//    }
//
//    TextMatcher notStartsWith(CharSequence str) {
//        new NegatedTextMatcher(startsWith(str))
//    }
//
//    TextMatcher contains(CharSequence str) {
//        new PatternTextMatcher(".*" + Pattern.quote(str) + ".*")
//    }
//
//    TextMatcher notContains(CharSequence str) {
//        new NegatedTextMatcher(contains(str))
//    }
//
//    TextMatcher endsWith(CharSequence str) {
//        new PatternTextMatcher(".*" + Pattern.quote(str))
//    }
//
//    TextMatcher notEndsWith(CharSequence str) {
//        new NegatedTextMatcher(endsWith(str))
//    }
//
//    TextMatcher iStartsWith(CharSequence str) {
//        new PatternTextMatcher("(?i)" + Pattern.quote(str) + ".*")
//    }
//
//    TextMatcher iNotStartsWith(CharSequence str) {
//        new NegatedTextMatcher(iStartsWith(str))
//    }
//
//    TextMatcher iContains(CharSequence str) {
//        new PatternTextMatcher("(?i).*" + Pattern.quote(str) + ".*")
//    }
//
//    TextMatcher iNotContains(CharSequence str) {
//        new NegatedTextMatcher(iContains(str))
//    }
//
//    TextMatcher iEndsWith(CharSequence str) {
//        new PatternTextMatcher("(?i).*" + Pattern.quote(str))
//    }
//
//    TextMatcher iNotEndsWith(CharSequence str) {
//        new NegatedTextMatcher(iEndsWith(str))
//    }
//
//    TextMatcher containsWord(CharSequence str) {
//        new PatternTextMatcher("(^|.+\\s)" + Pattern.quote(str) + "(\$|\\s.+)")
//    }
//
//    TextMatcher notContainsWord(CharSequence str) {
//        new NegatedTextMatcher(containsWord(str))
//    }
//
//    TextMatcher iContainsWord(CharSequence str) {
//        new PatternTextMatcher("(?i)(^|.+\\s)" + Pattern.quote(str) + "(\$|\\s.+)")
//    }
//
//    TextMatcher iNotContainsWord(CharSequence str) {
//        new NegatedTextMatcher(iContainsWord(str))
//    }
//
//    TextMatcher startsWith(Pattern pattern) {
//        new PatternTextMatcher(pattern.pattern() + ".*")
//    }
//
//    TextMatcher notStartsWith(Pattern pattern) {
//        new NegatedTextMatcher(startsWith(pattern))
//    }
//
//    TextMatcher contains(Pattern pattern) {
//        new PatternTextMatcher(".*" + pattern.pattern() + ".*")
//    }
//
//    TextMatcher notContains(Pattern pattern) {
//        new NegatedTextMatcher(contains(pattern))
//    }
//
//    TextMatcher endsWith(Pattern pattern) {
//        new PatternTextMatcher(".*" + pattern.pattern())
//    }
//
//    TextMatcher notEndsWith(Pattern pattern) {
//        new NegatedTextMatcher(endsWith(pattern))
//    }
//
//    TextMatcher iStartsWith(Pattern pattern) {
//        new PatternTextMatcher("(?i)" + pattern.pattern() + ".*")
//    }
//
//    TextMatcher iNotStartsWith(Pattern pattern) {
//        new NegatedTextMatcher(iStartsWith(pattern))
//    }
//
//    TextMatcher iContains(Pattern pattern) {
//        new PatternTextMatcher("(?i).*" + pattern.pattern() + ".*")
//    }
//
//    TextMatcher iNotContains(Pattern pattern) {
//        new NegatedTextMatcher(iContains(pattern))
//    }
//
//    TextMatcher iEndsWith(Pattern pattern) {
//        new PatternTextMatcher("(?i).*" + pattern.pattern())
//    }
//
//    TextMatcher iNotEndsWith(Pattern pattern) {
//        new NegatedTextMatcher(iEndsWith(pattern))
//    }
//
//    TextMatcher containsWord(Pattern pattern) {
//        new PatternTextMatcher("(^|.+\\s)" + pattern.pattern() + "(\$|\\s.+)")
//    }
//
//    TextMatcher notContainsWord(Pattern pattern) {
//        new NegatedTextMatcher(containsWord(pattern))
//    }
//
//    TextMatcher iContainsWord(Pattern pattern) {
//        new PatternTextMatcher("(?i)(^|.+\\s)" + pattern.pattern() + "(\$|\\s.+)")
//    }
//
//    TextMatcher iNotContainsWord(Pattern pattern) {
//        new NegatedTextMatcher(iContainsWord(pattern))
//    }

}
