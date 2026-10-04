package io.github.vitordalvi.vAuction.features.economy;

import io.github.vitordalvi.vAuction.features.economy.common.Economy;
import io.github.vitordalvi.vAuction.features.economy.common.EconomyType;

public class Cash extends Economy {

    public Cash(Double amount) {
        super(EconomyType.CASH);
        this.amount = amount;
    }
}
