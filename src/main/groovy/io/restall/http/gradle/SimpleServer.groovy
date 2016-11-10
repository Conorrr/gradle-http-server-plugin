package io.restall.http.gradle

import groovy.transform.CompileStatic
import net.freeutils.httpserver.HTTPServer


@CompileStatic
class SimpleServer {

    final int port
    final File filePath

    private HTTPServer server

    SimpleServer(File filePath, int port) {
        this.filePath = filePath
        this.port = port
    }

    void startServer() {
        HTTPServer.FileContextHandler contextHandler = new HTTPServer.FileContextHandler(filePath, "");

        HTTPServer.VirtualHost virtualHost = new HTTPServer.VirtualHost(null)
        virtualHost.addContext('/', contextHandler)

        server = new HTTPServer(port)
        server.addVirtualHost(virtualHost)

        server.start()
    }

    void stopServer() {
        server.stop()
    }
}

