package es.osoco.grails.plugins

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.apache.commons.lang.builder.ToStringBuilder

class DomainObject {
    def includedProperty
    def ignoredProperty

    boolean equals(o) {
        if (o == null) return false
        if (this.is(o)) return true
        if (!(o instanceof DomainObject)) return false

        DomainObject that = (DomainObject) o;
        new EqualsBuilder().append(includedProperty, that.includedProperty).isEquals()
    }

    int hashCode() {
        new HashCodeBuilder().append(includedProperty).toHashCode()
    }

    String toString() {
        ToStringBuilder.reflectionToString(this)
    }
}
