package org.testatoo.experimental.dsl
/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-01
 */
class Testatoo {

    final Evaluator evaluator = new LegacyEvaluator()

    final Matcher visible = { Component c ->
        Assert.ensure c, evaluator.isVisible(c), [e: 'visible', w: 'hidden']
    } as Matcher

    void assertThat(Block m) { m.run() }

    void assertThat(Collection<Block> ms) { ms*.run() }

    void assertThat(Closure<?> c) { c() }

    void open(String uri) { evaluator.open(uri) }

    void waitUntil(Block m, long timeout = 5000) {
        //TODO: support better optional params for timeout
        final long step = 500
        Throwable ex = null
        try {
            for (; timeout > 0; timeout -= step) {
                try {
                    m.run()
                    return
                } catch (Throwable e) {
                    ex = e
                }
                Thread.sleep(step)
            }
        } catch (InterruptedException iex) {
            throw new RuntimeException("Interrupted exception", iex)
        }
        throw new RuntimeException("Unable to reach the condition in 5 seconds", ex)
    }

    Component $(String jQuery, long timeout = 2000) { new Component(new jQueryId(evaluator, jQuery, timeout)) }

    Label getLabel() { new Label(evaluator) }

}
