package io.github.vitordalvi.vAuction.commands;

import io.github.vitordalvi.vAuction.AuctionPlugin;
import org.bukkit.command.Command;

public class Commands {

    private final AuctionPlugin plugin;

    public Commands(AuctionPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerAll() {
        registerCommand(new LaunchPadCommand(plugin, plugin.getManager().getLaunchPadConfig()));

    }

    private void registerCommand(Command command) {
        String prefix = plugin.getName().toLowerCase();
        plugin.getServer().getCommandMap().register(prefix, command);
    }
}
