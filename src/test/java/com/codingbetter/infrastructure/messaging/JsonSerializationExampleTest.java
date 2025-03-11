package com.codingbetter.infrastructure.messaging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codingbetter.domain.catalog.product.event.ProductActivatedEvent;
import com.codingbetter.domain.catalog.product.event.ProductPriceChangedEvent;
import com.codingbetter.domain.catalog.product.model.Money;
import com.codingbetter.domain.catalog.product.model.ProductId;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

class JsonSerializationExampleTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        // Add mixin to avoid circular reference
        objectMapper.addMixIn(ProductId.class, ProductIdMixin.class);
    }
    
    // Mixin to avoid circular reference
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    @JsonIgnoreProperties({"value"})
    abstract static class ProductIdMixin {}

    @Test
    void shouldSerializeProductActivatedEventWithAllFields() throws JsonProcessingException {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProductId productId = new ProductId(uuid);
        ProductActivatedEvent event = new ProductActivatedEvent(productId);
        
        // Act
        String json = objectMapper.writeValueAsString(event);
        JsonNode jsonNode = objectMapper.readTree(json);
        
        // Assert
        System.out.println("ProductActivatedEvent JSON: " + json);
        
        // Verify DomainEvent interface fields
        assertTrue(jsonNode.has("id"));
        assertTrue(jsonNode.has("occurredOn"));
        assertEquals(event.getId().toString(), jsonNode.get("id").asText());
        
        // Verify specific fields of ProductActivatedEvent
        assertTrue(jsonNode.has("productId"));
        JsonNode productIdNode = jsonNode.get("productId");
        assertTrue(productIdNode.has("uuid"));
        assertEquals(uuid.toString(), productIdNode.get("uuid").asText());
    }
    
    @Test
    void shouldSerializeProductPriceChangedEventWithAllFields() throws JsonProcessingException {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProductId productId = new ProductId(uuid);
        Money oldPrice = new Money(new BigDecimal("100.00"), Currency.getInstance("BRL"));
        Money newPrice = new Money(new BigDecimal("150.00"), Currency.getInstance("BRL"));
        ProductPriceChangedEvent event = new ProductPriceChangedEvent(productId, oldPrice, newPrice);
        
        // Act
        String json = objectMapper.writeValueAsString(event);
        JsonNode jsonNode = objectMapper.readTree(json);
        
        // Assert
        System.out.println("ProductPriceChangedEvent JSON: " + json);
        
        // Verify DomainEvent interface fields
        assertTrue(jsonNode.has("id"));
        assertTrue(jsonNode.has("occurredOn"));
        assertEquals(event.getId().toString(), jsonNode.get("id").asText());
        
        // Verify specific fields of ProductPriceChangedEvent
        assertTrue(jsonNode.has("productId"));
        assertTrue(jsonNode.has("oldPrice"));
        assertTrue(jsonNode.has("newPrice"));
        
        JsonNode oldPriceNode = jsonNode.get("oldPrice");
        assertTrue(oldPriceNode.has("amount"));
        assertTrue(oldPriceNode.has("currency"));
        assertEquals(new BigDecimal("100.00").doubleValue(), oldPriceNode.get("amount").asDouble(), 0.001);
        
        JsonNode newPriceNode = jsonNode.get("newPrice");
        assertTrue(newPriceNode.has("amount"));
        assertTrue(newPriceNode.has("currency"));
        assertEquals(new BigDecimal("150.00").doubleValue(), newPriceNode.get("amount").asDouble(), 0.001);
    }
} 