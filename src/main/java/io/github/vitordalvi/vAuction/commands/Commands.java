package io.github.vitordalvi.vAuction.commands;

import io.github.vitordalvi.vAuction.AuctionPlugin;
import io.github.vitordalvi.vAuction.config.features.LaunchPadConfig;
import org.bukkit.command.Command;

public class Commands {

    private final AuctionPlugin plugin;

    public Commands(AuctionPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerAll() {
        registerCommand(new LaunchPadCommand(plugin, LaunchPadConfig.getInstance()));

    }

    private void registerCommand(Command command) {
        String prefix = plugin.getName().toLowerCase();
        plugin.getServer().getCommandMap().register(prefix, command);
    }
}
