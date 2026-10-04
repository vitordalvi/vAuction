package io.github.vitordalvi.vAuction.features.auction;

import io.github.vitordalvi.vAuction.features.economy.common.EconomyType;

import java.util.HashMap;
import java.util.UUID;

public class Auctioned {
    private UUID id;
    private Double amount;
    private Double startingPrice;
    private Double currentPrice;
    private Double finalPrice;
    private HashMap<UUID, Double> highestBid;
    private EconomyType economyType;
}
