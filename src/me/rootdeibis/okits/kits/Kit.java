package me.rootdeibis.okits.kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.rootdeibis.mirandalib.utils.Cache;
import me.rootdeibis.mirandalib.utils.Logger;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.configurations.MessageUtils;


public class Kit {

    private final String name;
    private List<String> description = new ArrayList<>();

    private final Cache<ItemStack> items = new Cache<>();

    public Kit(String kitName, List<String> kitDescription) {
        this.name = kitName;
        this.description = kitDescription;
    }


    public Kit(String kitName, String description) {
        this.name = kitName;
        this.description.add(description);
    }


    public void addItem(ItemStack item) {
        items.add(item);
    }

    public void remove(ItemStack itemStack) {
        this.items.remove(i -> i == itemStack);
    }


    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }


    public Cache<ItemStack> getItems() {
        return items;
    }


    public void save() {
        
    }

    public void give(Player player) {

        Inventory playerInv = player.getInventory();



        for(ItemStack item : items.all()){

            if(isAvailableSlots(playerInv)) {
                playerInv.addItem(item);
            } else {
                player.getLocation().getWorld().dropItem(player.getLocation(), item);
            }

        }

        MessageUtils.sendTo(player, Config.getRecievedMessage(), Config.ConfigPlacelholders, this.name);

        try {
            Sound sound = Sound.valueOf(Config.getRecievedSound());

            player.playSound(player.getLocation(), sound, 1, 0);
        } catch (Exception e) {
            
            Logger.info("&4OKits Error -> Sound %s no't exists, check the name.", Config.getRecievedSound());
        }

    }


    private boolean isAvailableSlots(Inventory inv) {
        int availableSlots = inv.getSize();

        for (ItemStack contents : inv.getContents()) {
            if(contents != null) {
                --availableSlots;
            }
        }


        return availableSlots > 0;
    }
    
}
