package io.github.vitordalvi.vAuction;

import io.github.vitordalvi.vAuction.config.ConfigLoader;
import io.github.vitordalvi.vAuction.config.features.DbConfig;
import io.github.vitordalvi.vAuction.config.features.LaunchPadConfig;
import org.bukkit.plugin.Plugin;

public class Manager {

    private final ConfigLoader configLoader;
    private final LaunchPadConfig launchPadConfig;
    private final DbConfig databaseConfig;

    public Manager(Plugin plugin) {
        this.configLoader = new ConfigLoader(plugin);
        this.launchPadConfig = new LaunchPadConfig(this.configLoader);
        this.databaseConfig = new DbConfig(this.configLoader);
    }

    public void loadData() {

    }

    public void loadConfigs() {
        configLoader.load();
        launchPadConfig.load();
        databaseConfig.load();
    }

    public LaunchPadConfig getLaunchPadConfig() {
        return launchPadConfig;
    }

    public ConfigLoader getConfigLoader() {
        return configLoader;
    }
}
