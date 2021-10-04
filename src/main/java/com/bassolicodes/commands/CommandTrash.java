package com.bassolicodes.commands;

import com.bassolicodes.Core;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CommandTrash {

    FileConfiguration config = Core.getInstance().getConfig();

    @Command(
            name = "lixeira",
            aliases = {"lixo", "trash"},
            target = CommandTarget.PLAYER,
            permission = "core.lixeira"
    )

    public void handleTrash(Context<CommandSender> context) {

        val player = (Player) context.getSender();

        Inventory inventory = Bukkit.createInventory(player, 36, String.format(config.getString("Message.Trash_Title").replace("&", "§"), player.getName()));
        player.openInventory(inventory);

    }
}
