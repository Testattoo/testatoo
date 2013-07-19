# Testing in parallel

Testatoo provides the possibility to execute simultaneous tests in separated browsers

## Configuration

In the module, we must configure 2 servers and a session for each server.
The second session must have a session name.

    public class Module extends AbstractTestatooModule {

	// Setup first browser (the default browser)
	seleniumPort1 = findFreePort();
	seleniumServers().register(createSeleniumServer()
	    .port(seleniumPort1)
	    .useSingleWindow(true)
	    .build())
	    .scope(Scope.TEST_SUITE);
	seleniumSessions().register(createSeleniumSession()
	    .website("http://www.google.com/")
	    .browser("*firefox")
	    .serverHost("localhost")
	    .serverPort(seleniumPort1).build())
	    .scope(Scope.TEST_SUITE)
	    .withTimeout(20000)
	    .inCartridge(TestatooCartridge.HTML4);

	// Setup second browser
	seleniumPort2 = findFreePort();
	seleniumServers().register(createSeleniumServer()
	    .port(seleniumPort2)
	    .useSingleWindow(true)
	    .build())
	    .scope(Scope.TEST_SUITE);
	seleniumSessions().register(createSeleniumSession()
	    .website("http://www.google.com/")
	    .browser("*firefox")
	    .serverHost("localhost")
	    .serverPort(seleniumPort2).build())
	    .scope(Scope.TEST_SUITE)
	    .named("mySecondSession")
	    .withTimeout(20000)
		.inCartridge(TestatooCartridge.HTML4);
    }

## Usage

The session 1 is used for current code, and second session is used in another thread.

    @Test
    public void simultaneousGoogleSearch() {

	//Get second session by name
	EvaluatorHolder.RunnerBuilder mySecondSession = withEvaluator("mySecondSession");

	//Execute second session ...
	mySecondSession.runInParallel(new Runnable() {
	    @Override
	    public void run() {
		page().open("/");
		enter("Testatoo", into(searchField()));
		assertThat(firstResult(), has(text("Testatoo")));
	    }
	});

	//... during first session running
	page().open("/");
	enter("Testatoo", into(searchField()));
	assertThat(firstResult(), has(text("Testatoo")));

    }

## Exemple for chat

    @Test
    public void friendsChat() throws InterruptedException {

	EvaluatorHolder.RunnerBuilder mySecondSession = withEvaluator("mySecondSession");

	mySecondSession.runInParallel(new Runnable() {
	    @Override
	    public void run() {
		page().open("chatPage.html");
		clickOn(friendIcon());

		enter("Hello boy !", chatTextField());
		clickOn(sendMessageButton());

	    }
	});

	page().open("chatPage.html");

	waitUntil(friendMessages().first(), has(text("Hello boy !")), max(2, SECONDS));
    }

    private Selection<Phrase> friendMessages() {
	return components(P.class, $(".friendMessage"));
    }

    private TextField chatTextField() {
	return component(TextField.class, $("#chatTextField"));
    }

    private Button friendIcon() {
	return component(Button.class, $(".friendName"));
    }

## Synchronized test : Exemple for chat

    @Test
    public void chatBetweenTwoFriends() throws Exception {

	// Get second session
        EvaluatorHolder.RunnerBuilder secondSession = withEvaluator("mySecondSession");

	// Create "flags" to know state of test
        final CountDownLatch messageSent = new CountDownLatch(1);
        final CountDownLatch responseSent = new CountDownLatch(1);

	// Execute test in session 2
        Future f = secondSession.runInParallel(new Runnable() {
            @Override
            public void run() {

                try {
		    // Go to chat page
                    goTo("chatPage.html");

		    // Send message to friend
                    clickOn(friendIcon());
                    enter("Hello boy !", into(chatTextField()));
                    clickOn(sendMessageButton());
		    // Notify message has been sent
                    messageSent.countDown();

		    // Waiting response
                    responseSent.await();
		    // Verrify content of the response
                    assertThat(friendMessages().first(), has(text("Hi girl !")));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

	// Go to chat page
        goTo("chatPage.html");
	// Waiting other people send message in 2 seconds max
        messageSent.await(2, SECONDS);

	// Assert that the correct message has been received
        waitUntil(friendMessages().first(), has(text("Hello boy !")), max(1, SECONDS));

	// Send message to other friend
        enter("Hi girl !", into(chatTextField()));
        clickOn(sendMessageButton());
	// Notify message has been sent
        responseSent.countDown();

	// Waiting other session is done
        f.get();
    }
