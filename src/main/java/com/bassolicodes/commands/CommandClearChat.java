package com.bassolicodes.commands;

import com.bassolicodes.Core;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandClearChat {

    FileConfiguration config = Core.getInstance().getConfig();

    @Command(
            name = "limparchat",
            aliases = {"cc", "clearchat"},
            permission = "core.clearchat"
    )
    public void handleClearChat(Context<CommandSender> context) {

        val player = (Player) context.getSender();

        String clear = StringUtils.repeat(" §c \n §c ", 100);
        Bukkit.broadcastMessage(clear);
        Bukkit.broadcastMessage(String.format(config.getString("Message.Clear_Chat").replace("&", "§"), player.getName()));

    }

}
