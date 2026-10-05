package io.github.vitordalvi.vAuction.features.auction.exceptions;

public class BidLowerPriceException extends RuntimeException {
  public BidLowerPriceException(String message) {
    super(message);
  }
}
