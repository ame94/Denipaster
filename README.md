# Denipaster
Pastebin powered Denizen script management Spigot plugin for Minecraft.

###About:

This plugin lets you fetch pastes you make on your pastebin.com account, and writes them to files inside of plugins/Denizen/scripts

After running the plugin for the first time, the default configuration file will be saved under `Denipaster/config.yml`.

Inside you define `APIKey` and `USERKey`. This can be obtained when creating a Pastebin account and clicking on the API menu.

###Commands:

* `/denipaster ldir` shows the files in your Denizen/scripts folder
* `/denipaster rdir` shows a list of pastes you have on your pastebin account
* `/denipaster fetch <pasteid> <scriptname.yml>` downloads pasteid and saves it to scriptname.yml
* `/denipaster delete <scriptname.yml>` deletes a local script file 

###Permissions:

* `denipaster.admin` Root permission node needed for any command.
* `denipaster.list` Allows `ldir` and `rdir`
* `denipaster.fetch` Allows `fetch`
* `denipaster.delete` Allows `delete`
