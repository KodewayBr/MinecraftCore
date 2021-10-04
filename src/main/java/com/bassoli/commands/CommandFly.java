package com.bassoli.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly {
    @Command(
            name = "fly",
            permission = "core.fly"
    )
    public void handleFly(Context<CommandSender> context, @Optional Player target) {
        val player = (Player) context.getSender();

        if(player == target) {
            player.sendMessage("§cVocê podera habilitar o voô de sí mesmo.");
            return;
        }
        if(target == null) {
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
                player.sendMessage("§aModo de voô habilitado.");
            } else {
                player.setAllowFlight(false);
                player.sendMessage("§cModo de voô desabilitado.");
            }
        } else {
            if(!target.getAllowFlight()) {
                player.setAllowFlight(true);
                player.sendMessage("§aModo de voô habilitado de §f" + target.getName() + "§a.");
                target.sendMessage("§aSeu modo de voô foi habilitado por §f" + player.getName() + "§a.");
            } else {
                player.setAllowFlight(false);
                player.sendMessage("§cModo de voô desabilitado de §f" + target.getName() + "§c.");
                target.sendMessage("§cSeu modo de voô foi desabilitado por §f" + player.getName() + "§a.");
            }
        }
    }
}
