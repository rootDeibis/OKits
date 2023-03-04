package me.rootdeibis.okits.kits.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import me.rootdeibis.okits.Main;
import me.rootdeibis.okits.kits.Kit;

public class KitSerializer {

    public static void serialize(Kit kit) {
        File file = new File(Main.getPlugin().getDataFolder(), "kits/" + kit.getName() + ".yml");

        try {

            if(!file.exists()) file.createNewFile();

            YamlConfiguration config = new YamlConfiguration();


            config.set("uuid", kit.getKitUUID().toString());
            
            config.set("name", kit.getName());
            config.set("description", kit.getDescription());

            config.set("encoded-items", encodeItems(kit));

            if(kit.hasFrozenTime()) {
                
            }


            config.save(file);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Kit deserealize(FileConfiguration config) {
        Kit kit = new Kit(config.getString("name"), config.getStringList("description"), config.getString("uuid"));


        for (ItemStack decodedItem : decodeItems(config.getStringList("encoded-items"))) {
            kit.addItem(decodedItem);
        }

        return kit;
    }
    

    public static List<String> encodeItems(Kit kit) {
        List<String> encodeds = new ArrayList<>();

        for(ItemStack item : kit.getItems()) {

            try {
                ByteArrayOutputStream io = new ByteArrayOutputStream();
                BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
                os.writeObject(item);
                os.flush();
    
    
                byte[] serializedObject = io.toByteArray();
    
                String encodedObject = new String(Base64.getEncoder().encode(serializedObject));
    
                
                encodeds.add(encodedObject);

            } catch (Exception e) {
               e.printStackTrace();
            }

        }


        return encodeds;

    }


    public static List<ItemStack> decodeItems(List<String> encodedsData) {
        List<ItemStack> encodeds = new ArrayList<>();


        for (String data : encodedsData) {
            try {
                ByteArrayInputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(data));
    
                BukkitObjectInputStream is = new BukkitObjectInputStream(in);
 
                encodeds.add( (ItemStack) is.readObject());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return encodeds;
    }


}
