package io.github.vitordalvi.vAuction.features.auction.exceptions;

public class BidNotFoundException extends RuntimeException {
    public BidNotFoundException(String message) {
        super(message);
    }
}
