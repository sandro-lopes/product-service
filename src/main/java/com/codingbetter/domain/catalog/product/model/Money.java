package com.codingbetter.domain.catalog.product.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

import com.codingbetter.domain.shared.model.ValueObject;

public class Money implements ValueObject<Money> {
    private BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency is required");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount is required");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public void add(Money money) {
        validateMoneyForOperation(money);
        this.amount = this.amount.add(money.getAmount());
    }

    public void sub(Money money) {
        validateMoneyForOperation(money);
        this.amount = this.amount.subtract(money.getAmount());
    }

    public void changeAmount(Money money) {
        validateMoneyForOperation(money);
        this.amount = money.getAmount();
    }

    public void multiply(Integer quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than zero");
        }
        this.amount = this.amount.multiply(new BigDecimal(quantity));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    private void validateMoneyForOperation(Money money) {
        if (money == null) {
            throw new IllegalArgumentException("Money is required");
        }

        if(!money.getCurrency().equals(this.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Money money = (Money) other;
        return sameValueAs(money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public boolean sameValueAs(Money other) {
        return amount.compareTo(other.amount) == 0 && currency.equals(other.currency);
    }
}
