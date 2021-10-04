package com.bassoli;

import com.bassoli.registry.CommandRegistry;
import com.bassoli.registry.EventRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class Bassoli extends JavaPlugin {

    public static Bassoli instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("onEnable is called!");
        System.out.println("O plugin foi habilitado corretamente.");
        saveDefaultConfig();
        allRecords();
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
        System.out.println("O plugin foi desabilitado corretamente.");
        saveConfig();
    }

    public void allRecords() {
        CommandRegistry.of(this).register();
        EventRegistry.of(this).register();
    }

    public static Bassoli getInstance() {
        return instance;
    }

}
