import conf.Module;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.testatoo.config.annotation.TestatooModules;
import org.testatoo.config.junit.TestatooJunitRunner;
import org.testatoo.core.component.Radio;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.testatoo.core.ComponentFactory.component;
import static org.testatoo.core.ComponentFactory.page;
import static org.testatoo.core.Language.*;
import static org.testatoo.core.matcher.Matchers.*;

@RunWith(TestatooJunitRunner.class)
@TestatooModules(Module.class)
public class SampleTest_1 {

    @BeforeClass
    public static void setUp() {
        page().open("index.html");
    }

    @Test
    public void sample_cool_DSL_testing_power() {
        assertThat(maleRadio(), is(not(checked())));
        and(it(), has(label("Male")));

        assertThat(femaleRadio(), is(not(checked())));
        and(it(), has(label("Female")));

        check(maleRadio());
        assertThat(maleRadio(), is(checked()));
        assertThat(femaleRadio(), is(not(checked())));

        check(femaleRadio());
        assertThat(femaleRadio(), is(checked()));
        assertThat(maleRadio(), is(not(checked())));
    }

    private Radio maleRadio() {
        return component(Radio.class, "male");
    }

    private Radio femaleRadio() {
        return component(Radio.class, "female");
    }

}
