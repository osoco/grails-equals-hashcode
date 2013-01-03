# Summary
Base [Spock](http://code.google.com/p/spock/) specification for testing equals and hashCode methods if they are compliant equals/hashCode contracts.

As of 0.3 version it's independent of the Spock and Grails Spock plugin [Grails Spock plugin](http://www.grails.org/plugin/spock) version

# Usage
We have a domain class where we implemented ``equals`` and ``hashCode`` methods (either with Apache Commons Lang builders or with Groovy AST transformations). ``includedSampleProperty`` and ``child`` are used in ``equals``/``hashCode``, whereas ``ignoredSampleProperty`` is not a part of object's equality.

```
class DomainObject {
    String includedSampleProperty
    String ignoredSampleProperty
    SecondLevelDomainObject child

    boolean equals(o) {
        if (o == null) return false
        if (this.is(o)) return true
        if (!(o instanceof DomainObject)) return false

        DomainObject that = (DomainObject) o
        new EqualsBuilder()
            .append(includedProperty, that.includedProperty)
            .append(child, that.child)
            .isEquals()
    }

    int hashCode() {
        new HashCodeBuilder()
            .append(includedProperty)
            .append(child)
            .toHashCode()
    }
}

class SecondLevelDomainObject {
    String sampleProperty

    boolean equals(o) { // ... }
    int hashCode() { // ... }
}
```

We should test both ``equals`` and ``hashCode`` methods if they:

* fulfill ``equals`` and ``hashCode`` contracts as specified in [Object](http://docs.oracle.com/javase/6/docs/api/java/lang/Object.html) Javadoc (see below)
* use some properties in ``equals``/``hashCode``
* ignore some properties (i.e. if their value change, and the remaining properties stay unchanged, ``equals`` and ``hashCode`` should return the same value as before change)

To keep your code DRY, extend ``EqualsHashCodeSpec`` (which in turn extends ``UnitSpec``) and:

* override the factory method ``createDomainObjectToCompare`` that spawns a new object under test
* override the method ``modifiedPropertiesIncludedInEqualsAndHashCode`` that returns a map of property names used in ``equals``/``hashCode`` and their values changed with respect to the object created by ``createDomainObjectToCompare``
* optionally override the method ``modifiedPropertiesIgnoredInEqualsAndHashCode`` for properties NOT used in ``equals``/``hashCode``
* you may use closures as property values for lazy evaluation (as for ``child`` property value in the example below)

Example for our sample ``DomainObject``:

```
class ChildFactory {
    static newExtremelyComplexSecondLevelDomainObject() {
        new SecondLevelDomainObject(sampleProperty: 'a value')
    }
}

class DomainObjectSpec extends EqualsHashCodeSpec {

    def createDomainObjectToCompare() {
        new DomainObject(includedSampleProperty: 'foo', ignoredSampleProperty: 'bar',
            child: ChildFactory.newExtremelyComplexSecondLevelDomainObject())
    }

    def modifiedPropertiesIncludedInEqualsAndHashCode() {
        [includedSampleProperty: 'foo changed', , child: {
            def child = ChildFactory.newExtremelyComplexSecondLevelDomainObject()
            child.sampleProperty = 'a different value'
            child
        }]
    }

    def modifiedPropertiesIgnoredInEqualsAndHashCode() {
        [ignoredSampleProperty: 'bar changed']
    }
}
```

# How the plugin works
``EqualsHashCodeSpec`` applies a *One Bad Attribute* pattern (variation of [Derived Value](http://xunitpatterns.com/Derived%20Value.html)). For each property used and ignored in ``equals``/``hashCode``, ``createDomainObjectToCompare`` creates two objects to compare. One of the objects is modified - a single property value is changed. 

Tests with changed properties used in ``equals``/``hashCode`` verify that two objects are not equal. They don't check if hashCode values of different objects are the same (although a good implementation of ``hashCode`` should return distinct values for unequal objects). 

Tests with properties ignored in ``equals``/``hashCode`` verify that two objects are equal and hash codes are the same (although a property value is different in two objects).

# equals and hashCode contracts
According to [Object](http://docs.oracle.com/javase/6/docs/api/java/lang/Object.html) Javadoc, ``equals``:

* It is _reflexive_: for any non-null reference value ``x``, ``x.equals(x)`` should return ``true``.
* It is _symmetric_: for any non-null reference values ``x`` and ``y``, ``x.equals(y)`` should return ``true`` if and only if ``y.equals(x)`` returns ``true``.
* It is _transitive_: for any non-null reference values ``x``, ``y``, and ``z``, if ``x.equals(y)`` returns ``true`` and ``y.equals(z)`` returns ``true``, then ``x.equals(z)`` should return ``true``.
* It is _consistent_: for any non-null reference values ``x`` and ``y``, multiple invocations of ``x.equals(y)`` consistently return ``true`` or consistently return ``false``, provided no information used in equals comparisons on the objects is modified. For any non-null reference value ``x``, ``x.equals(null)`` should return ``false``.

``hashCode`` should be always implemented when equals is overriden and:

* Whenever it is invoked on the same object more than once during an execution of a Java application, the ``hashCode`` method must consistently return the same integer, provided no information used in equals comparisons on the object is modified. This integer need not remain consistent from one execution of an application to another execution of the same application.
* If two objects are equal according to the ``equals(Object)`` method, then calling the hashCode method on each of the two objects must produce the same integer result.
* It is not required that if two objects are unequal according to the ``equals(java.lang.Object)`` method, then calling the ``hashCode`` method on each of the two objects must produce distinct integer results. However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hashtables.

As much as is reasonably practical, the hashCode method defined by class ``Object`` does return distinct integers for distinct objects. (This is typically implemented by converting the internal address of the object into an integer, but this implementation technique is not required by the JavaTM programming language.)

# Changelog
* 0.3
	* Indepenent of Spock and Grails Spock plugin version
* 0.2-spock-0.5-groovy-1.7, 0.2-spock-0.6 version - March 06, 2012
	* Compatible with Grails 2.0
	* Two versions (for Grails 1.3.7 and Grails 2.0) following Spock versioning
* 0.2.0 version - June 29, 2011
	* EqualsHashCodeSpec moved to es.osoco.grails.plugins package. Please update your test code after upgrading to this version
	* Lazy property value evaluation
* 0.1.1 version - May 31, 2011
	* equals() is symmetric is tested correctly
* 0.1 version - May, 2011
	* initial release
