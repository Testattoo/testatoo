# Concurrent testing

Testatoo provides the possibility to execute concurrent tests in separated browsers.
All tests in one class will be executed in any browser simultaneously.

## Configuration

In the module, we must configure as many sessions as we want (server and client).
Finally, we must define which of these sessions will be used for concurrent testing.

    public class Module extends AbstractTestatooModule {

	@Override
	protected void configure() {
	    containers().register(createContainer()
		    .implementedBy(JETTY)
		    .webappRoot("src/test/webapp")
		    .port(7772)
		    .build())
		    .scope(Scope.TEST_SUITE);

	    // setup first browser
	    seleniumServers().register(createSeleniumServer()
		    .port(7783)
		    .useSingleWindow(true)
		    .build())
		    .scope(Scope.TEST_SUITE);
	    seleniumSessions()
		    .register(createSeleniumSession()
			    .website("http://127.0.0.1:7772/")
			    .browser("*firefox")
			    .serverHost("127.0.0.1")
			    .serverPort(7783)
			    .build())
		    .scope(Scope.TEST_SUITE)
		    .withTimeout(20000)
		    .inCartridge(TestatooCartridge.HTML4);

	    // setup second browser
	    seleniumServers().register(createSeleniumServer()
		    .port(7784)
		    .useSingleWindow(true)
		    .build())
		    .scope(Scope.TEST_SUITE);
	    seleniumSessions()
		    .register(createSeleniumSession()
			    .website("http://127.0.0.1:7772/")
			    .browser("*firefox")
			    .serverHost("127.0.0.1")
			    .serverPort(7784)
			    .build())
		    .scope(Scope.TEST_SUITE)
		    .named("secondSession")
		    .withTimeout(20000)
		    .inCartridge(TestatooCartridge.HTML4);

	    // sessions will be used for concurent testing
	    useConcurrentTesting()
		    .distributeTestsAmongst(Evaluator.DEFAULT_NAME, "secondSession");
	}
    }

## Usage

    @RunWith(TestatooJunitRunner.class)
    @TestatooModules(Module.class)
    @ConcurrentEvaluation
    public class ConcurrentTest {

	@Before
	public void setUp() {
	    page().open("/");
	}

	@Test
	public void firstTest() {
	    enter("Testatoo", into(searchField()));
	    assertThat(firstResult(), has(text("Testatoo")));
	}

	@Test
	public void secondTest() {
	    enter("Testatoo", into(searchField()));
	    assertThat(firstResult(), has(text("Testatoo")));
	}

	@Test
	public void thirdTest() {
	    enter("Testatoo", into(searchField()));
	    assertThat(firstResult(), has(text("Testatoo")));
	}
    }
