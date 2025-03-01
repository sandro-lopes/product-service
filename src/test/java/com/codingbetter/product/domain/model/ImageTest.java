package com.codingbetter.product.domain.model;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    void shouldCreateImageSuccessfully() throws Exception {
        // Arrange
        URL url = URI.create("https://example.com/image.jpg").toURL();
        
        // Act
        Image image = new Image(url);
        
        // Assert
        assertEquals(url, image.getUrl());
    }
    
    @Test
    void shouldThrowExceptionWhenUrlIsNull() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Image(null)
        );
        
        assertEquals("URL is required", exception.getMessage());
    }
    
    @Test
    void shouldCreateTwoImagesWithSameUrl() throws Exception {
        // Arrange
        URL url = URI.create("https://example.com/image.jpg").toURL();
        
        // Act
        Image image1 = new Image(url);
        Image image2 = new Image(url);
        
        // Assert
        assertEquals(image1.getUrl(), image2.getUrl());
    }
    
    @Test
    void shouldCreateTwoImagesWithDifferentUrls() throws Exception {
        // Arrange
        URL url1 = URI.create("https://example.com/image1.jpg").toURL();
        URL url2 = URI.create("https://example.com/image2.jpg").toURL();
        
        // Act
        Image image1 = new Image(url1);
        Image image2 = new Image(url2);
        
        // Assert
        assertNotEquals(image1.getUrl(), image2.getUrl());
    }
    
    @Test
    void shouldCreateImageWithHttpsUrl() throws Exception {
        // Arrange
        URL url = URI.create("https://example.com/secure-image.jpg").toURL();
        
        // Act
        Image image = new Image(url);
        
        // Assert
        assertEquals("https", image.getUrl().getProtocol());
    }
    
    @Test
    void shouldCreateImageWithHttpUrl() throws Exception {
        // Arrange
        URL url = URI.create("http://example.com/image.jpg").toURL();
        
        // Act
        Image image = new Image(url);
        
        // Assert
        assertEquals("http", image.getUrl().getProtocol());
    }
} 