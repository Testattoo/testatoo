package org.testatoo.hamcrest.matcher.state

import org.hamcrest.Description
import org.testatoo.core.Evaluator
import org.testatoo.core.component.Component
import org.testatoo.hamcrest.StateMatcher

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class ContainMatcher extends StateMatcher<Component> {
    private List<Component> components = new ArrayList<>()
    private Component container
    private Evaluator evaluator
    private List ret

    ContainMatcher(Evaluator evaluator, Component... components) {
        this.evaluator = evaluator
        this.components.addAll(components)
    }

    @Override
    protected boolean matchesSafely(Component container, Description mismatchDescription) {
        this.container = container
        ret = evaluator.getJson("\$().testatoo({method: 'contains', id:'${container.id()}', ids: [${components.collect { "'${it.id()}'" }.join(', ')}]});")
        mismatchDescription.appendText("does not contains expected component(s): ${components.findAll { it.id() in ret }}")
        ret.empty
    }

    @Override
    void describeTo(Description description) {

        description.appendText("Component ${container} contains ${components}")
    }
}
