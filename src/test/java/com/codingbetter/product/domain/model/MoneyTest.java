package com.codingbetter.product.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    private static final String CURRENCY = "BRL";
    private static final BigDecimal AMOUNT = new BigDecimal("100.00");
    
    private Money money;

    @BeforeEach
    void setUp() {
        money = new Money(AMOUNT, CURRENCY);
    }

    @Test
    void shouldCreateMoneySuccessfully() {
        // Arrange & Act
        Money money = new Money(AMOUNT, CURRENCY);
        
        // Assert
        assertEquals(AMOUNT, money.getAmount());
        assertEquals(CURRENCY, money.getCurrency());
    }
    
    @Test
    void shouldThrowExceptionWhenAmountIsNull() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Money(null, CURRENCY)
        );
        
        assertEquals("Amount is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        // Arrange
        BigDecimal negativeAmount = new BigDecimal("-10.00");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Money(negativeAmount, CURRENCY)
        );
        
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenCurrencyIsNull() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Money(AMOUNT, null)
        );
        
        assertEquals("Currency is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenCurrencyIsEmpty() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Money(AMOUNT, "  ")
        );
        
        assertEquals("Currency is required", exception.getMessage());
    }
    
    @Test
    void shouldAddMoneySuccessfully() {
        // Arrange
        BigDecimal additionalAmount = new BigDecimal("50.00");
        Money moneyToAdd = new Money(additionalAmount, CURRENCY);
        
        // Act
        money.add(moneyToAdd);
        
        // Assert
        assertEquals(new BigDecimal("150.00"), money.getAmount());
    }
    
    @Test
    void shouldThrowExceptionWhenAddingNullMoney() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.add(null)
        );
        
        assertEquals("Money is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenAddingMoneyWithDifferentCurrency() {
        // Arrange
        Money moneyWithDifferentCurrency = new Money(AMOUNT, "USD");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.add(moneyWithDifferentCurrency)
        );
        
        assertEquals("Currency mismatch", exception.getMessage());
    }
    
    @Test
    void shouldSubtractMoneySuccessfully() {
        // Arrange
        BigDecimal subtractAmount = new BigDecimal("30.00");
        Money moneyToSubtract = new Money(subtractAmount, CURRENCY);
        
        // Act
        money.sub(moneyToSubtract);
        
        // Assert
        assertEquals(new BigDecimal("70.00"), money.getAmount());
    }
    
    @Test
    void shouldThrowExceptionWhenSubtractingNullMoney() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.sub(null)
        );
        
        assertEquals("Money is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenSubtractingMoneyWithDifferentCurrency() {
        // Arrange
        Money moneyWithDifferentCurrency = new Money(AMOUNT, "USD");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.sub(moneyWithDifferentCurrency)
        );
        
        assertEquals("Currency mismatch", exception.getMessage());
    }
    
    @Test
    void shouldChangeAmountSuccessfully() {
        // Arrange
        BigDecimal newAmount = new BigDecimal("200.00");
        Money newMoney = new Money(newAmount, CURRENCY);
        
        // Act
        money.changeAmount(newMoney);
        
        // Assert
        assertEquals(newAmount, money.getAmount());
    }
    
    @Test
    void shouldThrowExceptionWhenChangingAmountWithNullMoney() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.changeAmount(null)
        );
        
        assertEquals("Money is required", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenChangingAmountWithDifferentCurrency() {
        // Arrange
        Money moneyWithDifferentCurrency = new Money(AMOUNT, "USD");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.changeAmount(moneyWithDifferentCurrency)
        );
        
        assertEquals("Currency mismatch", exception.getMessage());
    }
    
    @Test
    void shouldMultiplySuccessfully() {
        // Arrange
        Integer multiplier = 3;
        
        // Act
        money.multiply(multiplier);
        
        // Assert
        assertEquals(new BigDecimal("300.00"), money.getAmount());
    }
    
    @Test
    void shouldThrowExceptionWhenMultiplyingByZero() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.multiply(0)
        );
        
        assertEquals("quantity must be greater than zero", exception.getMessage());
    }
    
    @Test
    void shouldThrowExceptionWhenMultiplyingByNegativeNumber() {
        // Arrange, Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.multiply(-1)
        );
        
        assertEquals("quantity must be greater than zero", exception.getMessage());
    }
    
    @Test
    void shouldGetAmountSuccessfully() {
        // Act & Assert
        assertEquals(AMOUNT, money.getAmount());
    }
    
    @Test
    void shouldGetCurrencySuccessfully() {
        // Act & Assert
        assertEquals(CURRENCY, money.getCurrency());
    }
    
    @Test
    void shouldAddZeroAmountMoney() {
        // Arrange
        Money zeroMoney = new Money(BigDecimal.ZERO, CURRENCY);
        BigDecimal originalAmount = money.getAmount();
        
        // Act
        money.add(zeroMoney);
        
        // Assert
        assertEquals(originalAmount, money.getAmount());
    }
    
    @Test
    void shouldSubtractZeroAmountMoney() {
        // Arrange
        Money zeroMoney = new Money(BigDecimal.ZERO, CURRENCY);
        BigDecimal originalAmount = money.getAmount();
        
        // Act
        money.sub(zeroMoney);
        
        // Assert
        assertEquals(originalAmount, money.getAmount());
    }
    
    @Test
    void shouldChangeToSameAmount() {
        // Arrange
        Money sameMoney = new Money(AMOUNT, CURRENCY);
        
        // Act
        money.changeAmount(sameMoney);
        
        // Assert
        assertEquals(AMOUNT, money.getAmount());
    }
    
    @Test
    void shouldMultiplyByOne() {
        // Arrange
        Integer multiplier = 1;
        BigDecimal originalAmount = money.getAmount();
        
        // Act
        money.multiply(multiplier);
        
        // Assert
        assertEquals(originalAmount, money.getAmount());
    }
    
    @Test
    void shouldCreateMoneyWithZeroAmount() {
        // Arrange & Act
        Money zeroMoney = new Money(BigDecimal.ZERO, CURRENCY);
        
        // Assert
        assertEquals(BigDecimal.ZERO, zeroMoney.getAmount());
        assertEquals(CURRENCY, zeroMoney.getCurrency());
    }
    
    @Test
    void shouldValidateMoneyWithValidValues() {
        // Arrange
        BigDecimal validAmount = new BigDecimal("50.00");
        String validCurrency = "EUR";
        
        // Act & Assert
        assertDoesNotThrow(() -> new Money(validAmount, validCurrency));
    }
} 