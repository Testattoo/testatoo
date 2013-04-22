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
package org.testatoo.config.env;

import org.testatoo.config.AbstractTestatooModule;
import org.testatoo.config.Scope;
import org.testatoo.config.cartridge.TestatooCartridge;

final class HudsonModule extends AbstractTestatooModule {

    @Override
    protected void configure() {
        seleniumSessions()
                .registerProvider(createSeleniumSession()
                    .website("http://dev.ovea.com:7896/")
                    .browser("*googlechrome")
                    .serverHost("192.168.236.51")
                    .serverPort(4444)
                    .build())
                .scope(Scope.TEST_CLASS)
                .withTimeout(20000)
                .inCartridge(TestatooCartridge.HTML4);
    }
}