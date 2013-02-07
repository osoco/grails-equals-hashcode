class EqualsHashcodeTestGrailsPlugin {
    def version = '0.3.1'
    def grailsVersion = '1.3.7 > *'

    def loadAfter = ['spock']

    def title = 'Base Spock specification for equals and hashCode tests'
    def description = '''\\
Base Spock specification for testing equals and hashCode methods if they are compliant with equals/hashCode contracts.
'''

    def author = 'Marcin Gryszko'
    def authorEmail = 'marcin.gryszko@osoco.es'
    def developers = [
        [name: 'Marcin Gryszko', email: 'marcin.gryszko@osoco.es'],
    ]

    def license = 'APACHE'
    def documentation = 'https://github.com/osoco/grails-equals-hashcode'
    def scm = [url: 'https://github.com/osoco/grails-equals-hashcode']
    def issueManagement = [system: 'GitHub', url: 'https://github.com/osoco/grails-equals-hashcode/issues']
}
