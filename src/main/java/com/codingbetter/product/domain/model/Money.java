package com.codingbetter.product.domain.model;

import java.math.BigDecimal;

public class Money {

    private BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        validateMoney(amount, currency);
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

    public String getCurrency() {
        return currency;
    }

    private void validateMoneyForOperation(Money money) {
        if (money == null) {
            throw new IllegalArgumentException("Money is required");
        }

        if(!money.getCurrency().equals(this.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
        this.validateMoney(money.getAmount(), money.getCurrency());
    }

    private void validateMoney(BigDecimal amount, String currency) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount is required");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency is required");
        }
    }
}
