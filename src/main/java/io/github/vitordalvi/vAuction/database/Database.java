package io.github.vitordalvi.vAuction.database;

import io.github.vitordalvi.vAuction.database.enums.SGBDType;

public abstract class Database {

    private final SGBDType sgbd;
    private final String DB_ADDRESS;
    private final String DB_PORT;
    private final String DB_NAME;
    private final String DB_USERNAME;
    private final String DB_PASSWORD;

    public Database(SGBDType sgbd, String DB_ADDRESS, String DB_PORT, String DB_NAME, String DB_USERNAME, String DB_PASSWORD) {
        this.sgbd = sgbd;
        this.DB_ADDRESS = DB_ADDRESS;
        this.DB_PORT = DB_PORT;
        this.DB_NAME = DB_NAME;
        this.DB_USERNAME = DB_USERNAME;
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public abstract void connect();

}
