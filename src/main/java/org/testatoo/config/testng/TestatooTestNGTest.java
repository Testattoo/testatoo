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
package org.testatoo.config.testng;

import org.aopalliance.intercept.MethodInvocation;
import org.testatoo.config.annotation.TestatooModules;
import org.testatoo.config.testatoo.Testatoo;
import org.testng.Assert;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

public abstract class TestatooTestNGTest extends Assert implements IHookable {

    private Testatoo testatoo;

    @BeforeClass
    public final void startTestatoo() throws Throwable {
        if (testatoo == null) {
            testatoo = Testatoo.configure(getClass().getAnnotation(TestatooModules.class));
            testatoo.start();
        }
    }

    @AfterClass
    public final void stopTestatoo() throws Throwable {
        if (testatoo != null) {
            testatoo.stop();
            testatoo = null;
        }
    }

    @Override
    public final void run(final IHookCallBack callBack, final ITestResult testResult) {

        // the code below is necessary since TestNG is very bad designed and don't allow
        // to recover the test instance. When running in paralell mode, there could be
        // multiple test instances, which are not necessarly 'this'.
        final Object testInstance;
        try {
            Field field = callBack.getClass().getDeclaredField("val$instance");
            field.setAccessible(true);
            testInstance = field.get(callBack);
        } catch (Exception e) {
            throw new AssertionError(e.getMessage());
        }

        try {
            testatoo.executeTestMethod(new MethodInvocation() {
                @Override
                public Method getMethod() {
                    return testResult.getMethod().getMethod();
                }

                @Override
                public Object[] getArguments() {
                    return new Object[0];
                }

                @Override
                public Object proceed() throws Throwable {
                    testatoo.on(testInstance, testResult.getMethod().getMethod());
                    callBack.runTestMethod(testResult);
                    return null;
                }

                @Override
                public Object getThis() {
                    return testInstance;
                }

                @Override
                public AccessibleObject getStaticPart() {
                    return testResult.getMethod().getMethod();
                }
            });
        } catch (Throwable t) {
            if (t instanceof InvocationTargetException) {
                t = ((InvocationTargetException) t).getTargetException();
            }
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            throw new RuntimeException(t.getMessage(), t);
        }
    }
}
