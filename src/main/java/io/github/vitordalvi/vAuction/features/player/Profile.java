package io.github.vitordalvi.vAuction.features.player;

import io.github.vitordalvi.vAuction.features.economy.common.Economy;

import java.util.Map;
import java.util.UUID;

public class Profile {
    private UUID id;
    private Map.Entry<Economy, Double> economy;

    public Profile(UUID id, Map.Entry<Economy, Double> economy) {

    }
}
