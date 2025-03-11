package com.codingbetter.application.eventhandler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codingbetter.domain.catalog.product.event.ProductActivatedEvent;
import com.codingbetter.domain.catalog.product.event.ProductDeactivatedEvent;
import com.codingbetter.domain.catalog.product.event.ProductDiscontinuedEvent;
import com.codingbetter.domain.catalog.product.event.ProductPriceChangedEvent;
import com.codingbetter.infrastructure.config.ProductRabbitMQConfig;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductEventHandler {

    @Transactional
    @RabbitListener(queues = ProductRabbitMQConfig.PRODUCT_ACTIVATED_QUEUE)
    public void handleProductActivatedEvent(ProductActivatedEvent event, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        try {
            log.info("Consuming ProductActivatedEvent: Product activated: {}", event.getProductId().getUuid());
            
            // Here would be the business logic implementation
            // For example: update the product status in the database,
            // send notifications, etc.
            
            // Confirm message processing
            channel.basicAck(tag, false);
        } catch (Exception e) {
            // In case of error, reject the message and return it to the queue
            log.error("Error processing ProductActivatedEvent", e);
            channel.basicNack(tag, false, true);
            throw e; // Propagate the exception to rollback the transaction
        }
    }
    
    @Transactional
    @RabbitListener(queues = ProductRabbitMQConfig.PRODUCT_DEACTIVATED_QUEUE)
    public void handleProductDeactivatedEvent(ProductDeactivatedEvent event, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        try {
            log.info("Consuming ProductDeactivatedEvent: Product deactivated: {}", event.getProductId().getUuid());
            
            // Here would be the business logic implementation
            // For example: update the product status in the database,
            // send notifications, etc.
            
            // Confirm message processing
            channel.basicAck(tag, false);
        } catch (Exception e) {
            // In case of error, reject the message and return it to the queue
            log.error("Error processing ProductDeactivatedEvent", e);
            channel.basicNack(tag, false, true);
            throw e; // Propagate the exception to rollback the transaction
        }
    }
    
    @Transactional
    @RabbitListener(queues = ProductRabbitMQConfig.PRODUCT_DISCONTINUED_QUEUE)
    public void handleProductDiscontinuedEvent(ProductDiscontinuedEvent event, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        try {
            log.info("Consuming ProductDiscontinuedEvent: Product discontinued: {}", event.getProductId().getUuid());
            
            // Here would be the business logic implementation
            // For example: update the product status in the database,
            // send notifications, etc.
            
            // Confirm message processing
            channel.basicAck(tag, false);
        } catch (Exception e) {
            // In case of error, reject the message and return it to the queue
            log.error("Error processing ProductDiscontinuedEvent", e);
            channel.basicNack(tag, false, true);
            throw e; // Propagate the exception to rollback the transaction
        }
    }
    
    @Transactional
    @RabbitListener(queues = ProductRabbitMQConfig.PRODUCT_PRICE_CHANGED_QUEUE)
    public void handleProductPriceChangedEvent(ProductPriceChangedEvent event, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        try {
            log.info("Consuming ProductPriceChangedEvent: Product price changed: {}, new price: {}", 
                    event.getProductId().getUuid(), 
                    event.getNewPrice().getAmount());
            
            // Here would be the business logic implementation
            // For example: update the product price in the database,
            // send notifications, etc.
            
            // Confirm message processing
            channel.basicAck(tag, false);
        } catch (Exception e) {
            // In case of error, reject the message and return it to the queue
            log.error("Error processing ProductPriceChangedEvent", e);
            channel.basicNack(tag, false, true);
            throw e; // Propagate the exception to rollback the transaction
        }
    }
} 