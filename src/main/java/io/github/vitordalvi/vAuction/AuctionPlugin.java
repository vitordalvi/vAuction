package io.github.vitordalvi.vAuction;

import io.github.vitordalvi.vAuction.commands.LaunchPadCommand;
import io.github.vitordalvi.vAuction.listeners.LaunchPadEventListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.sql.SQLException;

public final class AuctionPlugin extends JavaPlugin {

    private BukkitTask task;
    private Manager manager;

    @Override
    public void onEnable() {
        getLogger().info("§a vAuction has been started!");

        this.manager = new Manager(getInstance());
        manager.loadConfigs();
        manager.startDatabase();

        getServer().getPluginManager().registerEvents(new LaunchPadEventListener(
                this, manager.getLaunchPadConfig()), this);

        getServer().getCommandMap().register("vauction", new LaunchPadCommand(this,
                manager.getLaunchPadConfig()));
    }

    @Override
    public void onDisable() {
        getLogger().info("§c vAuction has been disabled!");

        if (task != null && !task.isCancelled()) {
            task.cancel();
        }

        if (manager.getDatabase() != null) {
            try {
                manager.getDatabase().closeConnection();
            } catch (SQLException e) {
                getLogger().severe("Error while closing database connection: " + e.getMessage());
            }
        }
    }

    public static AuctionPlugin getInstance() {
        return getPlugin(AuctionPlugin.class);
    }

    public Manager getManager() {
        return manager;
    }

    public String getVersion() {
        return getServer().getVersion();
    }

    public Plugin getPlugin() {
        return this;
    }
}
