package experimental.dsl.todo

import org.junit.Test

import org.testatoo.core.component.Button

import static org.testatoo.core.Testatoo.$

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class CustomComponentTest {

    @Test
    public void new_dsl() {
        Button button = $('#button') as Button
        MyProp myProp = new MyProp()
        MyState myState = new MyState()

        // are / have extension
        assert [button].are(enabled)

        // custom components
        MyPanel myPanel = $('#panel') as MyPanel
        // existing property, evaluation overriden
        assert myPanel.has(title.equalsTo('temp2')).and(myPanel.has(myProp.equalsTo('temp1')))
        // new property
        assert myPanel.is(myState)
    }
}
