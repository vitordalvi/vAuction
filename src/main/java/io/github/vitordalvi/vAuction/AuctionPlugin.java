package io.github.vitordalvi.vAuction;

import io.github.vitordalvi.vAuction.commands.LaunchPadCommand;
import io.github.vitordalvi.vAuction.config.FileUtils;
import io.github.vitordalvi.vAuction.listeners.LaunchPadEventListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class AuctionPlugin extends JavaPlugin {

    private BukkitTask task;

    @Override
    public void onEnable() {
        getLogger().info("§a vAuction has been started!");

        this.getServer().getPluginManager().registerEvents(new LaunchPadEventListener(
                this, FileUtils.getInstance()), this);

        getServer().getCommandMap().register("vauction", new LaunchPadCommand(this,
                FileUtils.getInstance()));

        FileUtils.getInstance().load();
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
