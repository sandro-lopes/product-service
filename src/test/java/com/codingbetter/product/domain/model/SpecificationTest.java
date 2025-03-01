package com.codingbetter.product.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SpecificationTest {

    private static final String VALID_NAME = "Colour";
    private static final String VALID_VALUE = "Blue";

    @Test
    void shouldCreateSpecificationSuccessfully() {
        // Arrange & Act
        Specification specification = new Specification(VALID_NAME, VALID_VALUE);
        
        // Assert
        assertEquals(VALID_NAME, specification.getName());
        assertEquals(VALID_VALUE, specification.getValue());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidNames")
    void shouldThrowExceptionWhenNameIsInvalid(String name, String expectedErrorMessage) {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Specification(name, VALID_VALUE)
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidValues")
    void shouldThrowExceptionWhenValueIsInvalid(String value, String expectedErrorMessage) {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Specification(VALID_NAME, value)
        );
        
        assertEquals(expectedErrorMessage, exception.getMessage());
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
    
    private static Stream<Arguments> provideInvalidNames() {
        return Stream.of(
                Arguments.of(null, "Name of specification is required"),
                Arguments.of("", "Name of specification is required"),
                Arguments.of("   ", "Name of specification is required")
        );
    }
    
    private static Stream<Arguments> provideInvalidValues() {
        return Stream.of(
                Arguments.of(null, "Value of specification is required"),
                Arguments.of("", "Value of specification is required"),
                Arguments.of("   ", "Value of specification is required")
        );
    }
} 