package com.codingbetter.domain.catalog.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Money Tests")
class MoneyTest {

    @Test
    @DisplayName("Should create money successfully")
    void shouldCreateMoneySuccessfully() {
        // Arrange
        BigDecimal amount = new BigDecimal("100.00");
        Currency currency = Currency.getInstance("USD");

        // Act
        Money money = new Money(amount, currency);

        // Assert
        assertNotNull(money);
        assertEquals(amount, money.getAmount());
        assertEquals(currency, money.getCurrency());
    }

    @Test
    @DisplayName("Should throw exception when amount is null")
    void shouldThrowExceptionWhenAmountIsNull() {
        // Arrange
        Currency currency = Currency.getInstance("USD");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Money(null, currency)
        );
        assertEquals("Amount is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when currency is null")
    void shouldThrowExceptionWhenCurrencyIsNull() {
        // Arrange
        BigDecimal amount = new BigDecimal("100.00");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Money(amount, null)
        );
        assertEquals("Currency is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when amount is negative")
    void shouldThrowExceptionWhenAmountIsNegative() {
        // Arrange
        BigDecimal amount = new BigDecimal("-100.00");
        Currency currency = Currency.getInstance("USD");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Money(amount, currency)
        );
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }

    @Test
    @DisplayName("Should add money successfully")
    void shouldAddMoneySuccessfully() {
        // Arrange
        Money money1 = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));
        Money money2 = new Money(new BigDecimal("50.00"), Currency.getInstance("USD"));

        // Act
        money1.add(money2);

        // Assert
        assertEquals(new BigDecimal("150.00"), money1.getAmount());
    }

    @Test
    @DisplayName("Should subtract money successfully")
    void shouldSubtractMoneySuccessfully() {
        // Arrange
        Money money1 = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));
        Money money2 = new Money(new BigDecimal("30.00"), Currency.getInstance("USD"));

        // Act
        money1.sub(money2);

        // Assert
        assertEquals(new BigDecimal("70.00"), money1.getAmount());
    }

    @Test
    @DisplayName("Should change amount successfully")
    void shouldChangeAmountSuccessfully() {
        // Arrange
        Money money1 = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));
        Money money2 = new Money(new BigDecimal("200.00"), Currency.getInstance("USD"));

        // Act
        money1.changeAmount(money2);

        // Assert
        assertEquals(new BigDecimal("200.00"), money1.getAmount());
    }

    @Test
    @DisplayName("Should multiply amount successfully")
    void shouldMultiplyAmountSuccessfully() {
        // Arrange
        Money money = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));

        // Act
        money.multiply(3);

        // Assert
        assertEquals(new BigDecimal("300.00"), money.getAmount());
    }

    @Test
    @DisplayName("Should throw exception when multiplying by zero or negative")
    void shouldThrowExceptionWhenMultiplyingByZeroOrNegative() {
        // Arrange
        Money money = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.multiply(0)
        );
        assertEquals("quantity must be greater than zero", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when adding null money")
    void shouldThrowExceptionWhenAddingNullMoney() {
        // Arrange
        Money money = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.add(null)
        );
        assertEquals("Money is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when subtracting null money")
    void shouldThrowExceptionWhenSubtractingNullMoney() {
        // Arrange
        Money money = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.sub(null)
        );
        assertEquals("Money is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when changing amount with null money")
    void shouldThrowExceptionWhenChangingAmountWithNullMoney() {
        // Arrange
        Money money = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money.changeAmount(null)
        );
        assertEquals("Money is required", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when currencies are different")
    void shouldThrowExceptionWhenCurrenciesAreDifferent() {
        // Arrange
        Money money1 = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));
        Money money2 = new Money(new BigDecimal("50.00"), Currency.getInstance("EUR"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> money1.add(money2)
        );
        assertEquals("Currency mismatch", exception.getMessage());
    }

    @Test
    @DisplayName("Should compare money correctly")
    void shouldCompareMoneyCorrectly() {
        // Arrange
        Money money1 = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));
        Money money2 = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));
        Money money3 = new Money(new BigDecimal("200.00"), Currency.getInstance("USD"));
        Money money4 = new Money(new BigDecimal("100.00"), Currency.getInstance("EUR"));

        // Act & Assert
        assertTrue(money1.equals(money2));
        assertFalse(money1.equals(money3));
        assertFalse(money1.equals(money4));
        assertFalse(money1.equals(null));
        assertEquals(money1.hashCode(), money2.hashCode());
        assertNotEquals(money1.hashCode(), money3.hashCode());
        assertNotEquals(money1.hashCode(), money4.hashCode());
    }

    @Test
    @DisplayName("Should have consistent hashCode")
    void shouldHaveConsistentHashCode() {
        // Arrange
        Money money = new Money(new BigDecimal("100.00"), Currency.getInstance("USD"));

        // Act & Assert
        assertEquals(money.hashCode(), money.hashCode());
    }
} 