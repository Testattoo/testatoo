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

class EventListener {

    private final int priority;

    EventListener(Priority priority) {
        this.priority = priority.next();
    }

    void on(Event event) throws Throwable {
        switch (event) {
            case START:
                onStart();
                break;
            case STOP:
                onStop();
                break;
        }
    }

    public final int priority() {
        return priority;
    }

    void onStart() throws Throwable {
    }

    void onStop() throws Throwable {
    }
}
