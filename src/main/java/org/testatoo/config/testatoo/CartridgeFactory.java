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

import org.testatoo.config.cartridge.TestatooCartridge;
import org.testatoo.config.cartridge.TestatooEvaluator;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class CartridgeFactory {

    private CartridgeFactory() {
    }

    static CartridgeConfigurator get(TestatooCartridge cartridge, TestatooEvaluator evaluator) {
        if (!cartridge.supports(evaluator)) {
            throw new IllegalStateException("Cartridge " + cartridge + " does not support evaluator " + evaluator);
        }
        switch (cartridge) {
            case HTML4:
                switch (evaluator) {
                    case SELENIUM:
                        return new CartridgeConfiguratorBootstrapableSelenium();
                }
            case EXTJS2:
                switch (evaluator) {
                    case SELENIUM:
                        return new CartridgeConfiguratorBootstrapableSelenium();
                }
            case EXTJS3:
                switch (evaluator) {
                    case SELENIUM:
                        return new CartridgeConfiguratorBootstrapableSelenium();
                }
            case YUI2:
                switch (evaluator) {
                    case SELENIUM:
                        return new CartridgeConfiguratorBootstrapableSelenium();
                }
            case YUI3:
                switch (evaluator) {
                    case SELENIUM:
                        return new CartridgeConfiguratorBootstrapableSelenium();
                }
            case FLEX3:
                switch (evaluator) {
                    case SELENIUM:
                        return new CartridgeConfiguratorBootstrapableSelenium();
                }
            case FLEX4:
                switch (evaluator) {
                    case SELENIUM:
                        return new CartridgeConfiguratorBootstrapableSelenium();
                }
        }
        throw new AssertionError("THERE IS AN ISSUE IN TESTATOO DSL. Please report the bug ! There is a missing configuration for cartridge " + cartridge + " and evaluator " + evaluator);
    }

}
