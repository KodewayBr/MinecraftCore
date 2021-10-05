package com.bassolicodes.commands;

import com.bassolicodes.MinecraftCore;
import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandGamemode {

    FileConfiguration config = MinecraftCore.getInstance().getConfig();

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
            player.sendMessage(config.getString("Message.Gamemode_Invalid").replace("&", "§"));
            return;
        }

        if (player == target) {
            player.sendMessage(config.getString("Message.Gamemode_Same_Player").replace("&", "§"));
            return;
        }

        if (target == null) {
            player.setGameMode(gameMode);
            player.sendMessage(String.format(config.getString("Message.Gamemode_Change").replace("&", "§"), gameMode.name()));
        } else {
            target.setGameMode(gameMode);
            target.sendMessage(String.format(config.getString("Message.Gamemode_Change_Player").replace("&", "§"), target.getName(), gameMode.name()));
            //target.sendMessage("§aO modo de jogo de "+ target.getName() +" foi alterado para " + gameMode.name() + "§2.");
        }
    }
}
