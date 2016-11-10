package io.restall.http;

import org.gradle.api.UncheckedIOException;
import org.gradle.internal.concurrent.Stoppable;
import org.gradle.internal.impldep.org.simpleframework.http.core.ContainerServer;
import org.gradle.internal.impldep.org.simpleframework.http.resource.FileContext;
import org.gradle.internal.impldep.org.simpleframework.transport.connect.SocketConnection;
import org.gradle.plugins.javascript.envjs.http.HttpFileServer;
import org.gradle.plugins.javascript.envjs.http.simple.SimpleHttpFileServer;
import org.gradle.plugins.javascript.envjs.http.simple.internal.SimpleFileServerContainer;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ConfigurableHttpFileServerFactory {

    public static HttpFileServer start(File contentRoot, int port) {
        ConfigurableFileServerContainer container = new ConfigurableFileServerContainer(new FileContext(contentRoot));

        try {
            final ContainerServer e = new ContainerServer(container);
            SocketConnection connection = new SocketConnection(e);
            InetSocketAddress address = new InetSocketAddress(port);
            InetSocketAddress usedAddress = (InetSocketAddress)connection.connect(address);
            return new SimpleHttpFileServer(contentRoot, usedAddress.getPort(), new Stoppable() {
                public void stop() {
                    try {
                        e.stop();
                    } catch (IOException var2) {
                        throw new UncheckedIOException(var2);
                    }
                }
            });
        } catch (IOException var8) {
            throw new UncheckedIOException(var8);
        }
    }

}
