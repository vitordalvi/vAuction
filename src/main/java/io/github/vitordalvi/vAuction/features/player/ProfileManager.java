package io.github.vitordalvi.vAuction.features.player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileManager {

    private Map<UUID, Profile> cache = new ConcurrentHashMap<>();


}
