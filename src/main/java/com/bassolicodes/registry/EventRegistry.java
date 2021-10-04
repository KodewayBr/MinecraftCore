package com.bassolicodes.registry;

import com.bassolicodes.Core;
import com.bassolicodes.events.ServerAndPlayersEvents;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

@Data(staticConstructor = "of")
public class EventRegistry {

    private final Core plugin;

    public void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ServerAndPlayersEvents(), plugin);

    }

}
