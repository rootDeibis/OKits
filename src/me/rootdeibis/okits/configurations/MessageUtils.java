package me.rootdeibis.okits.configurations;

import org.bukkit.command.CommandSender;

import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.PlaceholderFormat;

@SuppressWarnings("all")
public class MessageUtils {


    public static void sendTo(CommandSender sender, String message, String... params) {
        sender.sendMessage(ColorUtils.parse(PlaceholderFormat.parseParams(message, params)));
    }

    public static void sendTo(CommandSender sender, String message, PlaceholderFormat placeholders) {
        sender.sendMessage(ColorUtils.parse(placeholders.parse(message)));
    }

    public static void sendTo(CommandSender sender, String message, PlaceholderFormat placeholders, String... params) {
       sendTo(sender, PlaceholderFormat.parseParams(message, params), placeholders);
    }
    
}
