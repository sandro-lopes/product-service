package com.codingbetter.domain.catalog.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Specification Tests")
class SpecificationTest {

    private static final String VALID_NAME = "Colour";
    private static final String VALID_VALUE = "Blue";

    @Test
    @DisplayName("Should create specification successfully")
    void shouldCreateSpecificationSuccessfully() {
        // Arrange
        String name = "Color";
        String value = "Blue";

        // Act
        Specification specification = new Specification(name, value);

        // Assert
        assertNotNull(specification);
        assertEquals(name, specification.getName());
        assertEquals(value, specification.getValue());
    }

    @Test
    @DisplayName("Should throw exception when name is null")
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        String value = "Blue";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Specification(null, value)
        );
        assertEquals("Name of specification is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        // Arrange
        String name = "Color";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Specification(name, null)
        );
        assertEquals("Value of specification is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when name is empty")
    void shouldThrowExceptionWhenNameIsEmpty() {
        // Arrange
        String name = "";
        String value = "Blue";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Specification(name, value)
        );
        assertEquals("Name of specification is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is empty")
    void shouldThrowExceptionWhenValueIsEmpty() {
        // Arrange
        String name = "Color";
        String value = "";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Specification(name, value)
        );
        assertEquals("Value of specification is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when name is blank")
    void shouldThrowExceptionWhenNameIsBlank() {
        // Arrange
        String name = "   ";
        String value = "Blue";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Specification(name, value)
        );
        assertEquals("Name of specification is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is blank")
    void shouldThrowExceptionWhenValueIsBlank() {
        // Arrange
        String name = "Color";
        String value = "   ";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Specification(name, value)
        );
        assertEquals("Value of specification is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should compare specifications correctly")
    void shouldCompareSpecificationsCorrectly() {
        // Arrange
        Specification spec1 = new Specification("Color", "Blue");
        Specification spec2 = new Specification("Color", "Blue");
        Specification spec3 = new Specification("Size", "Large");
        Specification spec4 = new Specification("Color", "Red");

        // Act & Assert
        assertTrue(spec1.equals(spec2));
        assertFalse(spec1.equals(spec3));
        assertFalse(spec1.equals(spec4));
        assertFalse(spec1.equals(null));
        assertEquals(spec1.hashCode(), spec2.hashCode());
        assertNotEquals(spec1.hashCode(), spec3.hashCode());
        assertNotEquals(spec1.hashCode(), spec4.hashCode());
    }

    @Test
    @DisplayName("Should have consistent hashCode")
    void shouldHaveConsistentHashCode() {
        // Arrange
        Specification specification = new Specification("Color", "Blue");

        // Act & Assert
        assertEquals(specification.hashCode(), specification.hashCode());
    }

    @Test
    void shouldCreateSpecificationWithDifferentName() {
        // Arrange
        String differentName = "Size";
        
        // Act
        Specification specification = new Specification(differentName, VALID_VALUE);
        
        // Assert
        assertEquals(differentName, specification.getName());
        assertEquals(VALID_VALUE, specification.getValue());
    }
    
    @Test
    void shouldCreateSpecificationWithDifferentValue() {
        // Arrange
        String differentValue = "Green";
        
        // Act
        Specification specification = new Specification(VALID_NAME, differentValue);
        
        // Assert
        assertEquals(VALID_NAME, specification.getName());
        assertEquals(differentValue, specification.getValue());
    }
    
    @Test
    void shouldCreateSpecificationWithTrimmedName() {
        // Arrange
        String nameWithSpaces = "  " + VALID_NAME + "  ";
        
        // Act
        Specification specification = new Specification(nameWithSpaces, VALID_VALUE);
        
        // Assert
        assertEquals(nameWithSpaces, specification.getName());
        assertEquals(VALID_VALUE, specification.getValue());
    }
    
    @Test
    void shouldCreateSpecificationWithTrimmedValue() {
        // Arrange
        String valueWithSpaces = "  " + VALID_VALUE + "  ";
        
        // Act
        Specification specification = new Specification(VALID_NAME, valueWithSpaces);
        
        // Assert
        assertEquals(VALID_NAME, specification.getName());
        assertEquals(valueWithSpaces, specification.getValue());
    }
    
    @Test
    void shouldCreateTwoIdenticalSpecificationsWithSameValues() {
        // Arrange
        Specification specification1 = new Specification(VALID_NAME, VALID_VALUE);
        Specification specification2 = new Specification(VALID_NAME, VALID_VALUE);
        
        // Assert
        assertEquals(specification1.getName(), specification2.getName());
        assertEquals(specification1.getValue(), specification2.getValue());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidNames")
    @DisplayName("Should throw exception for invalid names")
    void shouldThrowExceptionForInvalidNames(String invalidName, String expectedMessage) {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Specification(invalidName, VALID_VALUE)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
    
    private static Stream<Arguments> provideInvalidNames() {
        return Stream.of(
                Arguments.of(null, "Name of specification is required"),
                Arguments.of("", "Name of specification is required"),
                Arguments.of("   ", "Name of specification is required")
        );
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidValues")
    @DisplayName("Should throw exception for invalid values")
    void shouldThrowExceptionForInvalidValues(String invalidValue, String expectedMessage) {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Specification(VALID_NAME, invalidValue)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> provideInvalidValues() {
        return Stream.of(
                Arguments.of(null, "Value of specification is required"),
                Arguments.of("", "Value of specification is required"),
                Arguments.of("   ", "Value of specification is required")
        );
    }
} 