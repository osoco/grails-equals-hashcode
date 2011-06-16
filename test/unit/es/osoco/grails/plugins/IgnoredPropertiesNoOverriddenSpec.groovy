package es.osoco.grails.plugins

import es.osoco.EqualsHashCodeSpec

class IgnoredPropertiesNoOverriddenSpec extends EqualsHashCodeSpec {

    def createDomainObjectToCompare() {
        new DomainObject(includedProperty: 'included', ignoredProperty: 'ignored')
    }

    def modifiedPropertiesIncludedInEqualsAndHashCode() {
        [includedProperty: 'included changed']
    }

    // default implementation of modifiedPropertiesIgnoredInEqualsAndHashCode() will be used
}
