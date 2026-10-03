package io.github.vitordalvi.vAuction.database;

import io.github.vitordalvi.vAuction.database.enums.SGBDType;
import io.github.vitordalvi.vAuction.database.valueobjects.DbCredentialsVO;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Database {

    protected final DbCredentialsVO credentials;
    protected Connection connection;

    public Database(DbCredentialsVO credentials) {
        this.credentials = credentials;
    }

    public abstract void connect() throws SQLException;
    public abstract void disconnect() throws SQLException;

    public Connection getConnection() throws SQLException {
        return connection;
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (Exception ex) {
            return false;
        }
    }

}
