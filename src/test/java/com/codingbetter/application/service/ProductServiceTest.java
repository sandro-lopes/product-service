package com.codingbetter.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codingbetter.domain.catalog.product.exception.ProductNotFoundException;
import com.codingbetter.domain.catalog.product.model.Image;
import com.codingbetter.domain.catalog.product.model.Money;
import com.codingbetter.domain.catalog.product.model.Product;
import com.codingbetter.domain.catalog.product.model.ProductId;
import com.codingbetter.domain.catalog.product.model.Specification;
import com.codingbetter.domain.catalog.product.repository.ProductRepository;
import com.codingbetter.domain.shared.event.DomainEvent;
import com.codingbetter.domain.shared.event.DomainEventPublisher;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// @DisplayName("ProductServiceImpl Tests")
class ProductServiceTest {

    // @Mock
    // private ProductRepository productRepository;

    // @Mock
    // private DomainEventPublisher eventPublisher;

    // @Mock
    // private Product product;

    // private ProductApplicationService productService;
    // private ProductId productId;
    // private List<DomainEvent> domainEvents;

    // @BeforeEach
    // void setUp() {
    //     productService = new ProductApplicationService(productRepository, eventPublisher);
    //     productId = new ProductId(UUID.randomUUID());
    //     domainEvents = new ArrayList<>();
    // }

    // @Test
    // @DisplayName("Should update price successfully")
    // void shouldUpdatePriceSuccessfully() {
    //     // Arrange
    //     Money newPrice = new Money(new BigDecimal("100.00"), Currency.getInstance("BRL"));
    //     when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    //     when(product.getDomainEvents()).thenReturn(domainEvents);
        
    //     // Act
    //     productService.updatePrice(productId, newPrice);
        
    //     // Assert
    //     verify(product).updatePrice(newPrice);
    //     verify(productRepository).save(product);
    //     verify(product).getDomainEvents();
    //     verify(product).clearDomainEvents();
    // }

    // @Test
    // @DisplayName("Should activate product successfully")
    // void shouldActivateProductSuccessfully() {
    //     // Arrange
    //     when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    //     when(product.getDomainEvents()).thenReturn(domainEvents);
        
    //     // Act
    //     productService.activate(productId);
        
    //     // Assert
    //     verify(product).activate();
    //     verify(productRepository).save(product);
    //     verify(product).getDomainEvents();
    //     verify(product).clearDomainEvents();
    // }

    // @Test
    // @DisplayName("Should deactivate product successfully")
    // void shouldDeactivateProductSuccessfully() {
    //     // Arrange
    //     when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    //     when(product.getDomainEvents()).thenReturn(domainEvents);
        
    //     // Act
    //     productService.deactivate(productId);
        
    //     // Assert
    //     verify(product).deactivate();
    //     verify(productRepository).save(product);
    //     verify(product).getDomainEvents();
    //     verify(product).clearDomainEvents();
    // }

    // @Test
    // @DisplayName("Should discontinue product successfully")
    // void shouldDiscontinueProductSuccessfully() {
    //     // Arrange
    //     when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    //     when(product.getDomainEvents()).thenReturn(domainEvents);
        
    //     // Act
    //     productService.discontinue(productId);
        
    //     // Assert
    //     verify(product).discontinue();
    //     verify(productRepository).save(product);
    //     verify(product).getDomainEvents();
    //     verify(product).clearDomainEvents();
    // }

    // @Test
    // @DisplayName("Should add image successfully")
    // void shouldAddImageSuccessfully() throws MalformedURLException {
    //     // Arrange
    //     Image image = new Image(URI.create("http://example.com/image.jpg"));
    //     when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    //     when(product.getDomainEvents()).thenReturn(domainEvents);
        
    //     // Act
    //     productService.addImage(productId, image);
        
    //     // Assert
    //     verify(product).addImage(image);
    //     verify(productRepository).save(product);
    //     verify(product).getDomainEvents();
    //     verify(product).clearDomainEvents();
    // }

    // @Test
    // @DisplayName("Should add specification successfully")
    // void shouldAddSpecificationSuccessfully() {
    //     // Arrange
    //     Specification specification = new Specification("key", "value");
    //     when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    //     when(product.getDomainEvents()).thenReturn(domainEvents);
        
    //     // Act
    //     productService.addSpecification(productId, specification);
        
    //     // Assert
    //     verify(product).addSpecification(specification);
    //     verify(productRepository).save(product);
    //     verify(product).getDomainEvents();
    //     verify(product).clearDomainEvents();
    // }

    // @Test
    // @DisplayName("Should throw ProductNotFoundException when product not found")
    // void shouldThrowProductNotFoundExceptionWhenProductNotFound() {
    //     // Arrange
    //     ProductId nonExistentId = new ProductId(UUID.randomUUID());
    //     when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());
        
    //     // Act & Assert
    //     ProductNotFoundException exception = assertThrows(
    //         ProductNotFoundException.class,
    //         () -> productService.activate(nonExistentId)
    //     );
        
    //     assertTrue(exception.getMessage().contains(nonExistentId.getUuid().toString()));
    // }

    // @Test
    // @DisplayName("Should publish domain events")
    // void shouldPublishDomainEvents() {
    //     // Arrange
    //     DomainEvent event1 = mock(DomainEvent.class);
    //     DomainEvent event2 = mock(DomainEvent.class);
    //     domainEvents.add(event1);
    //     domainEvents.add(event2);
    //     when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    //     when(product.getDomainEvents()).thenReturn(domainEvents);
        
    //     // Act
    //     productService.activate(productId);
        
    //     // Assert
    //     verify(eventPublisher).publish(event1);
    //     verify(eventPublisher).publish(event2);
    // }

    // @Test
    // @DisplayName("Should save product after executing operation")
    // void shouldSaveProductAfterExecutingOperation() {
    //     // Arrange
    //     ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
    //     when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    //     when(product.getDomainEvents()).thenReturn(domainEvents);
        
    //     // Act
    //     productService.activate(productId);
        
    //     // Assert
    //     verify(productRepository).save(productCaptor.capture());
    //     assertEquals(product, productCaptor.getValue());
    // }

    // @Test
    // @DisplayName("Should clear domain events after publishing")
    // void shouldClearDomainEventsAfterPublishing() {
    //     // Arrange
    //     DomainEvent event = mock(DomainEvent.class);
    //     domainEvents.add(event);
    //     when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    //     when(product.getDomainEvents()).thenReturn(domainEvents);
        
    //     // Act
    //     productService.activate(productId);
        
    //     // Assert
    //     verify(product).clearDomainEvents();
    // }
} 