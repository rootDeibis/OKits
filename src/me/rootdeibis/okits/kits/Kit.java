package me.rootdeibis.okits.kits;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.rootdeibis.mirandalib.utils.Logger;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.configurations.MessageUtils;
import me.rootdeibis.okits.kits.player.PlayerKit;
import me.rootdeibis.okits.kits.player.PlayerKitManager;
import me.rootdeibis.okits.kits.serializer.KitSerializer;


public class Kit {

    private final String name;
    private List<String> description = new ArrayList<>();

    private final List<ItemStack> items = new ArrayList<ItemStack>();

    private UUID kitUUID;

    private int[] frozenTimes = {};


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

        kitUUID = UUID.randomUUID();
    }


    public void addItem(ItemStack item) {
        this.items.add(item);
    }

    public void remove(ItemStack itemStack) {
        this.items.remove(itemStack);
    }


    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }


    public void setMonths(int months) {
        frozenTimes[0] = months;
    }

    public void setDays(int days) {
        frozenTimes[1] = days;
    }

    public void setHours(int hours) {
        frozenTimes[2] = hours;
    }
    
    public void setMinutes(int minutes) {
        frozenTimes[3] = minutes;
    }

    
    public void setSeconds(int seconds) {
        frozenTimes[4] = seconds;
    }

    public int getMonths() {
        return frozenTimes[0];
    }

    public int getDays() {
        return frozenTimes[1];
    }

    public int getHours() {
        return frozenTimes[2];
    }

    public int getMinutes() {
        return frozenTimes[3];
    }

    public int getSeconds() {
        return frozenTimes[4];
    }

    public int[] getFrozenTimes() {
        return frozenTimes;
    }

    public boolean hasFrozenTime() {
        return frozenTimes.length > 0;
    }


    public List<ItemStack> getItems() {
        return items;
    }


    public void save() {

        KitSerializer.serialize(this);
        KitManager.getKits().add(this);
        
        PlayerKitManager.reload();
        
    }

    public void give(Player player) {
        PlayerKit pk = PlayerKitManager.getPlayers().find(p -> p.getUuid() == player.getUniqueId());

        if(!pk.available(this.getKitUUID())) {

            MessageUtils.sendTo(player, Config.getFrozenMessage(), Config.ConfigPlacelholders, this.name);

            return;
        }


        Inventory playerInv = player.getInventory();

        for(ItemStack item : items){

            if(isAvailableSlots(playerInv)) {
                playerInv.addItem(item);
            } else {
                player.getLocation().getWorld().dropItem(player.getLocation(), item);
            }
        }

    

        MessageUtils.sendTo(player, Config.getRecievedMessage(), Config.ConfigPlacelholders, this.name);

        if(this.hasFrozenTime()) {
           
            pk.edit(this.getKitUUID(), TimeTool.addToDate(this.getFrozenTimes()).getTime());

            pk.save();
        }

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
