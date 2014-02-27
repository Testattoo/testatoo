/**
 * Copyright (C) 2008 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo.core
/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
class Blocks {

    private static final Queue<Block> BLOCKS = new LinkedList<>()

    static Block block(String description, Closure c) {
        Block b = null
        b = new Block() {
            @Override
            void run() {
                try {
                    c()
                } finally {
                    BLOCKS.remove(b)
                }
            }

            @Override
            String toString() { description }
        }
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

    static Block and(Collection<Block> blocks) {
        if (blocks.empty) {
            // TODO Math how to pass here (code coverage)
            throw new IllegalArgumentException('Empty block')
        }
        BLOCKS.removeAll(blocks)
        Block b = null
        b = new Block() {
            @Override
            void run() {
                try {
                    blocks*.run()
                } finally {
                    BLOCKS.remove(b)
                }
            }

            @Override
            String toString() { blocks.collect { it as String }.join(' AND') }
        }
        BLOCKS.offer(b)
        return b
    }

    static Block or(Collection<Block> blocks) {
        if (blocks.empty) {
            // TODO Math how to pass here (code coverage)
            throw new IllegalArgumentException('Empty block')
        }
        BLOCKS.removeAll(blocks)
        Block b = null
        b = new Block() {
            @Override
            void run() {
                try {
                    Throwable last = null
                    Block succeed = blocks.find {
                        try {
                            it.run()
                            return true
                        } catch (Throwable t) {
                            last = t
                            return false
                        }
                    }
                    if (succeed == null) {
                        throw last
                    }
                } finally {
                    BLOCKS.remove(b)
                }
            }

            @Override
            String toString() { blocks.collect { it as String }.join(' OR ') }
        }
        BLOCKS.offer(b)
        return b
    }

    static void run(Block b) {
        Log.testatoo b.toString()
        b.run()
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
