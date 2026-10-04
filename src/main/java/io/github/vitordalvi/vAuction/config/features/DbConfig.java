package io.github.vitordalvi.vAuction.config.features;

import io.github.vitordalvi.vAuction.config.ConfigLoader;
import io.github.vitordalvi.vAuction.database.enums.SGBDType;
import io.github.vitordalvi.vAuction.database.valueobjects.DbCredentialsVO;
import org.bukkit.configuration.file.YamlConfiguration;

public class DbConfig {
    private ConfigLoader configLoader;
    private DbCredentialsVO credentials;
    private SGBDType type;

    private String jdbcUrl;

    public DbConfig(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    public void load() {
        YamlConfiguration config = configLoader.getConfig();

        if (!config.contains("database.sgbd")) {
            config.set("database.sgbd", "HIKARI");
            config.set("database.address", "localhost");
            config.set("database.port", "3306");
            config.set("database.name", "vauction");
            config.set("database.username", "root");
            config.set("database.password", "123456");
            config.set("database.jdbc.url", "jdbc:mysql://localhost:3306/vauction");
            configLoader.save();
        }

        credentials = new DbCredentialsVO(
                config.getString("database.address"),
                config.getString("database.port"),
                config.getString("database.name"),
                config.getString("database.username"),
                config.getString("database.password")
        );

        jdbcUrl = config.getString("database.jdbc.url");

        type = SGBDType.valueOf(config.getString("database.sgbd", "HIKARI").toUpperCase());
    }

    public DbCredentialsVO getCredentials() {
        return credentials;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public SGBDType getType() {
        return type;
    }
}
