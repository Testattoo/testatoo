/**
 * Copyright (C) 2008 Ovea <dev@testatoo.org>
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

package org.testatoo.html;

import com.thoughtworks.selenium.Selenium;
import org.testatoo.cartridge.html4.evaluator.selenium.SeleniumHtmlEvaluator;
import org.testatoo.core.CartridgeBootstraper;
import org.testatoo.core.Evaluator;
import org.testatoo.html.evaluator.SeleniumEvaluator;

import java.util.Map;

public final class Bootstraper implements CartridgeBootstraper {

    @Override
    public Evaluator buildEvaluator(Map<String, ?> params) {
        String name = (String) params.get("name");
        Selenium session = (Selenium) params.get(Selenium.class.getName());
        if (name != null && session != null) {
            return new SeleniumEvaluator(name, session);
        }
        return null;
    }
}
