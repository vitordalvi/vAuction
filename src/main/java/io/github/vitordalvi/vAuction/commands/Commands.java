package io.github.vitordalvi.vAuction.commands;

import io.github.vitordalvi.vAuction.AuctionPlugin;
import io.github.vitordalvi.vAuction.config.FileUtils;
import org.bukkit.command.Command;

public class Commands {

    private final AuctionPlugin plugin;

    public Commands(AuctionPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerAll() {
        registerCommand(new LaunchPadCommand(plugin, FileUtils.getInstance()));

    }

    private void registerCommand(Command command) {
        String prefix = plugin.getName().toLowerCase();
        plugin.getServer().getCommandMap().register(prefix, command);
    }
}
