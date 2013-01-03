grails.release.scm.enabled = false
grails.project.docs.output.dir = "docs"

grails.project.dependency.resolution = {
    inherits 'global'
    log 'warn'

    repositories {
        grailsCentral()
        mavenCentral()
    }

    dependencies {
        test 'commons-lang:commons-lang:2.6'
    }

    plugins {
        test ":spock:0.7", {
            export = false
        }

        build(':release:2.2.0', ':rest-client-builder:1.0.3') {
            export = false
        }
    }
}
