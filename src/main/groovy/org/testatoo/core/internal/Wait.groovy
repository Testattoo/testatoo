package org.testatoo.core.internal

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

import static org.testatoo.core.Testatoo.getConfig

/**
 * @author David Avenante (d.avenante@gmail.com)
 */
class Wait {

    static void waitUntil(Closure c, Matcher what = null) {
        boolean success = false
        long timeout = config.waitUntil.toMillis()
        long interval = 200

        Log.log "WaitUntil: " + timeout
        for (; timeout > 0; timeout -= interval) {
            try {
                if (what ? what.matches(c.delegate) : c()) {
                    success = true
                    break
                }
            } catch (e) {
                Log.log('Matcher evaluation fail with this exception : ' + e.message)
                Log.log('Retrying...')
            }
            Thread.sleep(interval)
        }

        if (!success) {
            Description description = new StringDescription()
            description.appendText('Unable to reach the condition after ' + config.waitUntil.toMillis() + ' milliseconds')
            if (what) {
                description.appendText('\nExpected: ')
                        .appendDescriptionOf(what)
                        .appendText('\n     but: ')
                what.describeMismatch(c.delegate, description)
            }
            throw new AssertionError(description.toString())
        }
    }
}
