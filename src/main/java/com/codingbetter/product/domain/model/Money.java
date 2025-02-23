package com.codingbetter.product.domain.model;

import java.math.BigDecimal;

public class Money {

    private BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public void add(Money money) {
        this.amount = money.getAmount();
    }

    public void multiply(Integer quantity) {
        this.amount = this.amount.multiply(new BigDecimal(quantity));
    }

    public void sub(Money money) {
        this.amount = money.getAmount();
    }

    public void changeAmount(Money money) {
        this.amount = money.getAmount();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
