package com.bassolicodes.commands;

import com.bassolicodes.configuration.MensagensValue;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGamemode {

    @Command(
            name = "gamemode",
            usage = "gamemode <1,2,3> <player>",
            aliases = "gm",
            permission = "core.gm",
            target = CommandTarget.PLAYER
    )

    public void handleGamemode(Context<CommandSender> context, Integer mode, @Optional Player target) {

        val player = (Player) context.getSender();
        GameMode gameMode = GameMode.getByValue(mode);

        if (gameMode == null) {
            player.sendMessage(MensagensValue.get(MensagensValue::gamemode_invalid));
        } else if (player == target) {
            player.sendMessage(MensagensValue.get(MensagensValue::gamemode_same_player));
        } if (!player.hasPermission("core.gm.admin")) {
            player.sendMessage("Você não pode alterar o modo de jogo de outros jogadores.");
        } else if (target == null) {
            player.setGameMode(gameMode);
            player.sendMessage(String.format(MensagensValue.get(MensagensValue::gamemode_change), gameMode.name()));
        } else {
            target.setGameMode(gameMode);
            target.sendMessage(String.format(MensagensValue.get(MensagensValue::gamemode_change_player), target.getName(), gameMode.name()));
        }
    }
}
