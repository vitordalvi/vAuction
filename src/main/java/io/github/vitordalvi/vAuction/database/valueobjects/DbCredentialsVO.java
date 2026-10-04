package io.github.vitordalvi.vAuction.database.valueobjects;

import io.github.vitordalvi.vAuction.database.enums.SGBDType;
import io.github.vitordalvi.vAuction.database.exceptions.DatabaseException;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Objects;

public record DbCredentialsVO(String address,
                              String port,
                              String database,
                              String username,
                              String password) {

    public DbCredentialsVO {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Database address cannot be null or blank");
        }
        if (port == null || port.isBlank()) {
            throw new IllegalArgumentException("Database port cannot be null or blank");
        }
        if (database == null || database.isBlank()) {
            throw new IllegalArgumentException("Database name cannot be null or blank");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Database username cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Database password cannot be null or blank");
        }
    }


    public String getJdbcUrl(SGBDType sgbd) {
        switch (sgbd) {
            case MYSQL, HIKARI -> {
                return "jdbc:mysql://" + address + ":" + port + "/" + database + "?useSSL=false&autoReconnect=true";
            }

            case POSTGRESQL -> {
                throw new NotImplementedException("PostgreSQL database pattern wasn't implemented yet!");
            }

            case MONGODB -> {
                throw new NotImplementedException("MongoDB database platform wasn't implemented yet!");
            }

            case null, default -> {
                throw new DatabaseException("Unknown database type: " + sgbd);
            }
        }
    }

}
