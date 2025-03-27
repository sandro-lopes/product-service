package com.codingbetter.domain.shared.model;

import java.io.Serializable;

/**
 * Interface that defines the basic contract for identities in the domain.
 * An identity is an object that represents a unique identifier
 * for an entity or aggregate.
 *
 * @param <T> the type of the identity's value
 */
public interface Identity<T> extends Serializable {
    /**
     * Returns the value of the identity.
     *
     * @return the identity's value
     */
    T getValue();

    /**
     * Checks if this identity is equal to another identity.
     *
     * @param other the other identity to compare
     * @return true if the identities are equal, false otherwise
     */
    boolean equals(Object other);

    /**
     * Returns the hash code of the identity.
     *
     * @return the identity's hash code
     */
    int hashCode();
} 