package io.github.vitordalvi.vAuction.config;

import io.github.vitordalvi.vAuction.AuctionPlugin;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import org.bukkit.Material;
import org.bukkit.block.BlockType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class FileUtils {

    private final Plugin plugin = AuctionPlugin.getInstance();
    private final static FileUtils instance = new FileUtils();

    private File file;
    private YamlConfiguration config;

    private Material launchPadMaterial;

    private FileUtils() {
    }

    public void load() {
        file = new File(plugin.getDataFolder(), "config.yml");

        if (!file.exists()) {
            plugin.saveResource("config.yml", false);
            set(file.getPath(), "material");
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        launchPadMaterial = Material.valueOf(config.getString("material", "IRON_BLOCK"));

        if (!isValid(launchPadMaterial)) {
            plugin.getLogger().info("Using default Launch Pad material: IRON_BLOCK");
            launchPadMaterial = Material.IRON_BLOCK;
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        config.set(path, value);

        save();
    }

    public boolean isValid(Material material) {
        if (material.isAir() || !material.isSolid() && material.isBlock()) {
            plugin.getLogger().warning("Launch Pad material is not valid!");
            return false;
        }

        return true;
    }

    public Material getLaunchpadMaterial() {
        return launchPadMaterial;
    }

    public void setLaunchPadMaterial(Material launchPadMaterial) {
        this.launchPadMaterial = launchPadMaterial;

        set("material", launchPadMaterial.name());
    }

    public static FileUtils getInstance() {
        return instance;
    }
}
