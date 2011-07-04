package es.osoco.grails.plugins

import org.apache.commons.lang.builder.ToStringBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.apache.commons.lang.builder.EqualsBuilder

class SecondLevelDomainObject {
    String sampleProperty

    boolean equals(o) {
        if (o == null) return false
        if (this.is(o)) return true
        if (!(o instanceof SecondLevelDomainObject)) return false

        SecondLevelDomainObject that = (SecondLevelDomainObject) o
        new EqualsBuilder()
            .append(sampleProperty, that.sampleProperty)
            .isEquals()
    }

    int hashCode() {
        new HashCodeBuilder()
            .append(sampleProperty)
            .toHashCode()
    }

    String toString() {
        ToStringBuilder.reflectionToString(this)
    }
}
