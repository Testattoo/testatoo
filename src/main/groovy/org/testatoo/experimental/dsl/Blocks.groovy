/**
 * Copyright (C) 2008 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
