package com.codingbetter.infrastructure.messaging;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.codingbetter.domain.catalog.product.event.ProductActivatedEvent;
import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.infrastructure.config.ProductRabbitMQConfig;
import com.codingbetter.infrastructure.messaging.strategy.EventRoutingStrategy;
import com.codingbetter.infrastructure.messaging.strategy.EventRoutingStrategyFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class RabbitMQDomainEventPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private ObjectMapper objectMapper;
    
    @Mock
    private EventRoutingStrategyFactory routingStrategyFactory;
    
    @Mock
    private EventRoutingStrategy routingStrategy;

    private RabbitMQDomainEventPublisher publisher;

    @BeforeEach
    void setUp() {
        publisher = new RabbitMQDomainEventPublisher(rabbitTemplate, objectMapper, routingStrategyFactory);
    }

    @Test
    void shouldPublishProductActivatedEventToRabbitMQ() throws JsonProcessingException {
        // Arrange
        ProductId productId = new ProductId(UUID.randomUUID());
        ProductActivatedEvent event = new ProductActivatedEvent(productId);
        String serializedEvent = "{\"productId\":\"" + productId.getUuid() + "\"}";
        
        when(objectMapper.writeValueAsString(any(ProductActivatedEvent.class))).thenReturn(serializedEvent);
        when(routingStrategyFactory.getStrategy(event)).thenReturn(routingStrategy);
        when(routingStrategy.getRoutingKey(event)).thenReturn(ProductRabbitMQConfig.PRODUCT_ACTIVATED_ROUTING_KEY);
        
        // Act
        publisher.publish(event);
        
        // Assert
        verify(objectMapper, times(1)).writeValueAsString(event);
        verify(routingStrategyFactory, times(1)).getStrategy(event);
        verify(routingStrategy, times(1)).getRoutingKey(event);
        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(ProductRabbitMQConfig.EXCHANGE_NAME),
                eq(ProductRabbitMQConfig.PRODUCT_ACTIVATED_ROUTING_KEY),
                eq(serializedEvent));
    }
} 