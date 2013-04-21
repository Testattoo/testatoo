/**
 * Copyright (C) 2013 Ovea <dev@testatoo.org>
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
package org.testatoo.config.testatoo;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

enum Priority {

    CONTAINER(10000),
    SELENIUM_SERVER(20000),
    IMPLEMENTATION(30000),
    CARTRIDGE(40000),
    LIFECYCLE(50000),
    CONCURRENT(60000);

    private int start;

    private Priority(int start) {
        this.start = start;
    }

    public int next() {
        return start++;
    }
}
