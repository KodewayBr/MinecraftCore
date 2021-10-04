package com.bassoli.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;

public class CommandSystemStatus {

    @Command(
            name = "status",
            aliases = "tps",
            permission = "seila.seila"
    )

    public void handleSystemStatus(Context<Player> context) {
        val player = (Player) context.getSender();

        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        player.sendMessage(
                new String[]
                        {
                                "",
                                " §lINFORMAÇÕES DO SERVIDOR",
                                "",
                                " §aSistema operacional: §7" + operatingSystemMXBean.getName() + ".",
                                " §aJAR utilizada: §7" + getJarType() + ".",
                                " §aMinecraft Version: §7" + getMinecraftVersion() + ".",
                                " §aTotal de jogadores: §7" + getOnlinePlayers() + "/" + getMaxPlayers(),
                                Bukkit.getServer().getIp() != null ? " §aEndereço de IP: §cIP não encontrado!" : " §aEndereço de IP: §7" + Bukkit.getServer().getIp(),
                                " §aPorta de acesso: §7" + Bukkit.getPort(),
                                ""
                        }
        );

    }


    public static String getJarType() {
        try {

            String info = Bukkit.getVersion();
            return info.split("git-")[1].split("-")[0];

        } catch (Throwable e) {
            return "Versão desconhecida!";
        }
    }

    public static String getMinecraftVersion() {
        try {
            String info = Bukkit.getVersion();
            return info.split("MC: ")[1].split("\\)")[0];
        } catch (Throwable e) {
            return "Nunca vi essa versao na minha vida, reportando para mojang";
        }
    }

    public static Object getMaxPlayers() {
        Object maxPlayers = Bukkit.getServer().getMaxPlayers();
        return maxPlayers;
    }

    public static Object getOnlinePlayers() {
        Object players = Bukkit.getOnlinePlayers().size();
        return players;
    }
}
