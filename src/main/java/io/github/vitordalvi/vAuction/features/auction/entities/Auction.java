package io.github.vitordalvi.vAuction.features.auction.entities;

import java.util.Map;
import java.util.UUID;

public class Auction {
    private UUID id;
    private Map<UUID, Auctioned> auctionedItems;
    private boolean enabled;

    public Auction(UUID id, Map<UUID, Auctioned> auctionedItems) {
        this.id = UUID.randomUUID();
        this.auctionedItems = auctionedItems;
        this.enabled = false;
    }


}
