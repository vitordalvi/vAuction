package io.github.vitordalvi.vAuction.database;

public abstract class Database {

    private final String DB_ADDRESS;
    private final String DB_PORT;
    private final String DB_NAME;
    private final String DB_USERNAME;
    private final String DB_PASSWORD;

    public Database() {

    }

    public abstract void connect();

}
