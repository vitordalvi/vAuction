package io.github.vitordalvi.vAuction.features.auction;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AuctionManager {
    public Map<UUID, Auctioned> auctionCache = new ConcurrentHashMap<>();
}
