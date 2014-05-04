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

    ListView reproachesList = $('[data-role=reproaches-list]') as ListView

    Warning first_warning = $('[data-role=first-warning]') as Warning
    Warning second_warning = $('[data-role=second-warning]') as Warning
    Warning third_warning = $('[data-role=third-warning]') as Warning

    Heading first_conclusion = $('[data-role=first-conclusion]') as Heading
    Conclusion second_conclusion = $('[data-role=second-conclusion]') as Conclusion
    Conclusion third_conclusion = $('[data-role=third-conclusion]') as Conclusion

    Conclusion last_conclusion = $('[data-role=last-conclusion]') as Conclusion

    Heading lens = $('[data-role=lens]') as Heading
    Heading recap = $('[data-role=recap]') as Heading
}
