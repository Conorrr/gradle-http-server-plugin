package io.restall.http.gradle

class StaticHttpServerPluginExtension {

    String basePath = 'build/http'
    int port = 9000
    String index = 'index.html'
    String context = '/'

}
