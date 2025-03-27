package com.codingbetter.domain.shared.model;

import java.io.Serializable;

/**
 * Interface that defines the basic contract for value objects in the domain.
 * A value object is an object that is defined only by its attributes
 * and has no identity of its own.
 *
 * @param <T> the type of the value object
 */
public interface ValueObject<T> extends Serializable {
    /**
     * Checks if this value object is equal to another value object.
     * Equality is based on all attributes of the object.
     *
     * @param other the other value object to compare
     * @return true if the objects are equal, false otherwise
     */
    boolean equals(Object other);

    /**
     * Returns the hash code of the value object.
     * The hash code must be based on all attributes of the object.
     *
     * @return the value object's hash code
     */
    int hashCode();

    /**
     * Checks if this value object is equal to another value object
     * in terms of their attributes.
     *
     * @param other the other value object to compare
     * @return true if the objects are equal in terms of attributes, false otherwise
     */
    boolean sameValueAs(T other);
} 