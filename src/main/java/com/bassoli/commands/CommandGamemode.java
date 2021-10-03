package com.bassoli.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGamemode {

    @Command(
            name = "gamemode",
            usage = "gamemode <1,2,3> <playerName>",
            aliases = "gm",
            permission = "minister.gm"
    )

    public void handleGamemode(Context<CommandSender> context, Integer mode, @Optional Player target) {
        val player = (Player) context.getSender();
        GameMode gameMode = GameMode.getByValue(mode);
        if (gameMode == null) {
            player.sendMessage("§cModo de jogo inserido inválido.");
            return;
        }
        if (player == null) {
            player.setGameMode(gameMode);
            player.sendMessage("§aModo de jogo alterado para §f" + gameMode.name() + "§e.");
        } else {
            target.setGameMode(gameMode);
            target.sendMessage("§aSeu modo de jogo foi alterado para" + gameMode.name() + "§e.");
        }
    }
}
