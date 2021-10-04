package com.bassolicodes.commands;

import com.bassolicodes.Core;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandWebsite {

    FileConfiguration config = Core.getInstance().getConfig();

    @Command(
            name = "site",
            aliases = {"website", "web"}
    )

    public void handleWebsite(Context<CommandSender> context) {
        val player = (Player) context.getSender();

        player.sendMessage(config.getString("Message.Website").replace("&", "§"));
    }

}
