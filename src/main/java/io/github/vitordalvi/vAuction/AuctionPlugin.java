package io.github.vitordalvi.vAuction;

import io.github.vitordalvi.vAuction.commands.LaunchPadCommand;
import io.github.vitordalvi.vAuction.config.ConfigLoader;
import io.github.vitordalvi.vAuction.config.features.LaunchPadConfig;
import io.github.vitordalvi.vAuction.listeners.LaunchPadEventListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class AuctionPlugin extends JavaPlugin {

    private BukkitTask task;

    @Override
    public void onEnable() {
        getLogger().info("§a vAuction has been started!");

        ConfigLoader configLoader = new ConfigLoader(this);
        configLoader.load();

        LaunchPadConfig launchPadConfig = new LaunchPadConfig();
        launchPadConfig.loadConfig();

        getServer().getPluginManager().registerEvents(new LaunchPadEventListener(
                this, launchPadConfig), this);

        getServer().getCommandMap().register("vauction", new LaunchPadCommand(this,
                launchPadConfig));

        ConfigLoader.getInstance().load();
    }

    @Override
    public void onDisable() {
        getLogger().info("§c vAuction has been disabled!");

        if (task != null && !task.isCancelled()) {
            task.cancel();
        }
    }

    public static AuctionPlugin getInstance() {
        return getPlugin(AuctionPlugin.class);
    }
}
