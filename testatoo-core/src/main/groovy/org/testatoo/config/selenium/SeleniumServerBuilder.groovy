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
package org.testatoo.config.selenium

import org.openqa.selenium.server.RemoteControlConfiguration
import org.openqa.selenium.server.SeleniumServer
import org.openqa.selenium.server.SslCertificateGenerator
import org.testatoo.config.Provider
import org.testatoo.config.ProviderBuilder

import java.util.logging.Level
import java.util.logging.Logger

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class SeleniumServerBuilder implements ProviderBuilder<SeleniumServer> {

    private final RemoteControlConfiguration seleniumServerConfiguration = new RemoteControlConfiguration()

    @Override
    public Provider<SeleniumServer> build() {
        return new Provider<SeleniumServer>() {
            @Override
            public SeleniumServer get() throws Throwable {
                if (!seleniumServerConfiguration.dontTouchLogging()) {
                    Logger.getLogger("org.openqa.selenium.server.SeleniumDriverResourceHandler").setLevel(Level.OFF)
                    Logger.getLogger("org.openqa.selenium.server.SeleniumServer").setLevel(Level.OFF)
                    Logger.getLogger("org.openqa.jetty").setLevel(Level.OFF)
                }
                return new SeleniumServer(seleniumServerConfiguration)
            }
        };
    }

    /* DELEGATES */
    public SeleniumServerBuilder setUserJSInjection(boolean useUserJSInjection) {
        seleniumServerConfiguration.setUserJSInjection(useUserJSInjection)
        return this
    }

    public SeleniumServerBuilder setAvoidProxy(boolean value) {
        seleniumServerConfiguration.setAvoidProxy(value)
        return this
    }

    public SeleniumServerBuilder setBrowserSideLogEnabled(boolean value) {
        seleniumServerConfiguration.setBrowserSideLogEnabled(value)
        return this
    }

    public SeleniumServerBuilder setBrowserTimeoutInMs(int browserTimeoutInMs) {
        seleniumServerConfiguration.setBrowserTimeoutInMs(browserTimeoutInMs)
        return this
    }

    public SeleniumServerBuilder setCaptureLogsOnQuit(boolean captureLogs) {
        seleniumServerConfiguration.setCaptureLogsOnQuit(captureLogs)
        return this
    }

    public SeleniumServerBuilder setDebugMode(boolean debugMode) {
        seleniumServerConfiguration.setDebugMode(debugMode)
        return this
    }

    public SeleniumServerBuilder setDebugURL(String newDebugURL) {
        seleniumServerConfiguration.setDebugURL(newDebugURL)
        return this
    }

    public SeleniumServerBuilder setDontInjectRegex(String newdontInjectRegex) {
        seleniumServerConfiguration.setDontInjectRegex(newdontInjectRegex)
        return this
    }

    public SeleniumServerBuilder setDontTouchLogging(boolean newValue) {
        seleniumServerConfiguration.setDontTouchLogging(newValue)
        return this
    }

    public SeleniumServerBuilder setEnsureCleanSession(boolean value) {
        seleniumServerConfiguration.setEnsureCleanSession(value)
        return this
    }

    public SeleniumServerBuilder setFirefoxProfileTemplate(File newFirefoxProfileTemplate) {
        seleniumServerConfiguration.setFirefoxProfileTemplate(newFirefoxProfileTemplate)
        return this
    }

    public SeleniumServerBuilder setForcedBrowserMode(String newForcedBrowserMode) {
        seleniumServerConfiguration.setForcedBrowserMode(newForcedBrowserMode)
        return this
    }

    public SeleniumServerBuilder setHonorSystemProxy(boolean willHonorSystemProxy) {
        seleniumServerConfiguration.setHonorSystemProxy(willHonorSystemProxy)
        return this
    }

    public SeleniumServerBuilder setHTMLSuite(boolean isHTMLSuite) {
        seleniumServerConfiguration.setHTMLSuite(isHTMLSuite)
        return this
    }

    public SeleniumServerBuilder setInteractive(boolean isInteractive) {
        seleniumServerConfiguration.setInteractive(isInteractive)
        return this
    }

    public SeleniumServerBuilder setJettyThreads(int jettyThreads) {
        seleniumServerConfiguration.setJettyThreads(jettyThreads)
        return this
    }

    public SeleniumServerBuilder setLogOutFile(File newLogOutFile) {
        seleniumServerConfiguration.setLogOutFile(newLogOutFile)
        return this
    }

    public SeleniumServerBuilder setLogOutFileName(String newLogOutFileName) {
        seleniumServerConfiguration.setLogOutFileName(newLogOutFileName)
        return this
    }

    public SeleniumServerBuilder setPort(int newPortNumber) {
        seleniumServerConfiguration.setPort(newPortNumber)
        return this
    }

    public SeleniumServerBuilder setPortDriversShouldContact(int newPortDriversShouldContact) {
        seleniumServerConfiguration.setPortDriversShouldContact(newPortDriversShouldContact)
        return this
    }

    public SeleniumServerBuilder setProfilesLocation(File profilesLocation) {
        seleniumServerConfiguration.setProfilesLocation(profilesLocation)
        return this
    }

    public SeleniumServerBuilder setProxyInjectionModeArg(boolean proxyInjectionModeArg) {
        seleniumServerConfiguration.setProxyInjectionModeArg(proxyInjectionModeArg)
        return this
    }

    public SeleniumServerBuilder setRetryTimeoutInSeconds(int newRetryTimeoutInSeconds) {
        seleniumServerConfiguration.setRetryTimeoutInSeconds(newRetryTimeoutInSeconds)
        return this
    }

    public SeleniumServerBuilder setReuseBrowserSessions(boolean reuseBrowserSessions) {
        seleniumServerConfiguration.setReuseBrowserSessions(reuseBrowserSessions)
        return this
    }

    public SeleniumServerBuilder setSeleniumServer(SslCertificateGenerator server) {
        seleniumServerConfiguration.setSeleniumServer(server)
        return this
    }

    public SeleniumServerBuilder setSelfTest(boolean isSelftest) {
        seleniumServerConfiguration.setSelfTest(isSelftest)
        return this
    }

    public SeleniumServerBuilder setSelfTestDir(File newSelfTestDir) {
        seleniumServerConfiguration.setSelfTestDir(newSelfTestDir)
        return this
    }

    public SeleniumServerBuilder setSingleWindow(boolean useSingleWindow) {
        seleniumServerConfiguration.setSingleWindow(useSingleWindow)
        return this
    }

    public SeleniumServerBuilder setTimeoutInSeconds(int newTimeoutInSeconds) {
        seleniumServerConfiguration.setTimeoutInSeconds(newTimeoutInSeconds)
        return this
    }

    public SeleniumServerBuilder setTrustAllSSLCertificates(boolean trustAllSSLCertificates) {
        seleniumServerConfiguration.setTrustAllSSLCertificates(trustAllSSLCertificates)
        return this
    }

    public SeleniumServerBuilder setUserExtensions(File newuserExtensions) {
        seleniumServerConfiguration.setUserExtensions(newuserExtensions)
        return this
    }

}
