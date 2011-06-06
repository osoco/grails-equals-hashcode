grails.project.docs.output.dir = "docs"
grails.project.dependency.resolution = {
    inherits("global") {
        excludes 'ehcache'
    }
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenCentral()
    }
    dependencies {
        test 'commons-lang:commons-lang:2.6'
    }
}
