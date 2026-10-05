package io.github.vitordalvi.vAuction.features.economy.common;

import io.github.vitordalvi.vAuction.features.economy.exceptions.EconomyException;

public abstract class Economy {
    protected Double amount;
    protected EconomyType type;

    public Economy(EconomyType type) {
        this.type = type;
    }

    public EconomyType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        if (amount == null || amount < 0) {
            throw new EconomyException("Amount is not valid.");
        }

        this.amount = amount;
    }

    public void remove(Double amount) {
        if (amount == null || amount > this.amount) {
            throw new EconomyException("Amount to remove cannot exceed current amount.");
        }

        this.amount -= amount;
    }

    public void add(Double amount) {
        if (amount == null || amount <= 0) {
            throw new EconomyException("Amount to add must be positive.");
        }

        this.amount += amount;
    }


}
