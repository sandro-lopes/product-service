package com.codingbetter.domain.catalog.product.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ProductNotFoundException Tests")
class ProductNotFoundExceptionTest {

    @Test
    @DisplayName("Should create exception with message")
    void shouldCreateExceptionWithMessage() {
        // Arrange
        String message = "Product not found";

        // Act
        ProductNotFoundException exception = new ProductNotFoundException(message);

        // Assert
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should create exception with null message")
    void shouldCreateExceptionWithNullMessage() {
        // Act
        ProductNotFoundException exception = new ProductNotFoundException(null);

        // Assert
        assertNotNull(exception);
        assertNull(exception.getMessage());
    }

    @Test
    @DisplayName("Should create exception with empty message")
    void shouldCreateExceptionWithEmptyMessage() {
        // Arrange
        String message = "";

        // Act
        ProductNotFoundException exception = new ProductNotFoundException(message);

        // Assert
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should create exception with special characters in message")
    void shouldCreateExceptionWithSpecialCharactersInMessage() {
        // Arrange
        String message = "Product #123 not found!";

        // Act
        ProductNotFoundException exception = new ProductNotFoundException(message);

        // Assert
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should create exception with long message")
    void shouldCreateExceptionWithLongMessage() {
        // Arrange
        String message = "Product with ID 123456789 not found in the database. Please check if the product exists and try again.";

        // Act
        ProductNotFoundException exception = new ProductNotFoundException(message);

        // Assert
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }
} 