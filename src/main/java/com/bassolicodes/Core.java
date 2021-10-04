package com.bassolicodes;

import com.bassolicodes.database.SQLProvider;
import com.bassolicodes.registry.CommandRegistry;
import com.bassolicodes.registry.EventRegistry;
import com.bassolicodes.utils.Config;
import com.bassolicodes.utils.TextLogger;
import com.google.common.base.Stopwatch;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.Getter;
import lombok.val;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

@Getter
public class Core extends JavaPlugin {

    public static Core instance;
    private final TextLogger textLogger = new TextLogger();
    public static Config config;
    private SQLConnector sqlConnector;
    private SQLExecutor sqlExecutor;
    private final boolean debug = getConfig().getBoolean("plugin.debug");


    @Override
    public void onEnable() {
        try {
            getLogger().info("Iniciando carregamento do plugin.");
            val loadTime = Stopwatch.createStarted();

            sqlConnector = SQLProvider.of(this).setup();
            sqlExecutor = new SQLExecutor(sqlConnector);

            instance = this;

            loadConfig();
            allRecords();

            SQLProvider.of(this).setup();
            getLogger().log(Level.INFO, "Plugin inicializado com sucesso. ({0})", loadTime);
        } catch (Exception e) {
            textLogger.error("Ocorreu um erro, tente novamente.");
            e.getMessage();
        }
    }

    public void onLoad() {
    }

    public void loadConfig() {
        try {
            val loadConfigTiming = Stopwatch.createStarted();

            System.out.println("Carregando configurações!");
            config = new Config(this, "config.yml");
            saveDefaultConfig();

            loadConfigTiming.stop();
            textLogger.info(String.format("As informações da configuração fora lidas. (%s)", loadConfigTiming));
        } catch (Throwable e) {
            System.out.println("Ocorreu um erro ao carregar as configurações!");
            e.getMessage();
        }
    }

    @Override
    public void onDisable() {
        val disableTiming = Stopwatch.createStarted();

        saveConfig();

        disableTiming.stop();
        textLogger.info(String.format("O plugin foi encerrado com sucesso. (%s)", disableTiming));
    }

    public void allRecords() {
        CommandRegistry.of(this).register();
        EventRegistry.of(this).register();
    }

    public static Core getInstance() {
        return instance;
    }

}
