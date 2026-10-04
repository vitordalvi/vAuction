package io.github.vitordalvi.vAuction.features.economy;

public abstract class Economy {
    protected double amount;
    protected EconomyType type;

    public Economy(EconomyType type) {
        this.type = type;
    }

    public EconomyType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void remove(double amount) {
        this.amount -= amount;
    }

    public void add(double amount) {
        this.amount += amount;
    }


}
