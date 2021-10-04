package com.bassoli.commands;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKickAll {

    @Command(
            name = "kickall",
            permission = "minister.kickall"
    )

    public void handleKickAll(Context<Player> context) {
        for (Player target : Bukkit.getServer().getOnlinePlayers()) {
            if (!target.hasPermission("minister.kickall"))
                target.kickPlayer("§cVocê foi kickado do servidor, tente entrar novamente ou entramos em manutenção.");
        }
    }
}
