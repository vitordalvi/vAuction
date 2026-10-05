package io.github.vitordalvi.vAuction.features.auction;

import io.github.vitordalvi.vAuction.features.auction.dao.AuctionRepository;
import io.github.vitordalvi.vAuction.features.auction.entities.Auctioned;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AuctionManager {
    private final AuctionRepository auctionRepository;

    public AuctionManager(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public Map<UUID, Auctioned> auctionCache = new ConcurrentHashMap<>();
}
