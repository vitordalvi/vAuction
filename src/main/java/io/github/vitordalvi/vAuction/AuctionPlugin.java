package io.github.vitordalvi.vAuction;

import io.github.vitordalvi.vAuction.commands.LaunchPadCommand;
import io.github.vitordalvi.vAuction.listeners.LaunchPadEventListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class AuctionPlugin extends JavaPlugin {

    private BukkitTask task;
    private Manager manager;

    @Override
    public void onEnable() {
        getLogger().info("§a vAuction has been started!");

        Manager manager = new Manager(getInstance());
        manager.loadConfigs();

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
    }

    public static AuctionPlugin getInstance() {
        return getPlugin(AuctionPlugin.class);
    }

    public Manager getManager() {
        return manager;
    }
}
