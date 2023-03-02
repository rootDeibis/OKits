package me.rootdeibis.okits.kits;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.rootdeibis.mirandalib.utils.Cache;
import me.rootdeibis.mirandalib.utils.Logger;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.configurations.MessageUtils;
import me.rootdeibis.okits.kits.serializer.KitSerializer;


public class Kit {

    private final String name;
    private List<String> description = new ArrayList<>();
    private String frozen_time_format = "1h";

    private final Cache<ItemStack> items = new Cache<>();

    private UUID kitUUID;

    public Kit(String kitName, List<String> kitDescription) {
        this.name = kitName;
        this.description = kitDescription;

        kitUUID = UUID.randomUUID();
    }

    public Kit(String kitName, List<String> kitDescription, String uuid) {
        this.name = kitName;
        this.description = kitDescription;

        kitUUID = UUID.fromString(uuid);
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


    public void setFrozenTimeFormat(String format) {
        frozen_time_format = format;
    }

    public String getFrozenTimeFormat() {
        return frozen_time_format;
    }


    public Cache<ItemStack> getItems() {
        return items;
    }


    public void save() {

        KitSerializer.serialize(this);
        KitManager.getKits().add(this);
        
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

    public UUID getKitUUID() {
        return kitUUID;
    }
    
}
