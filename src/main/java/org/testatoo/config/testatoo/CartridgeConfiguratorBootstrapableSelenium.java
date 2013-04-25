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
package org.testatoo.config.testatoo;

import org.testatoo.core.CartridgeBootstraper;
import org.testatoo.core.EvaluatorHolder;

import java.util.Map;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class CartridgeConfiguratorBootstrapableSelenium implements CartridgeConfigurator {

    private static final Logger LOGGER = Logger.getLogger(CartridgeConfiguratorBootstrapableSelenium.class.getName());

    @Override
    public void register(Map<String, ?> params) {
        // only one cartridge per classpath, find first working evaluator
        boolean found = false;
        for (CartridgeBootstraper bootstraper : ServiceLoader.load(CartridgeBootstraper.class)) {
            org.testatoo.core.Evaluator evaluator = bootstraper.buildEvaluator(params);
            if (evaluator != null) {
                found = true;
                if (LOGGER.isLoggable(Level.INFO))
                    LOGGER.info(String.format("Built evaluator %s with cartridge %s for parameters: %s", evaluator.getClass().getName(), bootstraper.getClass().getName(), params));
                EvaluatorHolder.register(evaluator);
            }
        }
        if (!found)
            throw new IllegalStateException("No CartridgeBootstraper service found in classpath. Be sure you have the good cartridge supporting Selenium in your classpath.");
    }

    @Override
    public void unregister(Map<String, ?> params) {
        EvaluatorHolder.unregister((String) params.get("name"));
    }
}