package com.bassoli;

import com.bassoli.registry.CommandRegistry;
import com.bassoli.registry.EventRegistry;
import com.bassoli.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    public static Core instance;
    public static Config config;

    @Override
    public void onEnable() {
        instance = this;
        config = new Config(this, "config.yml");
        System.out.println("O plugin foi habilitado corretamente.");
        saveDefaultConfig();
        allRecords();
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
