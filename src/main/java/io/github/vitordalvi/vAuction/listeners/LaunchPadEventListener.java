package io.github.vitordalvi.vAuction.listeners;

import io.github.vitordalvi.vAuction.config.features.LaunchPadConfig;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class LaunchPadEventListener implements Listener {

    private final Plugin plugin;
    private final LaunchPadConfig config;

    public LaunchPadEventListener(Plugin plugin, LaunchPadConfig config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Material launchPadBlock = config.getLaunchPadMaterial();

        if (event.getFrom().getBlockX() == event.getTo().getBlockX() &&
        event.getFrom().getBlockZ() == event.getTo().getBlockZ() &&
        event.getFrom().getBlockY() == event.getTo().getBlockY()) {
            return;
        }

        Block specialBlock = event.getTo().getBlock().getRelative(BlockFace.DOWN);

        if (specialBlock.getType() == launchPadBlock) {
            Player player = event.getPlayer();

            player.setAllowFlight(true);
            player.setVelocity(event.getPlayer().getVelocity().setY(4));
            player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_7, SoundCategory.HOSTILE, 1.0f, 1.2f);

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                if (player.isOnline()) {
                    player.setAllowFlight(false);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
                    player.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, player.getLocation(), 5);
                }
            }, 20L);
        }

    }

}
