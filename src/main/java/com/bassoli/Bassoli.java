package com.bassoli;

import com.bassoli.registry.CommandRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class Bassoli extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        System.out.println("O plugin foi habilitado corretamente.");
        register();
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
        System.out.println("O plugin foi desabilitado corretamente.");
    }

    public void register() {
        CommandRegistry.of(this).register();
    }

}
