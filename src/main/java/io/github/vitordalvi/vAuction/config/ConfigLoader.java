package io.github.vitordalvi.vAuction.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigLoader {

    private static ConfigLoader instance;
    private final Plugin plugin;
    private File file;
    private YamlConfiguration config;

    public ConfigLoader(Plugin plugin) {
        this.plugin = plugin;
        instance = this;
    }

    public void load() {
        file = new File(plugin.getDataFolder(), "config.yml");

        if (!file.exists()) {
            plugin.saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
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

    public YamlConfiguration getConfig() {
        return config;
    }

    public static ConfigLoader getInstance() {
        return instance;
    }
}
