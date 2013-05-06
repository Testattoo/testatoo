package org.testatoo.experimental.dsl

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2013-05-05
 */
class Blocks {

    private static final Queue<Block> BLOCKS = new LinkedList<>()

    static Block block(String description, Closure<?> c) {
        Block b = null
        b = [
            run: {
                try {
                    c()
                } finally {
                    BLOCKS.remove(b)
                }
            },
            toString: { description }
        ] as Block
        BLOCKS.offer(b)
        return b
    }

    static Collection<Block> pending() {
        Queue<Block> blks = new LinkedList<>()
        while (BLOCKS) {
            blks.offer(BLOCKS.poll())
        }
        return blks
    }

    static Block compose(Collection<Block> blocks) {
        Block b = null
        b = [
            run: {
                try {
                    blocks*.run()
                } finally {
                    BLOCKS.remove(b)
                }
            },
            toString: { blocks.collect { it as String }.join('\n') }
        ] as Block
        BLOCKS.offer(b)
        return b

    }

}
