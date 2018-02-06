package ca.ame94.denipaster.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class Logger {

    public static void Info(String msg) {
        Bukkit.getLogger().log(Level.INFO, "[Denipaster] " + msg);
    }

    public static void Warning(String msg) {
        Bukkit.getLogger().log(Level.WARNING, "[Denipaster] " + msg);
    }

    public static void BroadcastAndLog(String msg) {
        PluginMgr.getPlugin().getServer().broadcastMessage(msg);
        Info(msg);
    }

}
