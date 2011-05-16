package es.osoco

import spock.lang.Unroll

class EqualsHashCodeSpec extends grails.plugin.spock.UnitSpec {

    def "equals to null returns false"() {
        setup:
        def domainObject = createDomainObjectToCompare()

        expect:
        !domainObject.equals(null)
    }

    def "equals is reflexive"() {
        setup:
        def domainObject = createDomainObjectToCompare()

        expect:
        domainObject.equals(domainObject)
    }

    def "equals is transitive"() {
        setup:
        def domainObject = createDomainObjectToCompare()
        def domainObject2 = createDomainObjectToCompare()
        def domainObject3 = createDomainObjectToCompare()

        expect:
        domainObject.equals(domainObject2)
        domainObject2.equals(domainObject3)
        domainObject.equals(domainObject3)
    }

    def "equals is symmetric"() {
        setup:
        def domainObject = createDomainObjectToCompare()
        def domainObject2 = createDomainObjectToCompare()

        expect:
        domainObject.equals(domainObject2)
        domainObject.equals(domainObject2)
    }

    def "hashCode of two equal objects is equal"() {
        setup:
        def domainObject = createDomainObjectToCompare()
        def domainObject2 = createDomainObjectToCompare()

        expect:
        domainObject.hashCode() == domainObject2.hashCode()
    }

    def "equals is consistent"() {
        setup:
        def domainObject = createDomainObjectToCompare()
        def domainObject2 = createDomainObjectToCompare()

        expect:
        domainObject.equals(domainObject2)
        domainObject.equals(domainObject2)
    }

    def "hashCode is consistent"() {
        setup:
        def domainObject = createDomainObjectToCompare()

        expect:
        domainObject.equals(domainObject)
        domainObject.hashCode() == domainObject.hashCode()
    }

    def "equals to incompatible type returns false"() {
        setup:
        def domainObject = createDomainObjectToCompare()

        expect:
        !domainObject.equals(new Object())
    }

    @Unroll("equals and hashCode use #property")
    def "equals and hashCode use some properties"() {
        setup:
        def domainObject = createDomainObjectToCompare()
        def domainObject2 = createModifiedDomainObject(property)

        expect:
        !domainObject.equals(domainObject2)
        domainObject.hashCode() != domainObject2.hashCode()

        where:
        property << modifiedPropertiesIncludedInEqualsAndHashCode()
    }

    @Unroll("equals and hashCode ignore #property")
    def "equals and hashCode ignore some properties"() {
        setup:
        def domainObject = createDomainObjectToCompare()
        def domainObject2 = createModifiedDomainObject(property)

        expect:
        domainObject.equals(domainObject2)
        domainObject.hashCode() == domainObject2.hashCode()

        where:
        property << modifiedPropertiesIgnoredInEqualsAndHashCode()
    }

    def createModifiedDomainObject(def modifiedProperty) {
        def domainObject = createDomainObjectToCompare()
        domainObject[modifiedProperty.key] = modifiedProperty.value

        domainObject
    }

    /**
     * To execute a parametrized feature method, Spock needs at least one dataset.
     *
     * Since some domain objects will use all their properties in equals and hashCode comparisons, we must provide
     * a default implementation with a property that anyway should be ignored (metaClass).
     */
    def modifiedPropertiesIgnoredInEqualsAndHashCode() {
        [metaClass: createDomainObjectToCompare().metaClass]
    }
}
