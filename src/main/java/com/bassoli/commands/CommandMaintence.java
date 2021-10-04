package com.bassoli.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandMaintence {

    @Command(
            name = "manutencao",
            aliases = "maintence",
            permission = "minister.manutencao"
    )

    public void handleMaintence(Context<Player> context) {
        val player = context.getSender();

        if(Bukkit.getServer().hasWhitelist()) {
            Bukkit.getServer().setWhitelist(false);
            player.sendMessage("§aO servidor foi aberto para todos os jogadores!");
        } else {
            Bukkit.getServer().setWhitelist(true);
            player.sendMessage("§cServidor fechado para todos!");
            for (Player allPlayers : Bukkit.getServer().getOnlinePlayers()) {
                if (!allPlayers.hasPermission("minister.manutencao"))
                    allPlayers.kickPlayer("§cServidor entrou em manutenção, avisaremos a volta no discord.");
            }
        }

    }

}
