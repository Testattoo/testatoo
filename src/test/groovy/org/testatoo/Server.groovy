/**
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testatoo

import reactor.netty.DisposableServer
import reactor.netty.http.server.HttpServer

import java.nio.file.Path
import java.nio.file.Paths

class Server {
    static DisposableServer start(int port) {
        String helper = new File(".").absolutePath
        Path resource = Paths.get(helper + "/src/test/webapp")
        return HttpServer.create()
            .port(port)
            .route{routes -> routes.directory("/", resource)}
            .bindNow()
    }
}
