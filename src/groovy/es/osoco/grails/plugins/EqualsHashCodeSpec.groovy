package es.osoco.grails.plugins

import spock.lang.Unroll
import grails.plugin.spock.UnitSpec

class EqualsHashCodeSpec extends UnitSpec {

    private static FAKE_PROPERTY = 'dd2060d0-a28d-11e0-8264-0800200c9a66'

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
        domainObject2.equals(domainObject)
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
        // In a good hashCode implementation domainObject.hashCode() != domainObject2.hashCode(),
        // but it's not guaranteed

        where:
        property << modifiedPropertiesIncludedInEqualsAndHashCode()
    }

    @Unroll("equals and hashCode ignore #property")
    def "equals and hashCode ignore some properties"() {
        setup:
        def domainObject = createDomainObjectToCompare()
        def domainObject2 = property.key != FAKE_PROPERTY ?
            createModifiedDomainObject(property) :
            createDomainObjectToCompare()

        expect:
        domainObject.equals(domainObject2)
        domainObject.hashCode() == domainObject2.hashCode()

        where:
        property << modifiedPropertiesIgnoredInEqualsAndHashCode()
    }

    def createModifiedDomainObject(modifiedProperty) {
        def domainObject = createDomainObjectToCompare()
        domainObject[modifiedProperty.key] = evaluateValue(modifiedProperty.value)

        domainObject
    }

    private evaluateValue(value)
    {
        value instanceof Closure ? value() : value
    }

    /**
     * To execute a parametrized feature method, Spock needs at least one dataset.
     *
     * Since some domain objects will use all their properties in equals and hashCode comparisons, we must provide
     * a default implementation with a property that anyway should be ignored.
     */
    def modifiedPropertiesIgnoredInEqualsAndHashCode() {
        ["${FAKE_PROPERTY}": null]
    }
}
