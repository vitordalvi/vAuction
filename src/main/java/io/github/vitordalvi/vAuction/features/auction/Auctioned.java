package io.github.vitordalvi.vAuction.features.auction;

import io.github.vitordalvi.vAuction.features.auction.enums.AuctionedItemStatus;
import io.github.vitordalvi.vAuction.features.auction.exceptions.BidLowerPriceException;
import io.github.vitordalvi.vAuction.features.auction.exceptions.BidNotFoundException;
import io.github.vitordalvi.vAuction.features.auction.exceptions.BidTypeNotAllowedException;
import io.github.vitordalvi.vAuction.features.economy.common.EconomyType;

import java.util.*;

public class Auctioned {
    private UUID id; // Auctioned Item id
    private UUID auctionId; // Auction id
    private UUID ownerId; // Auctioned Item owner

    private Map<EconomyType, Double> currentPrice; // Aucioned Item current price
    private Map<EconomyType, Double> startingPrice; // Starting price of the item
    private Map<EconomyType, Double> finalPrice; // Auctioned Item last Price

    private List<Bid> bids; // List of all bids for this Item
    private List<EconomyType> allowedEconomyTypes; // Allowed economies on this auction

    private AuctionedItemStatus status; // Auctioned Item status

    public Auctioned(UUID id, UUID auctionId, UUID ownerId,
                     Map<EconomyType, Double> currentPrice, Map<EconomyType, Double> startingPrice,
                     Map<EconomyType, Double> finalPrice, List<Bid> bids,
                     List<EconomyType> allowedEconomyTypes) {
        this.id = id;
        this.auctionId = auctionId;
        this.ownerId = ownerId;

        // preventing NullPointerException if any price
        this.currentPrice = currentPrice != null ? currentPrice : new HashMap<>();

        /*
        this attributes doesn't need exception preventing
        because they need to be validated (because of their economy types)
        */
        this.startingPrice = startingPrice;
        this.finalPrice = finalPrice;

        // preventing NullPointerException if any list
        this.bids = bids != null ? bids : new ArrayList<>();

        this.allowedEconomyTypes = allowedEconomyTypes;
        this.status = AuctionedItemStatus.AVAILABLE;
    }

    // add a new bid
    public void add(Bid bid) {
        EconomyType bidType = bid.getEconomy().getType(); // get bid economy type
        Double bidAmount = bid.getEconomy().getAmount(); // get bid economy amount

        // if economy is not allowed in this bid
        if (!this.allowedEconomyTypes.contains(bidType)) {
            throw new BidTypeNotAllowedException("This auction don't accept this economy type.");
        }

        // calculate highest bid for this economy type
        Double highestBidForThisType = this.currentPrice.getOrDefault(
                bidType, // get the bid as this economy type
                this.startingPrice.getOrDefault( // starting price for this economy type
                        bidType, 0.0) // if empty, return default 0.0
        );

        // if bid amount is higher than current bid
        if (bidAmount > highestBidForThisType) {
            this.bids.add(bid); // add new bid into bids list
            this.currentPrice.put(bidType, bidAmount); // update the current price for this economy type
        } else {
            // if bid amount is lower, throw exception for being treated as a lower price
            throw new BidLowerPriceException("Your bid is too low for economy: " + bidType);
        }
    }

    // remove bid
    public void remove(Bid bid) {
        // try to remove the requested bid from the bids list
        boolean wasRemoved = this.bids.remove(bid);

        // if bid was removed
        if (wasRemoved) {
            // get bid economy type
            EconomyType bidType = bid.getEconomy().getType();

            // calculate new highest bid to update current price
            Double newHighestBid = this.bids.stream()
                    // filter every bid with the same economy type
                    .filter(b -> b.getEconomy().getType().equals(bidType))
                    // check values
                    .map(b -> b.getEconomy().getAmount())
                    // find highest value
                    .max(Double::compareTo)
                    // if not found, starting price as value or 0.0 if any
                    .orElse(this.startingPrice.getOrDefault(bidType, 0.0));

            // update current price
            this.currentPrice.put(bidType, newHighestBid);
        } else {
            // throw new bid not found exception to be treated
            throw new BidNotFoundException("Requested bid wasn't found.");
        }
    }

    // get auctioned item id
    public UUID getId() {
        return this.id;
    }

    public UUID getAuctionId() {
        return this.auctionId;
    }

    // get auctioned item owner id
    public UUID getOwnerId() {
        return this.ownerId;
    }

    // get first price set for this item
    public Map<EconomyType, Double> getStartingPrice() {
        return this.startingPrice;
    }

    // get final price to auctioned item
    public Map<EconomyType, Double> getFinalPrice() {
        return this.finalPrice;
    }

    // get a list of all bids
    public List<Bid> getBids() {
        return this.bids;
    }

    // get allowed economy types
    public List<EconomyType> getAllowedEconomyTypes() {
        return this.allowedEconomyTypes;
    }

    // allow a economy type
    public void allowEconomyType(EconomyType economyType) {
        this.allowedEconomyTypes.add(economyType);
    }

    // remove economy type from allowed types
    public void forbidEconomyType(EconomyType economyType) {
        this.allowedEconomyTypes.remove(economyType);
    }

    // allow a list of economy types to be used in bids
    public void setEconomyTypes(List<EconomyType> economyTypes) {
        this.allowedEconomyTypes = economyTypes;
    }

    // get auctioned item status
    public AuctionedItemStatus getStatus() {
        return this.status;
    }

    // set a auctioned item status
    public void setStatus(AuctionedItemStatus status) {
        this.status = status;
    }

    // get current price for specific economy type
    public Map<EconomyType, Double> getCurrentPrice() {
        return currentPrice;
    }

}
