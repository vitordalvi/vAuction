package io.github.vitordalvi.vAuction.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.vitordalvi.vAuction.database.enums.SGBDType;
import io.github.vitordalvi.vAuction.database.valueobjects.DbCredentialsVO;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariDatabase extends Database {
    private HikariDataSource dataSource;

    public HikariDatabase(DbCredentialsVO credentials) {
        super(credentials);
    }

    @Override
    public void connect() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(credentials.getJdbcUrl(SGBDType.HIKARI));
        config.setUsername(credentials.username());
        config.setPassword(credentials.password());

        dataSource = new HikariDataSource(config);
        connection = dataSource.getConnection();
    }

    @Override
    public void disconnect() throws SQLException {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public boolean isConnected() {
        try {
            return dataSource != null && !dataSource.isClosed();
        } catch (Exception ex) {
            return false;
        }
    }
}