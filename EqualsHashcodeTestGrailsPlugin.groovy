class EqualsHashcodeTestGrailsPlugin {
    def version = '0.2.0-spock-0.6-SNAPSHOT'
    def grailsVersion = '2.0.0 > *'
    def dependsOn = [spock: '0.6-SNAPSHOT']

    def author = 'Marcin Gryszko'
    def authorEmail = 'marcin@gryszko.net'
    def title = 'Base Spock specification for equals and hashCode tests'
    def description = '''\\
Base Spock specification for testing equals and hashCode methods of Grails domain classes and other Groovy objects.
'''
    def documentation = 'http://osoco.github.com/grails-equals-hashcode/'
}
