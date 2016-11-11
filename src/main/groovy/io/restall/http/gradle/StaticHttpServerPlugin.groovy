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

            File filePath = new File((String) project['httpServer']['basePath'])
            int port = (int) project['httpServer']['port']
            String index = (String) project['httpServer']['index']
            String context = (String) project['httpServer']['context']

            server = new SimpleServer(filePath, port, index, context)
            server.startServer()

            println "Server started on port ${server.port}"
        }

        project.task('httpStop') << {
            println "Stopping static http server"
            server.stopServer()
        }
    }
}