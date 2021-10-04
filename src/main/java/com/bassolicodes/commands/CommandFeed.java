package com.bassolicodes.commands;

import com.bassolicodes.Core;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandFeed {

    FileConfiguration config = Core.getInstance().getConfig();

    @Command(
            name = "feed",
            permission = "core.feed"
    )
    public void handleFeed(Context<CommandSender> context, @Optional Player target) {
        val player = (Player) context.getSender();

        if(target == null) {
            if (player.getFoodLevel() == 20) {
                player.sendMessage(config.getString("Message.Feed_Max").replace("&", "§"));
            } else {
                player.setFoodLevel(20);
                player.sendMessage(config.getString("Message.Feed_Satiated").replace("&", "§"));
            }
        } else {
          if(target.getFoodLevel() == 20) {
              player.sendMessage(config.getString("Message.Feed_Full_Player").replace("&", "§"));
          } else {
              target.setFoodLevel(20);
              player.sendMessage(String.format(config.getString("Message.Feed_Regenerated_Player").replace("&", "§"), target.getName()));
              target.sendMessage(String.format(config.getString("Message.Feed_Regenerated_By_Player").replace("&", "§"), player.getName()));
          }
        }
    }
}
