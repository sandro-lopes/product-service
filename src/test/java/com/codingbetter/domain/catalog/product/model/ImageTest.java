package com.codingbetter.domain.catalog.product.model;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import java.net.MalformedURLException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Image Tests")
class ImageTest {

    private static final String IMAGE_URL = "https://example.com/image.jpg";
    private static final String DIFFERENT_IMAGE_URL = "https://example.com/different-image.jpg";

    @Test
    @DisplayName("Should create image successfully")
    void shouldCreateImageSuccessfully() throws MalformedURLException {
        // Arrange
        URI uri = URI.create(IMAGE_URL);

        // Act
        Image image = new Image(uri);

        // Assert
        assertNotNull(image);
        assertEquals(uri.toURL(), image.getUrl());
    }

    @Test
    @DisplayName("Should throw exception when URL is null")
    void shouldThrowExceptionWhenUrlIsNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Image(null)
        );
        assertEquals("URL is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should compare images correctly")
    void shouldCompareImagesCorrectly() throws MalformedURLException {
        // Arrange
        URI uri = URI.create("http://example.com/image.jpg");
        Image image1 = new Image(uri);
        Image image2 = new Image(uri);
        Image image3 = new Image(URI.create("http://example.com/another.jpg"));

        // Act & Assert
        assertTrue(image1.equals(image2));
        assertFalse(image1.equals(image3));
        assertFalse(image1.equals(null));
        assertEquals(image1.hashCode(), image2.hashCode());
        assertNotEquals(image1.hashCode(), image3.hashCode());
    }

    @Test
    @DisplayName("Should have consistent hashCode")
    void shouldHaveConsistentHashCode() throws MalformedURLException {
        // Arrange
        URI uri = URI.create("http://example.com/image.jpg");
        Image image = new Image(uri);

        // Act & Assert
        assertEquals(image.hashCode(), image.hashCode());
    }

    @Test
    void shouldCreateTwoImagesWithSameUrl() throws Exception {
        // Arrange
        URI uri = URI.create(IMAGE_URL);
        
        // Act
        Image image1 = new Image(uri);
        Image image2 = new Image(uri);
        
        // Assert
        assertEquals(image1.getUrl(), image2.getUrl());
        assertEquals(image1, image2);
    }
    
    @Test
    void shouldCreateTwoImagesWithDifferentUrls() throws Exception {
        // Arrange
        URI uri1 = URI.create(IMAGE_URL);
        URI uri2 = URI.create(DIFFERENT_IMAGE_URL);
        
        // Act
        Image image1 = new Image(uri1);
        Image image2 = new Image(uri2);
        
        // Assert
        assertNotEquals(image1.getUrl(), image2.getUrl());
        assertNotEquals(image1, image2);
    }
    
    @Test
    void shouldCreateImageWithHttpsUrl() throws Exception {
        // Arrange
        URI uri = URI.create("https://example.com/secure-image.jpg");
        
        // Act
        Image image = new Image(uri);
        
        // Assert
        assertEquals("https", image.getUrl().getProtocol());
    }
    
    @Test
    void shouldCreateImageWithHttpUrl() throws Exception {
        // Arrange
        URI uri = URI.create("http://example.com/image.jpg");
        
        // Act
        Image image = new Image(uri);
        
        // Assert
        assertEquals("http", image.getUrl().getProtocol());
    }

    @Test
    void shouldCompareWithNull() throws Exception {
        // Arrange
        URI uri = URI.create(IMAGE_URL);
        Image image = new Image(uri);
        
        // Assert
        assertNotEquals(null, image);
    }

    @Test
    void shouldCompareWithDifferentClass() throws Exception {
        // Arrange
        URI uri = URI.create(IMAGE_URL);
        Image image = new Image(uri);
        Object differentObject = new Object();
        
        // Assert
        assertNotEquals(image, differentObject);
    }

    @Test
    void shouldCompareWithSameObject() throws Exception {
        // Arrange
        URI uri = URI.create(IMAGE_URL);
        Image image = new Image(uri);
        
        // Assert
        assertEquals(image, image);
    }
} 