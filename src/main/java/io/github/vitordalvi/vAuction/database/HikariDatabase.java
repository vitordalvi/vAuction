package io.github.vitordalvi.vAuction.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.vitordalvi.vAuction.database.common.base.Database;
import io.github.vitordalvi.vAuction.database.common.enums.SGBDType;
import io.github.vitordalvi.vAuction.database.common.valueobjects.DbCredentialsVO;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariDatabase extends Database {

    // dependency injection
    private HikariDataSource dataSource;

    // implementation of database base class
    public HikariDatabase(DbCredentialsVO credentials) {
        super(credentials);
    }

    // implementation of database base class connect method
    @Override
    public void connect() throws SQLException {
        // using hikari own way to manage database
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(credentials.getJdbcUrl(SGBDType.HIKARI));
        config.setUsername(credentials.username());
        config.setPassword(credentials.password());

        dataSource = new HikariDataSource(config);
    }

    @Override
    // implementation of database class disconnect method
    public void disconnect() throws SQLException {
        // if database exists, and is not closed
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close(); // close connection
        }
    }

    // get hikari connection pools
    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // check if database is connected
    @Override
    public boolean isConnected() {
        try {
            return dataSource != null && !dataSource.isClosed();
        } catch (Exception ex) {
            return false;
        }
    }
}