package io.github.vitordalvi.vAuction.config.features;

import io.github.vitordalvi.vAuction.config.ConfigLoader;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

public class LaunchPadConfig {

    private static LaunchPadConfig instance;
    private Material launchPadMaterial;

    public LaunchPadConfig() {
        instance = this;
    }

    public void loadConfig() {
        YamlConfiguration config = ConfigLoader.getInstance().getConfig();

        String materialName = config.getString("launchpad.material", "IRON_BLOCK");

        try {
            launchPadMaterial = Material.valueOf(materialName.toUpperCase());
        } catch (IllegalArgumentException ex) {
            launchPadMaterial = Material.IRON_BLOCK;
        }
    }

    public boolean isValid(Material material) {
        return !material.isAir() && material.isSolid() && material.isBlock();
    }

    public Material getLaunchPadMaterial() {
        return launchPadMaterial;
    }

    public void setLaunchPadMaterial(Material material) {
        this.launchPadMaterial = material;

        ConfigLoader.getInstance().set("launchpad.material", material.name());
    }

    public static LaunchPadConfig getInstance() {
        return instance;
    }
}
