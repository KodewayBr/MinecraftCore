package com.bassolicodes.commands;

import lombok.val;
import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class CommandSystemStatus {

    @Command(
            name = "status",
            permission = "core.status",
            target = CommandTarget.PLAYER
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
                                " §aUso de memória RAM: §7" + getRamUsage(),
                                " §aTotal de processadores: §7" + Runtime.getRuntime().availableProcessors(),
                                " §aJAR utilizada: §7" + getJarType() + ".",
                                " §aMinecraft Version: §7" + getMinecraftVersion() + ".",
                                " §aTotal de jogadores: §7" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers(),
                                " §aEndereço de IP: §7" + Bukkit.getServer().getIp().toString(),
                                " §aServidor está em whitelist: §7" + (Bukkit.getServer().hasWhitelist() ? "Sim" : "Não"),
                                " §aPorta de acesso: §7" + Bukkit.getPort(),
                                ""
                        }
        );

    }

    public static long getUsedMemory() {
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L;
    }

    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / 1048576L;
    }

    public static String getRamUsage() {
        float percent = (float) getUsedMemory() / (float) getMaxMemory() * 100.0F;
        return "§8[§7" + getUsedMemory() + "MB/" + getMaxMemory() + "MB§8] §a(" + Math.floor(percent) + "%)";
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
            return "Versão não encontrada!";
        }
    }
}
