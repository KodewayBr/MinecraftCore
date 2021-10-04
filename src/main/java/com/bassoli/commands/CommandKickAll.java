package com.bassoli.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandKickAll {

    @Command(
            name = "kickall",
            permission = "minister.kickall"
    )

    public void handleKickAll(Context<Player> context) {
        val player = (Player) context.getSender();

        for (Player allPlayers : Bukkit.getOnlinePlayers()) {
            allPlayers.kickPlayer("Você foi kickado do servidor!");
        }
    }
}
