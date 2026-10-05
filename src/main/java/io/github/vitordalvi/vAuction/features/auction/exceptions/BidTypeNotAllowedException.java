package io.github.vitordalvi.vAuction.features.auction.exceptions;

public class BidTypeNotAllowedException extends RuntimeException {
    public BidTypeNotAllowedException(String message) {
        super(message);
    }
}
