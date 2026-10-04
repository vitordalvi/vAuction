package io.github.vitordalvi.vAuction.features.economy;

import io.github.vitordalvi.vAuction.features.economy.common.Economy;
import io.github.vitordalvi.vAuction.features.economy.common.EconomyType;

public class Power extends Economy {

    public Power(Double amount) {
        super(EconomyType.POWER);
        this.amount = amount;
    }
}
