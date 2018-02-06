package ca.ame94.denipaster.util;

import org.bukkit.plugin.java.JavaPlugin;

public class Config {

    private static String APIKey = null;
    private static String UserKey = null;

    public static void Init() {
        JavaPlugin p = PluginMgr.getPlugin();

        if (!p.getDataFolder().exists()) {
            Logger.Info("Creating default configuration...");
            p.getConfig().options().copyDefaults(true);
            p.saveConfig();
        }

        getAPIKey();
        getUserKey();
    }

    public static void Reload() {
        PluginMgr.getPlugin().reloadConfig();

        APIKey = null;
        UserKey = null;
        getAPIKey();
        getUserKey();
    }

    public static String getUserKey() {
        String key = null;

        // UserKey hasn't been loaded from the config yet.
        if (UserKey == null) {
            key = PluginMgr.getPlugin().getConfig().getString("USERKey");

            if (key.length() == 32) {
                UserKey = key;
                Logger.Info("User Key loaded.");
            } else {
                if (key.equalsIgnoreCase("Your Pastebin USER Key goes in here.")) {
                    Logger.Warning("ERROR: Pastebin user key required!");
                    Logger.Info("Create a Pastebin account (they're free) then goto the API tab and get your Developer API key");
                    Logger.Info("Next, generate your user key: https://pastebin.com/api/api_user_key.html");
                } else {
                    Logger.Warning("ERROR: Config does not appear to contain a 32-character user key!");
                }
                key = null;
            }
        } else {
            // Key is loaded into memory, return it.
            key = UserKey;
        }

        return key;
    }


    public static String getAPIKey() {
        String key = null;

        // APIKey hasn't been loaded from the config yet.
        if (APIKey == null) {
            key = PluginMgr.getPlugin().getConfig().getString("APIKey");

            if (key.length() == 32) {
                APIKey = key;
                Logger.Info("API Key loaded.");
            } else {
                if (key.equalsIgnoreCase("Your Pastebin API Key goes in here.")) {
                    Logger.Warning("ERROR: Pastebin API key required!");
                    Logger.Info("Create a Pastebin account (they're free) then goto the API tab and get your Developer API key");
                    Logger.Info("Next, generate your user key: https://pastebin.com/api/api_user_key.html");
                } else {
                    Logger.Warning("ERROR: Config does not appear to contain a 32-character API key!");
                }
                key = null;
            }
        } else {
            // Key is loaded into memory, return it.
            key = APIKey;
        }

        return key;
    }
}
