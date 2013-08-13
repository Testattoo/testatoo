package org.testatoo.config

import java.util.concurrent.CopyOnWriteArrayList

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */

final class Shutdown {

    private static final Shutdown SHUTDOWN = new Shutdown()

    private final List<Hook> hooks = new CopyOnWriteArrayList<>()

    private Shutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread("Testatoo Module cleaner") {
            @Override
            public void run() {
                for (Hook hook : hooks) {
                    try {
                        hook.onShutdown()
                    } catch (Throwable ignored) {
                    }
                }
            }
        });
    }

    public static void addHook(Hook callable) {
        SHUTDOWN.hooks.add(callable)
    }

    public static interface Hook {
        void onShutdown() throws Throwable
    }
}
