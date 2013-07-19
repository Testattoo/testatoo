# Introduction

Testatoo is a web user interface testing tool. It's the result of numerous real-world observations of developers in the trenches in the area of GUI testing.
Working for many years to promote the TDD approaches, we often faced difficulties in their implementation for the graphical layer of applications.

The "test FIRST" principle excludes all scenario recorder based approaches that only allow you to write a posteriori tests.
Our experience has taught us that this path is a dead end (but we reserve this for another discussion...).

Another problem is GUI tests are brittle and costly! We do think that this is due to the lack of abstraction in existing UI testing tools.

Testatoo provides on one hand an abstraction of the UI business domain through an expressive API and on the other hand a way to express this domain via a DSL (a button semantically stays a buttons whatever the technology).
With Testatoo you can therefore write tests with a seldom achieved level of expressiveness and make these tests INDEPENDENT of the underlying technology.

Testatoo can therefore transform the test in real assets, present throughout the life of the application and always in tune with the latest version of the application.

## Technologies
 (TODO : check webdriver or selenium)
Testatoo is built on top of [WebDriver](http://code.google.com/p/selenium/), it can work [with any browser supported by WebDriver](http://http://code.google.com/p/selenium/wiki/FrequentlyAskedQuestions#Q:_Which_browsers_does_WebDriver_support?) (IE, Google-chrome, Firefox, ...).
Testatoo provides like [geb](http://www.gebish.org/testing) an extra layer of convenience and productivity, it is always possible to "fallback" to the WebDriver level to do something directly should you need to.
But Testatoo adds a powerful DSL while maintaining the usage of pure Java and keeps the advantage of a strong typed language.

Example of a test syntax :

    // assert that the button is visible
    assertThat(button(), is(visible()));

    // assert that a list of elements has size 2
    assertThat(elementList(), has(size(2)));

## Quick start

We will take a simple example and create test environment to run it.
The complete code sample is available in the directory sample/sample_1 of the documentation

### Specification

As a specifier I want to express my test (intention) BEFORE to code my page. So the intention is to have
a page with two radio buttons to select the gender (male or female). The test can be expressed like this :

        assert that the maleRadio is not checked
        and it has label "Male"

        assert that the femaleRadio is not checked
        and it has label "Female"

        check the maleRadio
        assert that the maleRadio is checked
        assert that the femaleRadio is not checked

        check the femaleRadio
        assert that the femaleRadio is checked
        assert that the maleRadio is not checked

### Import Testatoo librairies

Testatoo librairies can be added using maven, it looks like this: (TODO : commons-logging exclusion)

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit-dep</artifactId>
            <version>4.10</version>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.hamcrest</groupId>
                    <artifactId>hamcrest-core</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.testatoo</groupId>
            <artifactId>testatoo-selenium</artifactId>
            <version>2.31.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testatoo.cartridge</groupId>
            <artifactId>testatoo-html4</artifactId>
            <version>1.0-rc11</version>
            <scope>test</scope>
             <exclusions>
                <exclusion>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.testatoo</groupId>
            <artifactId>testatoo-config</artifactId>
            <version>1.0-rc4</version>
                <scope>test</scope>
        </dependency>
    </dependencies>

### Write the test

Thanks to Testatoo we can write our tests in a very expressive way  :

    public class SampleTest_1 {

        @Test
        public void sample_cool_DSL_testing_power() {
            assertThat(maleRadio(), is(not(checked())));
            and(it(), has(label("Male")));

            assertThat(femaleRadio(), is(not(checked())));
            and(it(), has(label("Female")));

            check(maleRadio());
            assertThat(maleRadio(), is(checked()));
            assertThat(femaleRadio(), is(not(checked())));

            check(femaleRadio());
            assertThat(femaleRadio(), is(checked()));
            assertThat(maleRadio(), is(not(checked())));
        }
    }

We define the type of our components.

    private Radio maleRadio() {
        return component(Radio.class, "male");
    }

    private Radio femaleRadio() {
        return component(Radio.class, "female");
    }

Finally, we put all of it in a class with the annotation @RunWith(TestatooJunitRunner.class) to use the Testatoo runner.

	import ...

	@RunWith(TestatooJunitRunner.class)
	public class SampleTest_1 {

		@Test
        public void sample_cool_DSL_testing_power() {
            assertThat(maleRadio(), is(not(checked())));
            assertThat(femaleRadio(), is(not(checked())));

            check(maleRadio());
            assertThat(maleRadio(), is(checked()));
            and(it(), has(label("Male")));

            assertThat(femaleRadio(), is(not(checked())));
            check(femaleRadio());
            assertThat(femaleRadio(), is(checked()));
            and(it(), has(label("Female")));

            assertThat(maleRadio(), is(not(checked())));
        }

        private Radio maleRadio() {
            return component(Radio.class, "male");
        }

        private Radio femaleRadio() {
            return component(Radio.class, "female");
        }
	}

### Configure Testatoo

Testatoo is configurable through modules

    @RunWith(TestatooJunitRunner.class)
    @TestatooModules(Module.class)
    public class SampleTest_1 {
        ...
    }

Module class allows Testatoo to be very configurable:

    public class Module extends AbstractTestatooModule {
        @Override
        protected void configure() {
            install(new ContainerModule());
            install(new SeleniumModule());
        }
    }

The container module allows to configure the web container:

    public class ContainerModule extends AbstractTestatooModule {
        @Override
        protected void configure() {
            containers().register(createContainer()
                    .implementedBy(JETTY)
                    .webappRoot("src/main/webapp")
                    .port(8080)
                    .build())
                    .scope(TEST_SUITE);
        }
    }

The selenium module allows to configure the Selenium server:

    public class SeleniumModule extends AbstractTestatooModule {
        @Override
        protected void configure() {
            int seleniumPort = findFreePort();

            seleniumServers().register(createSeleniumServer()
                    .port(seleniumPort)
                    .useSingleWindow(true)
                    .build())
                    .scope(Scope.TEST_SUITE);

            seleniumSessions().register(createSeleniumSession()
                    .website("http://localhost:8080")
                    .browser("*firefox")
                    .serverHost("localhost")
                    .serverPort(seleniumPort).build())
                    .scope(Scope.TEST_SUITE)
                    .withTimeout(20000)
                    .inCartridge(TestatooCartridge.HTML4);
        }
    }

### Run tests

Now you can run your test, Testatoo will open your browser and execute your test.


### Conclusion - first observations

Testatoo provides a DSL to write tests in a natural language easy to manipulate for a specifications writer.
As this is a high level language, it allows tests to be written before UI coding.
Testatoo allows configuration that facilitate embedded light container usage and Selenium usage.








