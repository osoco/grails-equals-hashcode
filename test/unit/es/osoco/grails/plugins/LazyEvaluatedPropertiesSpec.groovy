package es.osoco.grails.plugins

class ChildFactory {
    static newExtremelyComplexSecondLevelDomainObject() {
        new SecondLevelDomainObject(sampleProperty: 'a value')
    }
}

class LazyEvaluatedPropertiesSpec extends EqualsHashCodeSpec {

    def createDomainObjectToCompare() {
        new DomainObject(includedProperty: 'included', ignoredProperty: 'ignored',
            child: ChildFactory.newExtremelyComplexSecondLevelDomainObject())
    }

    def modifiedPropertiesIncludedInEqualsAndHashCode() {
        [includedProperty: { 'included changed' }, child: {
            def child = ChildFactory.newExtremelyComplexSecondLevelDomainObject()
            child.sampleProperty = 'a different value'
            child
        }]
    }

    def modifiedPropertiesIgnoredInEqualsAndHashCode() {
        [ignoredProperty: { 'ignored changed' }]
    }
}
