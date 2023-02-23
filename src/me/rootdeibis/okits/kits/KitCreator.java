package me.rootdeibis.okits.kits;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.rootdeibis.mirandalib.utils.Cache;
import me.rootdeibis.mirandalib.utils.Logger;
import me.rootdeibis.okits.Main;


public class KitCreator {

    public static final Cache<Kit> kits = new Cache<>();


    public static Kit createUsingPlayerInventory(String name, List<String> description, Player player) {
        Kit kit = new Kit(name, description);

        Inventory inv = player.getInventory();


        for(int i = 0; i < inv.getSize(); i++ ) {
            ItemStack itemStack = inv.getItem(i);

            if(itemStack != null) {
                kit.addItem(clone(itemStack));

                Logger.info("Amount: " + itemStack.getAmount());
            }
        }
        
        return kit;
    }


    public static Kit createUsingPlayerInventory(String name, Player player) {
        return createUsingPlayerInventory(name, new ArrayList<String>(), player);
    }
    

    public static void saveKit(Kit kit) {

        File file = new File(Main.getPlugin().getDataFolder(), "kits/" + kit.getName() + ".yml");

        try {
            if(!file.exists()) file.createNewFile();

            YamlConfiguration yamlConfiguration = new YamlConfiguration();

            yamlConfiguration.set("kit", kit);

            yamlConfiguration.save(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    private static ItemStack clone(ItemStack i) {
        return new ItemStack(i);
    }
}
