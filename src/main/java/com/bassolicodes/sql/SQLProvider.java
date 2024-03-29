package com.bassolicodes.sql;

import com.bassolicodes.MinecraftCore;
import lombok.val;
import lombok.Data;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.connector.type.SQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.SQLiteDatabaseType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

@Data(staticConstructor = "of")
public final class SQLProvider {

    private final MinecraftCore minecraftCore;

    public SQLConnector setup() {

        FileConfiguration configuration = minecraftCore.getConfig();
        ConfigurationSection databaseConfiguration = configuration.getConfigurationSection("database");

        String sqlType = databaseConfiguration.getString("type");

        val logger = minecraftCore.getTextLogger();

        SQLConnector sqlConnector;

        if (sqlType.equalsIgnoreCase("mysql")) {
            ConfigurationSection mysqlSection = databaseConfiguration.getConfigurationSection("mysql");

            sqlConnector = mysqlDatabaseType(mysqlSection).connect();

            logger.info("Conexão com o banco de dados (MySQL) realizada com sucesso.");
        } else if (sqlType.equalsIgnoreCase("sqlite")) {
            ConfigurationSection sqliteSection = databaseConfiguration.getConfigurationSection("sqlite");

            sqlConnector = sqliteDatabaseType(sqliteSection).connect();

            logger.info("Conexão com o banco de dados (SQLite) realizada com sucesso.");
            logger.warn("Recomendamos o uso do banco de dados MySQL.");
        } else {
            logger.error("O tipo de database selecionado não é válido.");

            return null;
        }

        return sqlConnector;

    }

    private SQLDatabaseType sqliteDatabaseType(ConfigurationSection section) {
        return SQLiteDatabaseType.builder()
                .file(new File(minecraftCore.getDataFolder(), section.getString("file")))
                .build();
    }

    private SQLDatabaseType mysqlDatabaseType(ConfigurationSection section) {
        return MySQLDatabaseType.builder()
                .address(section.getString("address"))
                .username(section.getString("username"))
                .password(section.getString("password"))
                .database(section.getString("database"))
                .build();
    }

}