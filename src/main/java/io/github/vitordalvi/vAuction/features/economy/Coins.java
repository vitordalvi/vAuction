package io.github.vitordalvi.vAuction.features.economy;

import io.github.vitordalvi.vAuction.features.economy.common.Economy;
import io.github.vitordalvi.vAuction.features.economy.common.EconomyType;

public class Coins extends Economy {

    public Coins(Double amount) {
        super(EconomyType.COINS);
        this.amount = amount;
    }
}
