package com.codingbetter.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRabbitMQConfig {

    public static final String EXCHANGE_NAME = "product-exchange";
    
    public static final String PRODUCT_ACTIVATED_QUEUE = "product-activated-queue";
    public static final String PRODUCT_DEACTIVATED_QUEUE = "product-deactivated-queue";
    public static final String PRODUCT_DISCONTINUED_QUEUE = "product-discontinued-queue";
    public static final String PRODUCT_PRICE_CHANGED_QUEUE = "product-price-changed-queue";

    public static final String PRODUCT_ACTIVATED_ROUTING_KEY = "product.activated";
    public static final String PRODUCT_DEACTIVATED_ROUTING_KEY = "product.deactivated";
    public static final String PRODUCT_DISCONTINUED_ROUTING_KEY = "product.discontinued";
    public static final String PRODUCT_PRICE_CHANGED_ROUTING_KEY = "product.price-changed";

    @Bean
    public TopicExchange productExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue productActivatedQueue() {
        return new Queue(PRODUCT_ACTIVATED_QUEUE, true);
    }

    @Bean
    public Queue productDeactivatedQueue() {
        return new Queue(PRODUCT_DEACTIVATED_QUEUE, true);
    }

    @Bean
    public Queue productDiscontinuedQueue() {
        return new Queue(PRODUCT_DISCONTINUED_QUEUE, true);
    }

    @Bean
    public Queue productPriceChangedQueue() {
        return new Queue(PRODUCT_PRICE_CHANGED_QUEUE, true);
    }

    @Bean
    public Binding productActivatedBinding(Queue productActivatedQueue, TopicExchange productExchange) {
        return BindingBuilder.bind(productActivatedQueue).to(productExchange).with(PRODUCT_ACTIVATED_ROUTING_KEY);
    }

    @Bean
    public Binding productDeactivatedBinding(Queue productDeactivatedQueue, TopicExchange productExchange) {
        return BindingBuilder.bind(productDeactivatedQueue).to(productExchange).with(PRODUCT_DEACTIVATED_ROUTING_KEY);
    }

    @Bean
    public Binding productDiscontinuedBinding(Queue productDiscontinuedQueue, TopicExchange productExchange) {
        return BindingBuilder.bind(productDiscontinuedQueue).to(productExchange).with(PRODUCT_DISCONTINUED_ROUTING_KEY);
    }

    @Bean
    public Binding productPriceChangedBinding(Queue productPriceChangedQueue, TopicExchange productExchange) {
        return BindingBuilder.bind(productPriceChangedQueue).to(productExchange).with(PRODUCT_PRICE_CHANGED_ROUTING_KEY);
    }
} 