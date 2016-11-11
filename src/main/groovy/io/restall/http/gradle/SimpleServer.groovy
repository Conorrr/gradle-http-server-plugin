package io.restall.http.gradle

import groovy.transform.CompileStatic
import net.freeutils.httpserver.HTTPServer


@CompileStatic
class SimpleServer {

    final int port
    final File filePath
    final String index
    final String context

    private HTTPServer server

    SimpleServer(File filePath, int port, String index, String context) {
        this.filePath = filePath
        this.port = port
        this.index = index
        this.context = context
    }

    void startServer() {
        HTTPServer.FileContextHandler contextHandler = new HTTPServer.FileContextHandler(filePath, "");

        HTTPServer.VirtualHost virtualHost = new HTTPServer.VirtualHost(null)
        virtualHost.setDirectoryIndex(index)
        virtualHost.addContext(context, contextHandler)

        server = new HTTPServer(port)
        server.addVirtualHost(virtualHost)

        server.start()
    }

    void stopServer() {
        server.stop()
    }
}

