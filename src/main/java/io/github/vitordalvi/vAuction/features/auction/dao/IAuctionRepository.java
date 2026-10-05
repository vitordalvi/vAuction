package io.github.vitordalvi.vAuction.features.auction.dao;

import io.github.vitordalvi.vAuction.features.auction.entities.Auction;
import io.github.vitordalvi.vAuction.features.auction.entities.Auctioned;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IAuctionRepository {

    // Auctions
    CompletableFuture<Map<UUID, Auction>> loadActiveAuctions(); // Loads only active auctions
    CompletableFuture<Map<UUID, Auction>> loadAllAuctions(int limit); // Loads every auction with limit

    CompletableFuture<Boolean> saveAuctionAsync(Auction auction);

    //Auctioned Items
    CompletableFuture<Map<UUID, Auctioned>> loadAllActiveItems();

    CompletableFuture<Boolean> saveAuctionedItem(Auctioned auctionedItem);
    CompletableFuture<Boolean> deleteAuctionedItem(UUID auctionedId);
}
