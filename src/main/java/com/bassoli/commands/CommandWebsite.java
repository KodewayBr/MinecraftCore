package com.bassoli.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.entity.Player;

public class CommandWebsite {

    @Command(
            name = "site",
            aliases = {"website", "web"}
    )

    public void handleWebsite(Context<Player> context) {
        val player = (Player) context.getSender();

        if (player == null) {
            return;
        }

        player.sendMessage(
                new String[]
                        {
                                "",
                                "§eAcesse nosso site e adquira suas vantagens:",
                                "§f→ www.website.com",
                                ""
                        }
        );
    }
}
