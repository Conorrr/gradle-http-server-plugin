package io.restall.http.gradle

import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project

@CompileStatic
class StaticHttpServerPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.extensions.create("httpServer", StaticHttpServerPluginExtension)

        project.task('wait') << {
            sleep(100000) // 100 secs
        }

        SimpleServer server

        project.task('httpStart') << {
            println "Starting static http server"

            File root = new File((String) project['httpServer']['basePath'])
            int port = (int) project['httpServer']['port']

            server = new SimpleServer(root, port)
            server.startServer()

            println "Server started on port ${server.port}"
        }

        project.task('httpStop') << {
            println "Stopping static http server"
            server.stopServer()
        }
    }
}