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
package org.testatoo.config.selenium;

import org.testatoo.config.ProviderBuilder;
import org.testatoo.selenium.server.SeleniumServer;

import java.io.File;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public interface SeleniumServerBuilder extends ProviderBuilder<SeleniumServer> {

    SeleniumServerBuilder port(int newPortNumber);

    SeleniumServerBuilder useSingleWindow(boolean useSingleWindow);

    SeleniumServerBuilder profilesLocation(File profilesLocation);

    SeleniumServerBuilder proxyInjectionModeArg(boolean proxyInjectionModeArg);

    SeleniumServerBuilder portDriversShouldContact(int newPortDriversShouldContact);

    SeleniumServerBuilder htmlSuite(boolean isHTMLSuite);

    SeleniumServerBuilder selfTest(boolean isSelftest);

    SeleniumServerBuilder selfTestDir(File newSelfTestDir);

    SeleniumServerBuilder interactive(boolean isInteractive);

    SeleniumServerBuilder userExtensions(File newuserExtensions);

    SeleniumServerBuilder userJSInjection(boolean useUserJSInjection);

    SeleniumServerBuilder trustAllSSLCertificates(boolean trustAllSSLCertificates);

    SeleniumServerBuilder debugURL(String newDebugURL);

    SeleniumServerBuilder debugMode(boolean debugMode);

    SeleniumServerBuilder dontInjectRegex(String newdontInjectRegex);

    SeleniumServerBuilder firefoxProfileTemplate(File newFirefoxProfileTemplate);

    SeleniumServerBuilder reuseBrowserSessions(boolean reuseBrowserSessions);

    SeleniumServerBuilder logOutFileName(String newLogOutFileName);

    SeleniumServerBuilder logOutFile(File newLogOutFile);

    SeleniumServerBuilder forcedBrowserMode(String newForcedBrowserMode);

    SeleniumServerBuilder honorSystemProxy(boolean willHonorSystemProxy);

    SeleniumServerBuilder timeoutInSeconds(int newTimeoutInSeconds);

    SeleniumServerBuilder retryTimeoutInSeconds(int newRetryTimeoutInSeconds);

    SeleniumServerBuilder dontTouchLogging(boolean newValue);

    SeleniumServerBuilder ensureCleanSession(boolean value);

    SeleniumServerBuilder useSeleniumServerConfigurationProxy(boolean value);

    SeleniumServerBuilder browserSideLogEnabled(boolean value);

    SeleniumServerBuilder jettyThreads(int jettyThreads);
}
