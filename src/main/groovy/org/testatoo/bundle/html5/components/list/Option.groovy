package org.testatoo.bundle.html5.components.list

import org.testatoo.core.ByCss
import org.testatoo.core.ComponentException
import org.testatoo.core.component.Item

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */

@ByCss('option')
class Option extends Item {

    @Override
    boolean isSelected() {
        config.evaluator.check(id, "!!it.prop('selected')")
    }

    @Override
    boolean isUnselected() {
        !selected
    }

    @Override
    Object getValue() {
        config.evaluator.eval(id, "it.attr('label') && it.attr('label').length > 0 ? it.attr('label') : it.text().trim()")
    }

    @Override
    boolean isEnabled() {
        !disabled
    }

    @Override
    boolean isDisabled() {
        config.evaluator.check(id, "el.is(':disabled') || el.attr('disabled') != undefined || el.closest('select').is(':disabled')")
    }

    boolean equals(o) {
        if (this.is(o)) return true
        return value == o.value
    }

    @Override
    String toString() {
        return value
    }

    @Override
    void select() {
        if(this.disabled)
            throw new ComponentException("${this.class.simpleName} ${this} is disabled and cannot be selected")
        if (this.unselected)
            this.click()
        else
            throw new ComponentException("${this.class.simpleName} ${this} is already selected and cannot be selected")
    }

    @Override
    void unselect() {
        if(this.disabled)
            throw new ComponentException("${this.class.simpleName} ${this} is disabled and cannot be unselected")
        if (this.selected)
            this.click()
        else
            throw new ComponentException("${this.class.simpleName} ${this} is already unselected and cannot be unselected")
    }
}
