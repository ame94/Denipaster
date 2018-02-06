package ca.ame94.denipaster;

import ca.ame94.denipaster.denizen.FileSystem;
import ca.ame94.denipaster.pastebin.Contents;
import ca.ame94.denipaster.util.Config;
import ca.ame94.denipaster.util.Logger;
import ca.ame94.denipaster.util.PluginMgr;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

import static ca.ame94.denipaster.denizen.FileSystem.getScriptFolderContents;

public class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginMgr.Init(this);
        Config.Init();
    }

    @Override
    public void onDisable() {

    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        int numArgs = args.length;
        String subCommand;

        switch (numArgs) {
            case 0:
                break;

            case 1:
                subCommand = args[0];
                // Command usage
                if (subCommand.equalsIgnoreCase("help")) {
                    sender.sendMessage("Usage:");
                    sender.sendMessage("  /denipaster ldir  Gets local Denizen script filenames");
                    sender.sendMessage("  /denipaster rdir  Lists remote Pastebin entries");
                    sender.sendMessage("  /denipaster fetch <PasteID> <scriptname.yml>  Writes paste to script file.");
                    return true;
                }

                if (subCommand.equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("denipaster.reload")) {
                        sender.sendMessage("Reloading configuration...");
                        Logger.Info("Reloading configuration...");
                        Config.Reload();
                    } else {
                        sender.sendMessage("You do not have permission for that!");
                    }
                    return true;
                }


                // Local script folder listing
                if (subCommand.equalsIgnoreCase("ldir")) {
                    if (sender.hasPermission("denipaster.list")) {
                        sender.sendMessage("Getting script list:");

                        ArrayList<String> list = getScriptFolderContents();
                        if (list != null) {
                            for (String entry : list) {
                                sender.sendMessage("  " + entry);
                            }
                        } else {
                            sender.sendMessage("Could not get local directory information!");
                        }
                    } else {
                        sender.sendMessage("You do not have permission for that!");
                    }
                    return true;
                }

                // Remote Pastebin listing
                if (subCommand.equalsIgnoreCase("rdir")) {
                    if (sender.hasPermission("denipaster.list")) {
                        sender.sendMessage("Getting remote script list:");
                        ArrayList<String> remoteDirectory = Contents.getPastebinDirectory();
                        if (remoteDirectory != null) {
                            for (String entry : remoteDirectory) {
                                sender.sendMessage(entry);
                            }
                        }
                    } else {
                        sender.sendMessage("You do not have permission for that!");
                    }
                    return true;
                }

            case 2:
                // Delete local script file
                subCommand = args[0];
                if (subCommand.equalsIgnoreCase("delete")) {
                    if (sender.hasPermission("denipaster.delete")) {
                        String filename = args[1];
                        FileSystem.delScriptFile(filename);
                    } else {
                        sender.sendMessage("You do not have permission for that!");
                    }
                    return true;
                }

            case 3:
                // Fetch paste and write file
                subCommand = args[0];
                if (subCommand.equalsIgnoreCase("fetch")) {
                    if (sender.hasPermission("denipaster.fetch")) {
                        String pasteID = args[1];
                        String filename = args[2];

                        sender.sendMessage("Fetching paste " + pasteID + " to " + filename);
                        FileSystem.fetchPasteToFile(pasteID, filename);
                    } else {
                        sender.sendMessage("You do not have permission for that!");
                    }
                    return true;
                }
        }
        return false;
    }
}
