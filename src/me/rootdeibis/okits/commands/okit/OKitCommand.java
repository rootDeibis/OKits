package me.rootdeibis.okits.commands.okit;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.rootdeibis.mirandalib.managers.commands.MirandaCommand;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.configurations.MessageUtils;
import me.rootdeibis.okits.kits.KitManager;
import me.rootdeibis.okits.kits.editor.menus.PrincipalMenu;

public class OKitCommand extends MirandaCommand {

    public OKitCommand() {
        super("okits", "Kit Manager for Okits", "ok");
    }

    @Override
    public boolean handle(CommandSender sender, MirandaCommand cmd, String label, String[] args) {
        boolean isPlayer = sender instanceof Player;
        Player player = (Player) sender;

        String subcmd = args.length >= 1 ? args[0] : "";
        
        
        if(sender.hasPermission("okits.command.manager")) {

           if(isPlayer) {
                if(subcmd.equalsIgnoreCase("create")) {

                    if(args.length == 2) {
                        String kitName = args[1];

                        if(!KitManager.getKits().has(kit -> kit.getName().equalsIgnoreCase(kitName))) {


                            PrincipalMenu creatorMenu = new PrincipalMenu(kitName);

                            creatorMenu.open(player);

                        } else {
                            MessageUtils.sendTo(sender, Config.getAlreadyExistMessage(), Config.ConfigPlacelholders, kitName);
                        }

                    } else {

                        MessageUtils.sendTo(sender, "%s &cUsage /okits create <kit-name>", Config.getMessagesPrefix());
                    }
                } else if(subcmd.equalsIgnoreCase("edit")) {
                    if(args.length == 2) {
                        String kitName = args[1];

                        if(KitManager.getKits().has(kit -> kit.getName().equalsIgnoreCase(kitName))) {


                            PrincipalMenu editorMenu = new PrincipalMenu(kitName);

                            editorMenu.open(player);

                        } else {
                            MessageUtils.sendTo(sender, Config.getDoesNotExistMessage(), Config.ConfigPlacelholders, kitName);
                        }

                    } else {

                        MessageUtils.sendTo(sender, "%s &cUsage /okits edit <kit-name>", Config.getMessagesPrefix());
                    }
                } else if(subcmd.equalsIgnoreCase("give")) {
                    if(args.length == 3) {
                        String playerName = args[1];
                        String kitName = args[2];

                        Player givePlayer = Bukkit.getPlayer(playerName);

                        if(givePlayer == null) {
                            MessageUtils.sendTo(sender, "%s &cThe player %s is not online.", Config.getMessagesPrefix(), playerName);

                            return false;
                        } 

                        if(KitManager.getKits().has(kit -> kit.getName().equalsIgnoreCase(kitName))) {
                           
                            KitManager.getKits()
                            .find(kit -> kit.getName().equalsIgnoreCase(kitName))
                            .give(givePlayer, true);


                        } else {
                            MessageUtils.sendTo(sender, Config.getDoesNotExistMessage(), Config.ConfigPlacelholders, kitName);
                        }

                    } else {

                        MessageUtils.sendTo(sender, "%s &cUsage /okits give <player> <kit-name>", Config.getMessagesPrefix());
                    }
                }
           } else {
               MessageUtils.sendTo(sender, "%s &cThe console cannot use this command", Config.getMessagesPrefix());
           }



        } else {
            MessageUtils.sendTo(sender, "%s &cInsuficent permissions", Config.getMessagesPrefix());
        }



        return false;
    }
    
}
