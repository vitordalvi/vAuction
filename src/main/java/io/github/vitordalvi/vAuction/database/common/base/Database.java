package io.github.vitordalvi.vAuction.database.common.base;

import io.github.vitordalvi.vAuction.database.common.valueobjects.DbCredentialsVO;

import java.sql.Connection;
import java.sql.SQLException;

// Base class for any database implementation
public abstract class Database {
    protected final DbCredentialsVO credentials;
    protected Connection connection;

    public Database(DbCredentialsVO credentials) {
        this.credentials = credentials;
    }

    public abstract void connect() throws SQLException;
    public abstract void disconnect() throws SQLException;

    public abstract Connection getConnection() throws SQLException;

    public abstract boolean isConnected();
}
