package io.github.vitordalvi.vAuction.config.features;

import io.github.vitordalvi.vAuction.config.ConfigLoader;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

public class LaunchPadConfig {

    private final ConfigLoader configLoader;
    private Material launchPadMaterial;

    public LaunchPadConfig(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    public void load() {
        YamlConfiguration config = configLoader.getConfig();

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

        configLoader.set("launchpad.material", material.name());
    }
}
