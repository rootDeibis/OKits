package me.rootdeibis.okits.configurations;

import me.rootdeibis.mirandalib.managers.configuration.IFile;
import me.rootdeibis.mirandalib.utils.PlaceholderFormat;
import me.rootdeibis.okits.Main;

public class Config {

    public static PlaceholderFormat ConfigPlacelholders = new PlaceholderFormat();

    public static IFile get() {
        return Main.getFileManager().Import("config.yml");
    }


    public static boolean isEnabledSounds() {
        return get().getBoolean("settings.enable-sounds");
    }
    
    public static String getMessagesPrefix() {
        return get().getString("messages.prefix");
    }

    public static String getRecievedMessage() {
        return transformToString("messages.recieved.message");
    }

    public static String getRecievedSound() {
        return get().getString("messages.recieved.sound");
    }

    private static String transformToString(String path) {
        return get().isList(path) ? String.join("\n", get().getStringList(path)) : get().getString(path);
    }
}
