package com.bassoli.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFeed {
    @Command(
            name = "feed",
            permission = "core.feed"
    )
    public void handleFeed(Context<CommandSender> context, @Optional Player target) {
        val player = (Player) context.getSender();

        if(target == null) {
            if (player.getFoodLevel() == 20) {
                player.sendMessage("§cSua fome está cheia.");
            } else {
                player.setFoodLevel(20);
                player.sendMessage("§aSua fome foi saciada com êxito.");
            }
        } else {
          if(target.getFoodLevel() == 20) {
              player.sendMessage("§cA fome deste jogador já está cheia.");
          } else {
              target.setFoodLevel(20);
              player.sendMessage("§eFome de §f" + target.getName() + " §efoi saciada com êxito.");
              target.sendMessage("§eSua fome foi saciada por §f" + player.getName());
          }
        }
    }
}
