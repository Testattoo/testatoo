/**
 * Copyright (C) 2014 Ovea (dev@ovea.com)
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
package experimental

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 * @date 2014-11-04
 */
class Term extends Closure<Term> {

    static Term enabled  = { it } as Term
    static Term should = { it } as Term
    static Term label = { it } as Term
    static Term be = { it } as Term
    static Term is = { it } as Term
    static Term have = { it } as Term
    static Term not = { it } as Term
    static Term containing = { it } as Term

    Term(Object owner, Object thisObject) {
        super(owner, thisObject)
    }

    Term(Object owner) {
        super(owner)
    }
}
