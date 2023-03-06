package me.rootdeibis.okits.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.rootdeibis.mirandalib.managers.commands.MirandaCommand;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.configurations.MessageUtils;
import me.rootdeibis.okits.kits.KitManager;
import me.rootdeibis.okits.kits.player.PlayerKit;
import me.rootdeibis.okits.kits.player.PlayerKitManager;

public class KitsCommand extends MirandaCommand{

    public KitsCommand() {
        super("kit", "Kits Command", "kits");
    }

    @Override
    public boolean handle(CommandSender sender, MirandaCommand command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("kits")) {

            StringBuilder message = new StringBuilder("&aKits: &r");


            KitManager.getKits().all().forEach(kit -> {

                PlayerKit pk =  PlayerKitManager.getPlayers().find(p -> p.getUuid() == player.getUniqueId());

                if(pk.available(kit.getKitUUID()) && player.hasPermission(kit.getPermission())) {

                    message.append("&a" + kit.getName() + ",");

                } else {
                    message.append("&c" + kit.getName() + ",");

                }

            }); 

            MessageUtils.sendTo(sender, message.toString());

        } else {
            if(args.length == 1) {
                String kitName = args[0];

        
                        if(KitManager.getKits().has(kit -> kit.getName().equalsIgnoreCase(kitName))) {
                           
                            KitManager.getKits()
                            .find(kit -> kit.getName().equalsIgnoreCase(kitName))
                            .give(player);


                        } else {
                            MessageUtils.sendTo(sender, Config.getDoesNotExistMessage(), Config.ConfigPlacelholders, kitName);
                        }
            }
        }


       return false;
    }
    
}
