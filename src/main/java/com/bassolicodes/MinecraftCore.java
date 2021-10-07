package com.bassolicodes;

import com.bassolicodes.configuration.ConfigurationRegistry;
import lombok.val;
import lombok.Getter;
import com.bassolicodes.registry.CommandRegistry;
import com.bassolicodes.registry.EventRegistry;
import com.bassolicodes.utils.Config;
import com.bassolicodes.utils.TextLogger;
import com.google.common.base.Stopwatch;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class MinecraftCore extends JavaPlugin {

    public static MinecraftCore instance;
    private final TextLogger textLogger = new TextLogger();
    public static Config config;

    @Override
    public void onEnable() {
        try {
            textLogger.info("Iniciando o carregamento do plugin.");
            val loadTime = Stopwatch.createStarted();

            loadConfig();
            instance = this;
            allRecords();

            loadTime.stop();
            textLogger.info(String.format("Sucesso! O Plugin foi inicializado com sucesso. (%s)", loadTime));
        } catch (Exception e) {
            textLogger.error("Erro! O Plugin ocorreu problemas na inicialização.");
            e.getMessage();
        }
    }

    public void loadConfig() {
        try {
            val loadConfigTiming = Stopwatch.createStarted();

            textLogger.info("Carregando configurações do plugin!");
            config = new Config(this, "config.yml");
            saveDefaultConfig();

            ConfigurationRegistry.of(this).setupConfiguration();

            loadConfigTiming.stop();
            textLogger.info(String.format("As informações da configuração fora lidas. (%s)", loadConfigTiming));
        } catch (Throwable e) {
            textLogger.error("Ocorreu um erro ao carregar as configurações!");
            e.getMessage();
        }
    }

    public void allRecords() {
        try {
            val allRecordsTiming = Stopwatch.createStarted();
            CommandRegistry.of(this).register();

            textLogger.info(String.format("Comandos registrados foram verificados... [1/2] (%s)", allRecordsTiming));
            allRecordsTiming.stop();

            val allEventsTiming = Stopwatch.createStarted();
            EventRegistry.of(this).register();
            allEventsTiming.stop();
            textLogger.info(String.format("Eventos registrados foram verificados... [2/2] (%s)", allEventsTiming));
        } catch (Exception e) {
            textLogger.error("Ocorreu um erro com o registro de eventos e comando, verifique!");
            e.getMessage();
        }
    }

    public static MinecraftCore getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        val disableTiming = Stopwatch.createStarted();

        saveConfig();

        disableTiming.stop();
        textLogger.info(String.format("O plugin foi encerrado com sucesso. (%s)", disableTiming));
    }
}
