package com.bassolicodes.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandMaintence {

    public static boolean maintenceStatus = false;

    @Command(
            name = "manutencao",
            aliases = "maintence",
            permission = "core.manutencao"
    )

    public void handleMaintence(Context<Player> context) {
        val player = context.getSender();


        for (Player allPlayers : Bukkit.getServer().getOnlinePlayers()) {

            if (!maintenceStatus) {

                maintenceStatus = true;

                if (!allPlayers.hasPermission("minister.manutencao")) {
                    allPlayers.kickPlayer("§cServidor entrou em manutenção, avisaremos a volta no discord.");
                } else {
                    if (maintenceStatus) {
                        maintenceStatus = false;
                        player.sendMessage("§cO servidor está em manutenção!");
                    }
                }
            }

        }

    }

}
