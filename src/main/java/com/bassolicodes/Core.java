package com.bassolicodes;

import com.bassolicodes.registry.CommandRegistry;
import com.bassolicodes.registry.EventRegistry;
import com.bassolicodes.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Core extends JavaPlugin {

    public static Core instance;
    public static Config config;

    final String username = config.getConfig().getString("MySQL.username");
    final String password = config.getConfig().getString("MySQL.password");
    final String url = "jdbc:mysql://" + config.getConfig().getString("MySQL.host") + ":" + config.getConfig().getInt("MySQL.port") + "/" + config.getConfig().getString("MySQL.database");
    static Connection connection;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        config = new Config(this, "config.yml");
        System.out.println("O plugin foi habilitado corretamente.");
        saveDefaultConfig();
        allRecords();

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Plugin conectado com sucesso ao banco de dados!");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao se conectar com MySQL, revise os dados!");
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        System.out.println("O plugin foi desabilitado corretamente.");
        saveConfig();
    }

    public void allRecords() {
        CommandRegistry.of(this).register();
        EventRegistry.of(this).register();
    }

    public static Core getInstance() {
        return instance;
    }

}
