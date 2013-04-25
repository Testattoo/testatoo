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

import org.testatoo.config.Provider;
import org.testatoo.config.selenium.SeleniumServerBuilder;
import org.testatoo.selenium.server.SeleniumServer;
import org.testatoo.selenium.server.SeleniumServerFactory;

import java.io.File;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class DefaultSeleniumServerBuilder implements SeleniumServerBuilder {

    private final org.testatoo.selenium.server.SeleniumServerBuilder seleniumServerConfiguration = SeleniumServerFactory.configure();

    @Override
    public Provider<SeleniumServer> build() {
        return new Provider<SeleniumServer>() {
            @Override
            public SeleniumServer get() {
                return seleniumServerConfiguration.create();
            }
        };
    }

    /* DELEGATES */

    @Override
    public SeleniumServerBuilder port(int newPortNumber) {
        seleniumServerConfiguration.setPort(newPortNumber);
        return this;
    }

    @Override
    public SeleniumServerBuilder useSingleWindow(boolean useSingleWindow) {
        seleniumServerConfiguration.setSingleWindow(useSingleWindow);
        return this;
    }

    @Override
    public SeleniumServerBuilder profilesLocation(File profilesLocation) {
        seleniumServerConfiguration.setProfilesLocation(profilesLocation);
        return this;
    }

    @Override
    public SeleniumServerBuilder proxyInjectionModeArg(boolean proxyInjectionModeArg) {
        seleniumServerConfiguration.setProxyInjectionModeArg(proxyInjectionModeArg);
        return this;
    }

    @Override
    public SeleniumServerBuilder portDriversShouldContact(int newPortDriversShouldContact) {
        seleniumServerConfiguration.setPortDriversShouldContact(newPortDriversShouldContact);
        return this;
    }

    @Override
    public SeleniumServerBuilder htmlSuite(boolean isHTMLSuite) {
        seleniumServerConfiguration.setHTMLSuite(isHTMLSuite);
        return this;
    }

    @Override
    public SeleniumServerBuilder selfTest(boolean isSelftest) {
        seleniumServerConfiguration.setSelfTest(isSelftest);
        return this;
    }

    @Override
    public SeleniumServerBuilder selfTestDir(File newSelfTestDir) {
        seleniumServerConfiguration.setSelfTestDir(newSelfTestDir);
        return this;
    }

    @Override
    public SeleniumServerBuilder interactive(boolean isInteractive) {
        seleniumServerConfiguration.setInteractive(isInteractive);
        return this;
    }

    @Override
    public SeleniumServerBuilder userExtensions(File newuserExtensions) {
        seleniumServerConfiguration.setUserExtensions(newuserExtensions);
        return this;
    }

    @Override
    public SeleniumServerBuilder userJSInjection(boolean useUserJSInjection) {
        seleniumServerConfiguration.setUserJSInjection(useUserJSInjection);
        return this;
    }

    @Override
    public SeleniumServerBuilder trustAllSSLCertificates(boolean trustAllSSLCertificates) {
        seleniumServerConfiguration.setTrustAllSSLCertificates(trustAllSSLCertificates);
        return this;
    }

    @Override
    public SeleniumServerBuilder debugURL(String newDebugURL) {
        seleniumServerConfiguration.setDebugURL(newDebugURL);
        return this;
    }

    @Override
    public SeleniumServerBuilder debugMode(boolean debugMode) {
        seleniumServerConfiguration.setDebugMode(debugMode);
        return this;
    }

    @Override
    public SeleniumServerBuilder dontInjectRegex(String newdontInjectRegex) {
        seleniumServerConfiguration.setDontInjectRegex(newdontInjectRegex);
        return this;
    }

    @Override
    public SeleniumServerBuilder firefoxProfileTemplate(File newFirefoxProfileTemplate) {
        seleniumServerConfiguration.setFirefoxProfileTemplate(newFirefoxProfileTemplate);
        return this;
    }

    @Override
    public SeleniumServerBuilder reuseBrowserSessions(boolean reuseBrowserSessions) {
        seleniumServerConfiguration.setReuseBrowserSessions(reuseBrowserSessions);
        return this;
    }

    @Override
    public SeleniumServerBuilder logOutFileName(String newLogOutFileName) {
        seleniumServerConfiguration.setLogOutFileName(newLogOutFileName);
        return this;
    }

    @Override
    public SeleniumServerBuilder logOutFile(File newLogOutFile) {
        seleniumServerConfiguration.setLogOutFile(newLogOutFile);
        return this;
    }

    @Override
    public SeleniumServerBuilder forcedBrowserMode(String newForcedBrowserMode) {
        seleniumServerConfiguration.setForcedBrowserMode(newForcedBrowserMode);
        return this;
    }

    @Override
    public SeleniumServerBuilder honorSystemProxy(boolean willHonorSystemProxy) {
        seleniumServerConfiguration.setHonorSystemProxy(willHonorSystemProxy);
        return this;
    }

    @Override
    public SeleniumServerBuilder timeoutInSeconds(int newTimeoutInSeconds) {
        seleniumServerConfiguration.setTimeoutInSeconds(newTimeoutInSeconds);
        return this;
    }

    @Override
    public SeleniumServerBuilder retryTimeoutInSeconds(int newRetryTimeoutInSeconds) {
        seleniumServerConfiguration.setRetryTimeoutInSeconds(newRetryTimeoutInSeconds);
        return this;
    }

    @Override
    public SeleniumServerBuilder dontTouchLogging(boolean newValue) {
        seleniumServerConfiguration.setDontTouchLogging(newValue);
        return this;
    }

    @Override
    public SeleniumServerBuilder ensureCleanSession(boolean value) {
        seleniumServerConfiguration.setEnsureCleanSession(value);
        return this;
    }

    @Override
    public SeleniumServerBuilder useSeleniumServerConfigurationProxy(boolean value) {
        seleniumServerConfiguration.setASeleniumServerConfigurationProxy(value);
        return this;
    }

    @Override
    public SeleniumServerBuilder browserSideLogEnabled(boolean value) {
        seleniumServerConfiguration.setBrowserSideLogEnabled(value);
        return this;
    }

    @Override
    public SeleniumServerBuilder jettyThreads(int jettyThreads) {
        seleniumServerConfiguration.setJettyThreads(jettyThreads);
        return this;
    }

    @Override
    public String toString() {
        return seleniumServerConfiguration.toString();
    }
}
