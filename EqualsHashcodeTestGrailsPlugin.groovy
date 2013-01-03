class EqualsHashcodeTestGrailsPlugin {
    def version = '0.3'
    def grailsVersion = '1.3.7 > *'

    def title = 'Base Spock specification for equals and hashCode tests'
    def description = '''\\
Base Spock specification for testing equals and hashCode methods of Grails domain classes and other Groovy objects.
'''

    def author = 'Marcin Gryszko'
    def authorEmail = 'marcin.gryszko@osoco.es'
    def developers = [
        [name: 'Marcin Gryszko', email: 'marcin.gryszko@osoco.es'],
    ]

    def license = 'APACHE'
    def documentation = 'http://osoco.github.com/grails-equals-hashcode/'
    def scm = [url: 'https://github.com/osoco/grails-equals-hashcode']
    def issueManagement = [system: 'GitHub', url: 'https://github.com/osoco/grails-equals-hashcode/issues']
}
