package io.github.vitordalvi.vAuction;

import io.github.vitordalvi.vAuction.config.ConfigLoader;
import io.github.vitordalvi.vAuction.config.features.LaunchPadConfig;
import org.bukkit.plugin.Plugin;

public class Manager {

    private final ConfigLoader configLoader;
    private final LaunchPadConfig launchPadConfig;

    public Manager(Plugin plugin) {
        this.configLoader = new ConfigLoader(plugin);
        this.launchPadConfig = new LaunchPadConfig(this.configLoader);
    }

    public void load() {
        configLoader.load();
        launchPadConfig.load();
    }

    public LaunchPadConfig getLaunchPadConfig() {
        return launchPadConfig;
    }

    public ConfigLoader getConfigLoader() {
        return configLoader;
    }
}
