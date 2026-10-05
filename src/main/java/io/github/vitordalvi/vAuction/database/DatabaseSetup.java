package io.github.vitordalvi.vAuction.database;

import io.github.vitordalvi.vAuction.database.common.base.Database;
import io.github.vitordalvi.vAuction.database.common.enums.SGBDType;
import io.github.vitordalvi.vAuction.database.common.valueobjects.DbCredentialsVO;
import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class DatabaseSetup {

    private final Plugin plugin;
    private final String version;

    private final Database database;
    private final DbCredentialsVO credentials;

    private SGBDType sgbdType;

    public DatabaseSetup(Plugin plugin, DbCredentialsVO credentials, @NotNull String version, SGBDType sgbdType) {
        this.plugin = plugin;
        this.version = version;
        this.credentials = credentials;

        switch (sgbdType) {
            case HIKARI -> this.database = new HikariDatabase(credentials);
            case MYSQL -> throw new NotImplementedException("MySQL database usage is not available yet!");
            case MONGODB -> throw new NotImplementedException("MongoDB database usage is not available yet!");
            case null, default -> throw new IllegalArgumentException("Select a valid SGBD Type");
        }
    }

    public Database getDatabase() {
        return this.database;
    }
}
