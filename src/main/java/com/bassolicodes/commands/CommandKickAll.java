package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandKickAll {

    FileConfiguration config = MinecraftCore.getInstance().getConfig();

    @Command(
            name = "kickall",
            permission = "core.kickall"
    )

    public void handleKickAll(Context<Player> context) {
        for (Player allPlayers : Bukkit.getServer().getOnlinePlayers()) {
            if (!allPlayers.hasPermission("core.kickall"))
                allPlayers.kickPlayer(String.format(config.getString("Message.Maintence_Kick").replace("&", "§"), allPlayers.getName()));
        }
    }
}
