package me.rootdeibis.okits.kits;


import java.io.File;
import java.util.stream.Collectors;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.rootdeibis.mirandalib.managers.configuration.IFile;
import me.rootdeibis.mirandalib.utils.Cache;
import me.rootdeibis.mirandalib.utils.Logger;
import me.rootdeibis.okits.Main;
import me.rootdeibis.okits.kits.serializer.KitSerializer;


public class KitManager {

    private static final Cache<Kit> kits = new Cache<>();

    

    public static Cache<Kit> getKits() {
        return kits;
    }


    public static Kit createFromInv(String name, Inventory inv) {
        Kit kit = new Kit(name, "No description available");

        for (ItemStack item : inv.getContents()) {
            if(item == null) continue;

            kit.addItem(item);
        }

        return kit;
    }

    public static void remove(String name) {
        File file = new File(Main.getPlugin().getDataFolder(), "kits/" + name + ".yml");

        if(file.exists()) {
            file.delete();
        }


        kits.remove(kit -> kit.getName().equalsIgnoreCase(name));
    }

    public static void loadKitsFromDirectory() {

        Cache<IFile> files = Main.getFileManager().dir("kits").getFiles();

        Logger.info("&eOKits &7> &aKits: " + String.join("&f, &a", files.all().stream().map(f -> f.getString("name")).collect(Collectors.toList())));

        for (IFile kitConfig : files.all()) {
            Kit kit = KitSerializer.deserealize(kitConfig);

            kits.add(kit);
        }
    }

    

}
