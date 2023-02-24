package me.rootdeibis.okits.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.rootdeibis.mirandalib.managers.commands.MirandaCommand;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.configurations.MessageUtils;
import me.rootdeibis.okits.kits.Kit;
import me.rootdeibis.okits.kits.KitManager;

public class OKDCommand extends MirandaCommand{

    public OKDCommand() {
        super("okitsadmin", "OKits Manager", "okd");
    }

    @Override
    public boolean handle(CommandSender sender, MirandaCommand command, String label, String[] args) {
        

        if(sender instanceof ConsoleCommandSender) {

           MessageUtils.sendTo(sender, "%s &conly player can use this command", Config.getMessagesPrefix());

        } else {
            Player player = (Player) sender;

            if(args.length >= 1) {
                String subcmd = args[0];
                
                if(subcmd.equalsIgnoreCase("create")) {

                    if(args.length >= 2) {
                        String kitName = args[1];

                       
                        if(!KitManager.getKits().has(kit -> kit.getName().equalsIgnoreCase(kitName))) {

                            Kit kit = KitManager.createFromInv(kitName, player.getInventory());


                            kit.save();

                            MessageUtils.sendTo(sender, "%s &aKit %s created", Config.getMessagesPrefix(), kitName);
                            

                        } else {
                            MessageUtils.sendTo(sender, "%s &cKit already exists", Config.getMessagesPrefix());
                        }

                        

 
                    } else {

                        MessageUtils.sendTo(sender, "%s &cCan use /okd create <name> <optional-description>", Config.getMessagesPrefix());

                    }


                } else if(subcmd.equalsIgnoreCase("give")) {
                    
                    if(args.length == 2) {
                        String kitName = args[1];


                        if(KitManager.getKits().has(kit -> kit.getName().equalsIgnoreCase(kitName))) {

                            Kit kit = KitManager.getKits().find(k -> k.getName().equalsIgnoreCase(kitName));

                            

                            kit.give(player);



                        } else {
                            MessageUtils.sendTo(sender, "%s &cKit not found", Config.getMessagesPrefix());

                        }


                    }


                }

                

            }

        }

        return false;
    }
    
}
