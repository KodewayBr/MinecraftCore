package com.bassolicodes.commands;

import com.bassolicodes.Core;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandWebsite {

    FileConfiguration config = Core.getInstance().getConfig();

    @Command(
            name = "site",
            aliases = {"website", "web"}
    )

    public void handleWebsite(Context<Player> context) {
        val player = (Player) context.getSender();

        if (player == null) {
            return;
        }

        player.sendMessage(String.valueOf(config.getStringList("Message.Website")).replace("&", "§"));
    }
}
