package org.testatoo.experimental.dsl
/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-01
 */
class Testatoo {

    // settings
    Evaluator evaluator = new LegacyEvaluator()

    // matchers
    final Matcher visible = { Component c ->
        Assert.ensure c, evaluator.isVisible(c), [e: 'visible', w: 'hidden']
    } as Matcher

    final Matcher hidden = { Component c ->
        Assert.ensure c, !evaluator.isVisible(c), [e: 'hidden', w: 'visible']
    } as Matcher

    // dsl

    Component $(String jQuery, long timeout = 2000) { new Component(evaluator: evaluator, id: new jQueryId(jQuery, timeout)) }

    void open(String uri) { evaluator.open(uri) }

    void assertThat(Block m) { run(m) }

    void on(Block m) { run(m) }

    void assertThat(Collection<Block> blocks) { run(Blocks.compose(blocks)) }

    void assertThat(Closure<?> c) {
        c()
        run(Blocks.compose(Blocks.pending()))
    }

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

    // attributes

    LabelAttribute getLabel() { new LabelAttribute(evaluator) }

    TextAttribute getText() { new TextAttribute(evaluator) }

    // utils

    private void run(Block block) {
        println block
        block.run()
    }

}
