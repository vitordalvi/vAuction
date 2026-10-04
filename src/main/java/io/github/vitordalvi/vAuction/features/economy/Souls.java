package io.github.vitordalvi.vAuction.features.economy;

import io.github.vitordalvi.vAuction.features.economy.common.Economy;
import io.github.vitordalvi.vAuction.features.economy.common.EconomyType;

public class Souls extends Economy {

    public Souls(Double amount) {
        super(EconomyType.SOULS);
        this.amount = amount;
    }
}
