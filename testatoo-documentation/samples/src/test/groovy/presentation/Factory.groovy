package presentation

import org.testatoo.core.Testatoo
import org.testatoo.core.component.Heading
import org.testatoo.core.component.Image
import org.testatoo.core.component.list.ListView

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Factory extends Testatoo {
    Presentation presentation = $('[data-role=slides]') as Presentation

    Image testatoo_logo = $('#logo') as Image

    Teaser first_teaser = $('[data-role=first_teaser]') as Teaser
    Teaser second_teaser = $('[data-role=second_teaser]') as Teaser

    ListView reproaches_list = $('[data-role=reproaches-list]') as ListView
    ListView rethink_list = $('[data-role=rethink-list]') as ListView

    Heading first_conclusion = $('[data-role=first-conclusion]') as Heading

    Conclusion second_conclusion = $('[data-role=second-conclusion]') as Conclusion

    Heading lens = $('[data-role=lens]') as Heading
    Heading recap = $('[data-role=recap]') as Heading

    Heading philosophy_title = $('[data-role=philosophy-title]') as Heading
    Heading practice_title = $('[data-role=practice-title]') as Heading

    Heading term_title = $('[data-role=term-title]') as Heading
    ListView terms_list = $('[data-role=terms-list]') as ListView

    TextMessage what_message = $('[data-role=what]') as TextMessage
    TextMessage how_message = $('[data-role=how]') as TextMessage



}
