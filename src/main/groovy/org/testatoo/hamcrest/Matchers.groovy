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
import org.testatoo.core.component.Component
import org.testatoo.core.support.CheckSupport
import org.testatoo.core.support.EmptySupport
import org.testatoo.core.support.FilledSupport
import org.testatoo.core.support.InputSupport
import org.testatoo.core.support.LabelSupport
import org.testatoo.core.support.MaximumSupport
import org.testatoo.core.support.MinimumSupport
import org.testatoo.core.support.OptionalSupport
import org.testatoo.core.support.RangeSupport
import org.testatoo.core.support.ReadOnlySupport
import org.testatoo.core.support.RequiredSupport
import org.testatoo.core.support.SelectSupport
import org.testatoo.core.support.StepSupport
import org.testatoo.core.support.TextSupport
import org.testatoo.core.support.ValiditySupport
import org.testatoo.core.support.ValueSupport
import org.testatoo.hamcrest.matcher.property.LabelMatcher
import org.testatoo.hamcrest.matcher.property.MaximumMatcher
import org.testatoo.hamcrest.matcher.property.MinimumMatcher
import org.testatoo.hamcrest.matcher.property.PlaceholderMatcher
import org.testatoo.hamcrest.matcher.property.StepMatcher
import org.testatoo.hamcrest.matcher.property.TextMatcher
import org.testatoo.hamcrest.matcher.property.ValueMatcher
import org.testatoo.hamcrest.matcher.state.AvailableMatcher
import org.testatoo.hamcrest.matcher.state.CheckedMatcher
import org.testatoo.hamcrest.matcher.state.DisabledMatcher
import org.testatoo.hamcrest.matcher.state.EmptyMatcher
import org.testatoo.hamcrest.matcher.state.EnabledMatcher
import org.testatoo.hamcrest.matcher.state.FilledMatcher
import org.testatoo.hamcrest.matcher.state.HiddenMatcher
import org.testatoo.hamcrest.matcher.state.InRangeMatcher
import org.testatoo.hamcrest.matcher.state.InvalidMatcher
import org.testatoo.hamcrest.matcher.state.MissingMatcher
import org.testatoo.hamcrest.matcher.state.OptionalMatcher
import org.testatoo.hamcrest.matcher.state.OutOfRangeMatcher
import org.testatoo.hamcrest.matcher.state.ReadOnlyMatcher
import org.testatoo.hamcrest.matcher.state.RequiredMatcher
import org.testatoo.hamcrest.matcher.state.SelectedMatcher
import org.testatoo.hamcrest.matcher.state.UnCheckedMatcher
import org.testatoo.hamcrest.matcher.state.UnSelectedMatcher
import org.testatoo.hamcrest.matcher.state.ValidMatcher
import org.testatoo.hamcrest.matcher.state.VisibleMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Matchers {

    @Factory
    public static <T> Matcher<T> has(Matcher<T> matcher) { new Has(matcher) }

    // States
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
    public static Matcher<EmptySupport> empty() { new EmptyMatcher() }

    @Factory
    public static Matcher<FilledSupport> filled() { new FilledMatcher() }

    @Factory
    public static Matcher<ReadOnlySupport> readOnly() { new ReadOnlyMatcher() }

    @Factory
    public static Matcher<RequiredSupport> required() { new RequiredMatcher() }

    @Factory
    public static Matcher<OptionalSupport> optional() { new OptionalMatcher() }

    @Factory
    public static Matcher<CheckSupport> checked() { new CheckedMatcher() }

    @Factory
    public static Matcher<CheckSupport> unchecked() { new UnCheckedMatcher() }

    @Factory
    public static Matcher<SelectSupport> selected() { new SelectedMatcher() }

    @Factory
    public static Matcher<SelectSupport> unselected() { new UnSelectedMatcher() }

    @Factory
    public static Matcher<RangeSupport> inRange() { new InRangeMatcher() }

    @Factory
    public static Matcher<RangeSupport> outOfRange() { new OutOfRangeMatcher() }

    @Factory
    public static Matcher<ValiditySupport> valid() { new ValidMatcher() }

    @Factory
    public static Matcher<ValiditySupport> invalid() { new InvalidMatcher() }

    // Properties
    @Factory
    public static Matcher<LabelSupport> label(String label) { new LabelMatcher(label) }

    @Factory
    public static Matcher<InputSupport> placeholder(String placeholder) { new PlaceholderMatcher(placeholder) }

    @Factory
    public static Matcher<MaximumSupport> maximum(Object maximum) { new MaximumMatcher(maximum) }

    @Factory
    public static Matcher<MinimumSupport> minimum(Object minimum) { new MinimumMatcher(minimum) }

    @Factory
    public static Matcher<StepSupport> step(Object minimum) { new StepMatcher(minimum) }

    @Factory
    public static Matcher<TextSupport> text(String text) { new TextMatcher(text) }

    @Factory
    public static Matcher<ValueSupport> value(Object value) { new ValueMatcher(value) }
}
