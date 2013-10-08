import org.testatoo.core.component.Button
import org.testatoo.core.property.Text

import static org.testatoo.core.Testatoo.$
import static org.testatoo.core.Testatoo.assertThat

Button button = $('#button_4') as Button

Text text = new Text()

assertThat button has text.equalsTo('Button')

