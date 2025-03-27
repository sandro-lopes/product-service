package com.codingbetter.infrastructure.messaging;

import jakarta.annotation.PostConstruct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.domain.shared.event.DomainEvent;
import com.codingbetter.domain.shared.event.DomainEventPublisher;
import com.codingbetter.infrastructure.config.ProductRabbitMQConfig;
import com.codingbetter.infrastructure.messaging.strategy.EventRoutingStrategy;
import com.codingbetter.infrastructure.messaging.strategy.EventRoutingStrategyFactory;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of DomainEventPublisher that publishes events to RabbitMQ.
 * Uses the Strategy pattern to determine the routing key for each event type.
 */
@Component
@Slf4j
public class RabbitMQDomainEventPublisher implements DomainEventPublisher {
    
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final EventRoutingStrategyFactory routingStrategyFactory;
    
    public RabbitMQDomainEventPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, EventRoutingStrategyFactory routingStrategyFactory) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.routingStrategyFactory = routingStrategyFactory;
    }
    
    @PostConstruct
    public void init() {
        objectMapper.addMixIn(ProductId.class, ProductIdMixin.class);
    }
    
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    @JsonIgnoreProperties({"value"})
    abstract static class ProductIdMixin {}
    
    @Override
    public void publish(DomainEvent event) {
        log.info("Publishing domain event: {} with ID: {} occurred at: {}", event.getClass().getSimpleName(), event.getId(), event.getOccurredOn());
        try {
            String message = objectMapper.writeValueAsString(event);
            String routingKey = determineRoutingKey(event);
            rabbitTemplate.convertAndSend(ProductRabbitMQConfig.EXCHANGE_NAME, routingKey, message);
            log.info("Event published to RabbitMQ: exchange={}, routingKey={}", ProductRabbitMQConfig.EXCHANGE_NAME, routingKey);
        } catch (JsonProcessingException e) {
            log.error("Error serializing domain event", e);
            throw new RuntimeException("Error serializing domain event", e);
        }
    }
    
    private String determineRoutingKey(DomainEvent event) {
        EventRoutingStrategy strategy = routingStrategyFactory.getStrategy(event);
        
        if (strategy == null) {
            throw new RuntimeException("Unknown event type: " + event.getClass().getName());
        }
        return strategy.getRoutingKey(event);
    }
} 