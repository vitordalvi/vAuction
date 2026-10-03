package io.github.vitordalvi.vAuction.commands;

import io.github.vitordalvi.vAuction.AuctionPlugin;
import io.github.vitordalvi.vAuction.config.FileUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LaunchPadCommand extends Command {
    private final AuctionPlugin plugin;
    private final FileUtils config;

    public LaunchPadCommand(AuctionPlugin plugin, FileUtils config) {
        super("launchpad",
                "LaunchPad commands",
                "/launchpad",
                List.of("lcp"));
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command is only available for players!");
            return true;
        }

        Material launchPadMaterial = config.getLaunchpadMaterial();
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("§a§l    LAUNCHPAD | COMANDOS\n");
            player.sendMessage("§7 - §e/launchpad ver §7- §fCheck current material");
            player.sendMessage("§7 - §e/launchpad <material> §7- §fChange the LaunchPad material");
            return true;
        }

        if (args[0].equalsIgnoreCase("ver") && args.length < 2) {
            player.sendMessage("§7LaunchPad current material is: §e" + launchPadMaterial.name());
            return true;
        }

        if (args.length < 2) {
            try {
                Material newMaterial = Material.valueOf(args[0].toUpperCase());

                if (launchPadMaterial.equals(newMaterial)) {
                    player.sendMessage("§cThis material is already set as the LaunchPad material!");
                    return true;
                }

                if (!config.isValid(newMaterial)) {
                    player.sendMessage("§cMaterial §f§l" + args[0] + " §c is not valid!");
                    return true;
                }

                config.setLaunchPadMaterial(newMaterial);
                player.sendMessage("§aLaunchPad material has been updated to §f§l" + newMaterial.name() + "§a!");
                return true;

            } catch (IllegalArgumentException ex) {
                player.sendMessage("§cMaterial " + args[0] + " doesn't exists!");
                return true;
            }
        }

        return false;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) throws IllegalArgumentException {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            String typed = args[0].toUpperCase();

            if ("VER".startsWith(typed)) {
                suggestions.add("ver");
            }

            for (Material material : Material.values()) {
                if (material.isBlock() && material.isSolid() && !material.isAir()) {
                    if (material.name().startsWith(typed)) {
                        suggestions.add(material.name());
                    }
                }
            }
        }

        return suggestions;
    }
}
