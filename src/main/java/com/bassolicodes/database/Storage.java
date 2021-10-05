package com.bassolicodes.database;

import com.bassolicodes.utils.TextLogger;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Storage {

    private final TextLogger textLogger = new TextLogger();
    private Connection connection;

    public Storage(String address, String user, String password, String database, int port) {
        try {
            textLogger.info("Estabelecendo conexão com o MySQL...");
            String url = "jdbc:mysql://" + address + ":" + port + "/" + database + "?autoReconnect=true";
            this.connection = DriverManager.getConnection(url, user, password);
            textLogger.info("A conexão foi estabelecida.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Validate.notNull(connection, "[MinecraftCore] A conexão não foi estabelecida.");
        return connection;
    }

}
