package es.osoco.grails.plugins

import es.osoco.EqualsHashCodeSpec

class LazyEvaluatedPropertiesSpec extends EqualsHashCodeSpec {

    def createDomainObjectToCompare() {
        new DomainObject(includedProperty: 'included', ignoredProperty: 'ignored')
    }

    def modifiedPropertiesIncludedInEqualsAndHashCode() {
        [includedProperty: { 'included changed' }]
    }

    def modifiedPropertiesIgnoredInEqualsAndHashCode() {
        [ignoredProperty: { 'ignored changed' }]
    }
}
