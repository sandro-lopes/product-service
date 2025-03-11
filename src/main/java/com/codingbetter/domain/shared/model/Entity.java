package com.codingbetter.domain.shared.model;

import java.io.Serializable;

/**
 * Interface that defines the basic contract for entities in the domain.
 * An entity is an object that has a unique identity that persists
 * regardless of its attributes.
 *
 * @param <T> the type of the entity
 * @param <ID> the type of the identity's value
 */
public interface Entity<T, ID> extends Serializable {
    /**
     * Returns the unique identity of the entity.
     *
     * @return the entity's identity
     */
    Identity<ID> getId();

    /**
     * Checks if this entity is equal to another entity.
     * Equality is based on the entity's identity, not its attributes.
     *
     * @param other the other entity to compare
     * @return true if the entities are equal, false otherwise
     */
    boolean equals(Object other);

    /**
     * Returns the hash code of the entity.
     * The hash code must be based on the entity's identity.
     *
     * @return the entity's hash code
     */
    int hashCode();
}
