class EqualsHashcodeTestGrailsPlugin {
    def version = '0.1'
    def grailsVersion = '1.3 > *'
    def dependsOn = [spock: '0.5-groovy-1.7']

    def author = "Marcin Gryszko"
    def authorEmail = 'marcin.gryszko@osoco.es'
    def title = "Base Spock specification for equals and hashCode tests"
    def description = '''\\
Base Spock specification for testing equals and hashCode methods of domain classes.
'''
}
