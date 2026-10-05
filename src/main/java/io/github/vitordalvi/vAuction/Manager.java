package io.github.vitordalvi.vAuction;

import io.github.vitordalvi.vAuction.config.ConfigLoader;
import io.github.vitordalvi.vAuction.config.features.DbConfig;
import io.github.vitordalvi.vAuction.config.features.LaunchPadConfig;
import io.github.vitordalvi.vAuction.database.DatabaseSetup;
import io.github.vitordalvi.vAuction.database.common.base.Database;
import org.bukkit.plugin.Plugin;

public class Manager {

    // plugin reference to dependency injection
    private final Plugin plugin;

    // Configs
    private final ConfigLoader configLoader;
    private final LaunchPadConfig launchPadConfig;
    private final DbConfig dbConfig;

    // Database
    private DatabaseSetup databaseSetup;
    private Database database;

    public Manager(Plugin plugin) {
        // plugin DI
        this.plugin = plugin;

        // Configs
        this.configLoader = new ConfigLoader(plugin);
        this.launchPadConfig = new LaunchPadConfig(this.configLoader);
        this.dbConfig = new DbConfig(this.configLoader);

        // Database
        this.database = null;
    }

    // configs loader
    public void loadConfigs() {
        configLoader.load();
        launchPadConfig.load();
        dbConfig.load();
    }

    // database starter
    public void startDatabase() {
        this.databaseSetup = new DatabaseSetup(plugin,
                dbConfig.getCredentials(),
                plugin.getServer().getVersion(),
                dbConfig.getType());

        this.database = databaseSetup.getDatabase();
    }

    // database data loader
    public void loadData() {

    }

    public Database getDatabase() {
        return this.database;
    }

    // todo refact this
    public LaunchPadConfig getLaunchPadConfig() {
        return launchPadConfig;
    }

}
