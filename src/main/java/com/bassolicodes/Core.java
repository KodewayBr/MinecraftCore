package com.bassolicodes;

import lombok.val;
import lombok.Getter;
import org.bukkit.Bukkit;
import com.bassolicodes.database.Storage;
import com.bassolicodes.registry.CommandRegistry;
import com.bassolicodes.registry.EventRegistry;
import com.bassolicodes.utils.Config;
import com.bassolicodes.utils.TextLogger;
import com.google.common.base.Stopwatch;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

@Getter
public class Core extends JavaPlugin {

    public static Core instance;
    private final TextLogger textLogger = new TextLogger();
    public static Config config;
    private Storage storage;

    @Override
    public void onEnable() {
        try {
            getLogger().info("Iniciando carregamento do plugin.");
            val loadTime = Stopwatch.createStarted();

            loadConfig();

            instance = this;
            allRecords();
            connect();

            textLogger.info(String.format("Plugin inicializado com sucesso. (%s)", loadTime));
        } catch (Exception e) {
            textLogger.error("Ocorreu um erro, tente novamente.");
            e.getMessage();
        }
    }

    public void loadConfig() {
        try {
            val loadConfigTiming = Stopwatch.createStarted();

            textLogger.info("Carregando configurações!");
            config = new Config(this, "config.yml");
            saveDefaultConfig();

            loadConfigTiming.stop();
            textLogger.info(String.format("As informações da configuração fora lidas. (%s)", loadConfigTiming));
        } catch (Throwable e) {
            textLogger.error("Ocorreu um erro ao carregar as configurações!");
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
        val allRecordsTiming = Stopwatch.createStarted();
        CommandRegistry.of(this).register();
        EventRegistry.of(this).register();

        allRecordsTiming.stop();
        textLogger.info(String.format("Todos os registros foram lidos. (%s)", allRecordsTiming));
    }

    public static Core getInstance() {
        return instance;
    }

    private void connect(){

        try {
            String address = this.getConfig().getString("database.address");
            int port =this.getConfig().getInt("database.port");
            String user =this.getConfig().getString("database.username");
            String password =this.getConfig().getString("database.password");
            String database = this.getConfig().getString("database.database");

            this.storage = new Storage(address, user, password, database, port);
        } catch (Exception e){
            textLogger.error("Falha ao conectar-se com o MYSQL...");
        }
    }

}
