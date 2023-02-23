package me.rootdeibis.okits;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.rootdeibis.mirandalib.managers.commands.CommandManager;
import me.rootdeibis.mirandalib.managers.configuration.FileManager;
import me.rootdeibis.mirandalib.utils.Logger;
import me.rootdeibis.okits.commands.OKitsComamnds;
import me.rootdeibis.okits.configurations.Config;

public class Main extends JavaPlugin {

    private static Plugin plugin;
    private static FileManager fileManager;

    private final String FILE_MANAGER_RESOURCES_PATH = "me/rootdeibis/okits/resources/";

    @Override
    public void onLoad() {
        plugin = this;

        fileManager = new FileManager(this, this.FILE_MANAGER_RESOURCES_PATH);
        fileManager.Export("config.yml");

        fileManager.dir("kits");
        

        CommandManager.register(new OKitsComamnds());


        Config.ConfigPlacelholders.register("prefix", Config.getMessagesPrefix());

        
       
    }

    @Override
    public void onEnable() {        
        Logger.info("&a[OKits] v%s by &dgithub.com/rootDeibis", this.getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        
    }


    public static Plugin getPlugin() {
        return plugin;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
    

    
}
