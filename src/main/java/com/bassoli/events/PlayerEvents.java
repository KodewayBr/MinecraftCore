package com.bassoli.events;

import lombok.val;
import com.bassoli.Bassoli;
import com.bassoli.commands.CommandMaintence;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    FileConfiguration config = Bassoli.getInstance().getConfig();

    @EventHandler
    public void onMaintence(PlayerJoinEvent event) {
        val player = event.getPlayer();

        event.setJoinMessage(null);

        if (CommandMaintence.maintenceStatus) {
            if (!player.hasPermission("minister.manutencao")) {
                player.kickPlayer("§cVocê não pode entrar com o servidor em manutenção!");
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
            event.setDeathMessage(null);
    }

    @EventHandler
    public void deathOnJoin(EntityPortalEnterEvent event) {
        if (event.getEntity().isDead()) {
            event.getEntity().remove();
        }
    }

    @EventHandler
    public void deathOnQuit(EntityPortalExitEvent event) {
        if (event.getEntity().isDead()) {
            event.getEntity().remove();
        }
    }

}
