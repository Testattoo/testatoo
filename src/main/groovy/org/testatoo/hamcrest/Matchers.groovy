/**
 * Copyright (C) 2016 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.hamcrest

import org.hamcrest.Factory
import org.hamcrest.Matcher
import org.testatoo.bundle.html5.components.list.MultiSelect
import org.testatoo.core.Component
import org.testatoo.core.support.CheckSupport
import org.testatoo.core.support.InputSupport
import org.testatoo.core.support.LabelSupport
import org.testatoo.core.support.RangeSupport
import org.testatoo.core.support.SelectSupport
import org.testatoo.core.support.TextSupport
import org.testatoo.core.support.ValiditySupport
import org.testatoo.core.support.ValueSupport

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Matchers {

    @Factory
    public static Matcher<Component> enabled() { new EnabledMatcher() }

    @Factory
    public static Matcher<Component> disabled() { new DisabledMatcher() }

    @Factory
    public static Matcher<Component> available() { new AvailableMatcher() }

    @Factory
    public static Matcher<Component> missing() { new MissingMatcher() }

    @Factory
    public static Matcher<Component> visible() { new VisibleMatcher() }

    @Factory
    public static Matcher<Component> hidden() { new HiddenMatcher() }

    @Factory
    public static Matcher<LabelSupport> hasLabel(String label) { new LabelMatcher(label) }

    @Factory
    public static Matcher<InputSupport> hasPlaceholder(String placeholder) { new PlaceholderMatcher(placeholder) }

    @Factory
    public static Matcher<InputSupport> empty() { new EmptyMatcher() }

    @Factory
    public static Matcher<InputSupport> filled() { new FilledMatcher() }

    @Factory
    public static Matcher<InputSupport> readOnly() { new ReadOnlyMatcher() }

    @Factory
    public static Matcher<InputSupport> required() { new RequiredMatcher() }

    @Factory
    public static Matcher<InputSupport> optional() { new OptionalMatcher() }

    @Factory
    public static Matcher<CheckSupport> checked() { new CheckedMatcher() }

    @Factory
    public static Matcher<CheckSupport> unchecked() { new UnCheckedMatcher() }

    @Factory
    public static Matcher<RangeSupport> hasMaximum(Object maximum) { new MaximumMatcher(maximum) }

    @Factory
    public static Matcher<RangeSupport> hasMinimum(Object minimum) { new MinimumMatcher(minimum) }

    @Factory
    public static Matcher<RangeSupport> hasStep(Object minimum) { new StepMatcher(minimum) }

    @Factory
    public static Matcher<RangeSupport> inRange() { new InRangeMatcher() }

    @Factory
    public static Matcher<RangeSupport> outOfRange() { new OutOfRangeMatcher() }

    @Factory
    public static Matcher<ValiditySupport> valid() { new ValidMatcher() }

    @Factory
    public static Matcher<ValiditySupport> invalid() { new InValidMatcher() }

    @Factory
    public static Matcher<TextSupport> hasText(String text) { new TextMatcher(text) }

    @Factory
    public static Matcher<SelectSupport> selected() { new SelectedMatcher() }

    @Factory
    public static Matcher<SelectSupport> unselected() { new UnSelectedMatcher() }

    @Factory
    public static Matcher<ValueSupport> hasValue(Object value) { new ValueMatcher(value) }

    @Factory
    public static Matcher<MultiSelect> multiSelectable() { new MultiSelectableMatcher() }
}
