### Testatoo libraries

Until now, we used Testatoo to write our tests, but to do this, we have to use JUnit and Testatoo libraries.

Testatoo-core library defines the DSL and the API of Testatoo.
Testatoo-html4 is the implementation to test html web sites. It includes Testatoo-core and Hamcrest libraries.
Testatoo-config provides simplifications to configure Testatoo.
Testatoo-selenium is selenium library modified to work with Testatoo. Indeed, Testatoo uses selenium as low level controller to manipulate browser.

Using maven, it looks like this.

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
        </dependency>
        <dependency>
            <groupId>org.testatoo</groupId>
            <artifactId>testatoo-config</artifactId>
            <version>1.0-rc4</version>
                <scope>test</scope>
        </dependency>
    </dependencies>

### Testatoo configuration

Now, we have our libraries included and our test written. It remains for us to configure Testatoo.
To do that, we have to create a Testatoo configuration module in which we will create selenium client and server.

	public class Module extends AbstractTestatooModule {
		@Override
		protected void configure() {
			...
		}
	}

In first time, we have to define the port that will be used by selenium.

	int seleniumPort = findFreePort();

Then we create a selenium server.
In it, we will define the following pameters:
- The port,
- The necessity to open a new window for each test or not (useSingleWindow method),
- The scope to define if the server is scoped on test suite or on test class (scope method).

	seleniumServers().register(createSeleniumServer()
		.port(seleniumPort)
		.useSingleWindow(true)
		.build())
		.scope(Scope.TEST_SUITE);

We will also create a selenium session.
In it, we will define others parameters :
- The web site we will test (here www.google.com),
- The browser to use (here firefox),
- The selenium host and port,
- The scope, as in selenium server creation,
- The timeout for response,
- The cartridge we want to use (here, the one for html4 testing).

	seleniumSessions().register(createSeleniumSession()
		.website("http://www.google.com/")
		.browser("*firefox")
		.serverHost("localhost")
		.serverPort(seleniumPort).build())
		.scope(Scope.TEST_SUITE)
		.withTimeout(20000)
		.inCartridge(TestatooCartridge.HTML4);

We obtain a class like this

	import ...
	public class Module extends AbstractTestatooModule {
		@Override
		protected void configure() {
			int seleniumPort = findFreePort();

			seleniumServers().register(createSeleniumServer()
				.port(seleniumPort)
				.useSingleWindow(true)
				.build())
				.scope(Scope.TEST_SUITE);

			seleniumSessions().register(createSeleniumSession()
				.website("http://www.google.com/")
				.browser("*firefox")
				.serverHost("localhost")
				.serverPort(seleniumPort).build())
				.scope(Scope.TEST_SUITE)
				.withTimeout(20000)
				.inCartridge(TestatooCartridge.HTML4);
		}
	}

It's also possible to load other modules using install method:

    @Override
    protected void configure() {
        install(myFirstTestatooModule());
        install(mySecondTestatooModule());
    }

Last step, we have to define which module is used for our test.
To do this, we add the annotation TestatooModules on our test class with the module class name.

	@RunWith(TestatooJunitRunner.class)
	@TestatooModules(Module.class)
	public class GoogleSearchTest {
	...
