package me.rootdeibis.okits;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.rootdeibis.mirandalib.managers.commands.CommandManager;
import me.rootdeibis.mirandalib.managers.configuration.FileManager;
import me.rootdeibis.mirandalib.utils.Logger;
import me.rootdeibis.mirandalib.utils.guifactory.GUIListenerEvent;
import me.rootdeibis.okits.commands.OKDCommand;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.kits.KitManager;
import me.rootdeibis.okits.listeners.PlayerJoinListener;

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
        fileManager.dir("players");
        

        CommandManager.register(new OKDCommand());


        
    }

    @Override
    public void onEnable() {        
        Bukkit.getPluginManager().registerEvents(new GUIListenerEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);

        
        Logger.info("&a[OKits] v%s by &dgithub.com/rootDeibis", this.getDescription().getVersion());
        Config.ConfigPlacelholders.register("prefix", Config.getMessagesPrefix());
        KitManager.loadKitsFromDirectory();

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
