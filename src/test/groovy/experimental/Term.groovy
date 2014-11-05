package experimental

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2014-11-04
 */
class Term {

    static Term enabled = new Term()

    static Term should = new Term()

    static Term label = new Term()

    static Term be(Term term) { term }
    static Term is(Term term) { term }

    static Term have(Term term) { term }

    static Term containing(String s) { new Term() }

}
