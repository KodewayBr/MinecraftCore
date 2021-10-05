package com.bassolicodes.database;

import com.bassolicodes.Core;
import com.bassolicodes.utils.TextLogger;
import lombok.Data;
import org.bukkit.configuration.file.FileConfiguration;


@Data(staticConstructor = "of")
public final class SQLProvider {

    private Storage storage;
    private final TextLogger textLogger = new TextLogger();

    private void connect() {

        FileConfiguration config = Core.getInstance().getConfig();

        try {
            String address = config.getString("database.address");
            int port = config.getInt("database.port");
            String user = config.getString("database.user");
            String password = config.getString("database.password");
            String database = config.getString("database.database");

            this.storage = new Storage(address, user, password, database, port);
        } catch (Exception e) {
            textLogger.error("Falha ao conectar-se com o MYSQL...");
            e.getMessage();
        }

    }

    public Storage getStorage() {
        return storage;
    }

}