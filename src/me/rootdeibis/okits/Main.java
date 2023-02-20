package me.rootdeibis.okits;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.rootdeibis.mirandalib.managers.configuration.FileManager;
import me.rootdeibis.mirandalib.utils.Logger;
import me.rootdeibis.mirandalib.utils.PlaceholderFormat;

public class Main extends JavaPlugin {

    private static Plugin plugin;
    private static FileManager fileManager;

    private final String FILE_MANAGER_RESOURCES_PATH = "me/rootdeibis/okits/resources/";
    private static Logger logger;

    @Override
    public void onLoad() {
        plugin = this;

        logger = new Logger();
        fileManager = new FileManager(this, this.FILE_MANAGER_RESOURCES_PATH);

        fileManager.Export("config.yml");
        
       
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(PlaceholderFormat.parseParams("&aOKits %s", this.getDescription().getVersion()));
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
    
    public static Logger getOLogger() {
        return logger;
    }


    
}
