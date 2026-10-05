package io.github.vitordalvi.vAuction.features.auction;

import io.github.vitordalvi.vAuction.features.economy.common.Economy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class Bid {
    private final UUID id;
    private final UUID playerId;
    private final UUID auctionedItemId;
    private final Economy economy;
    private final Instant timestamp;

    public Bid(UUID id, UUID playerId, UUID auctionedItemId, Economy economy, LocalDateTime timestamp) {
        this.id = UUID.randomUUID();
        this.playerId = playerId;
        this.auctionedItemId = auctionedItemId;
        this.economy = economy;
        this.timestamp = timestamp.toInstant(java.time.ZoneOffset.UTC);
    }

    public UUID getPlayerId() {
        return this.playerId;
    }

    public UUID getAuctionedItemId() {
        return this.auctionedItemId;
    }

    public Economy getEconomy() {
        return economy;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
