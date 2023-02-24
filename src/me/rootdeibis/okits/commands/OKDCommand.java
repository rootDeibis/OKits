package me.rootdeibis.okits.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.rootdeibis.mirandalib.managers.commands.MirandaCommand;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.configurations.MessageUtils;
import me.rootdeibis.okits.kits.KitManager;
import me.rootdeibis.okits.kits.editor.EditorMenu;

public class OKDCommand extends MirandaCommand{

    public OKDCommand() {
        super("okitsadmin", "OKits Manager", "okd");
    }

    @Override
    public boolean handle(CommandSender sender, MirandaCommand command, String label, String[] args) {
        

        if(sender instanceof ConsoleCommandSender) {

           MessageUtils.sendTo(sender, "<prefix> &conly player can use this command", Config.ConfigPlacelholders);

        } else {

            Player player = (Player) sender;
            if(args.length >= 1) {
                String subcmd = args[0];
                
                if(subcmd.equalsIgnoreCase("create")) {

                    if(args.length >= 2) {
                        String kitName = args[1];

                       
                        if(!KitManager.getKits().has(kit -> kit.getName().equalsIgnoreCase(kitName))) {

                            new EditorMenu(kitName).open(player);

                            

                        } else {
                            MessageUtils.sendTo(sender, Config.getAlreadyExistMessage(),Config.ConfigPlacelholders, kitName);
                        }

                        

 
                    } else {

                        MessageUtils.sendTo(sender, "%s &cCan use /okd create <name> <optional-description>", Config.getMessagesPrefix());

                    }

                } else if(subcmd.equalsIgnoreCase("edit")) {

                    if(args.length >= 2)  {

                        String kitName = args[1];


                        if(KitManager.getKits().has(kit -> kit.getName().equalsIgnoreCase(kitName))) {

                            new EditorMenu(kitName, KitManager.getKits().find(kit -> kit.getName().equalsIgnoreCase(kitName)).getItems()).open(player);
                            
                        } else {
                            MessageUtils.sendTo(sender, Config.getDoesNotExistMessage(),Config.ConfigPlacelholders, kitName);
                        }


                    }

                } else if(subcmd.equalsIgnoreCase("remove")) {

                    if(args.length >= 2)  {

                        String kitName = args[1];

                        if(KitManager.getKits().has(kit -> kit.getName().equalsIgnoreCase(kitName))) {


                            KitManager.remove(kitName);

                            MessageUtils.sendTo(sender, Config.getRemovedMessage(),Config.ConfigPlacelholders, kitName);

                        } else {
                            MessageUtils.sendTo(sender, Config.getDoesNotExistMessage(),Config.ConfigPlacelholders, kitName);
                        }

                    }
                   


                }

                

            }

        }

        return false;
    }
    
}
