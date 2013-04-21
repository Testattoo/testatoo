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
package org.testatoo.config.cartridge;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

import static org.testatoo.config.cartridge.TestatooEvaluator.SELENIUM;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

/**
 * Define all supported Testatoo Cartridges, and for each cartridges, list all available
 * implementations.
 */
public enum TestatooCartridge {

    /**
     * HTML4 Cartridge. Supports selenium and WebDriver implementations
     */
    HTML4(SELENIUM),

    /**
     * ExtJS 2 Cartridge. Supports selenium and WebDriver implementations
     */
    EXTJS2(SELENIUM),

    /**
     * ExtJS 3 Cartridge. Supports selenium and WebDriver implementations
     */
    EXTJS3(SELENIUM),

    /**
     * Yahoo UI 2 Cartridge. Supports selenium and WebDriver implementations
     */
    YUI2(SELENIUM),

    /**
     * Yahoo UI 3 Cartridge. Supports selenium and WebDriver implementations
     */
    YUI3(SELENIUM),

    /**
     * Flex 3 Cartridge. Supports selenium and WebDriver implementations
     */
    FLEX3(SELENIUM),

    /**
     * Flex 4 Cartridge. Supports selenium and WebDriver implementations
     */
    FLEX4(SELENIUM);

    private final Set<TestatooEvaluator> supportedEvaluators;

    private TestatooCartridge(TestatooEvaluator... supportedEvaluators) {
        this.supportedEvaluators = EnumSet.copyOf(Arrays.asList(supportedEvaluators));
    }

    /**
     * Check whether this cartridge supports the given implementation
     *
     * @param evaluator The implementation to check
     * @return true if the implementation is supported by this cartridge
     */
    public boolean supports(TestatooEvaluator evaluator) {
        return supportedEvaluators.contains(evaluator);
    }
}
