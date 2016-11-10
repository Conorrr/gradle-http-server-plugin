package io.restall.http.gradle

import io.restall.http.ConfigurableFileServerContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.plugins.javascript.envjs.http.HttpFileServer

class StaticHttpServerPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.extensions.create("httpServer", StaticHttpServerPluginExtension)

        project.task('wait') << {
            sleep(100000) // 100 secs
        }

        HttpFileServer server

        project.task('httpStart') << {
            println "Starting static http server"

            File root = new File(project.httpServer.basePath ?: 'build/http')
            int port = project.httpServer.port ?: 9000

            server = new ConfigurableFileServerContainer().start(root, port)

            println "Server started on port ${server.port}"
        }

        project.task('httpStop') << {
            println "Stopping static http server"
        }
    }
}