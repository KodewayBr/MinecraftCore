package com.bassoli.commands;


import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.entity.Player;

public class CommandKickAll {

    @Command(
            name = "kickall",
            permission = "minister.kickall"
    )

    public void handleKickAll(Context<Player> context) {

        val player = context.getSender();

    }

}
