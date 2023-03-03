package me.rootdeibis.okits.kits.player;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.rootdeibis.okits.Main;
import me.rootdeibis.okits.kits.Kit;
import me.rootdeibis.okits.kits.KitManager;

public class PlayerKit {

    private final UUID uuid;
    private final HashMap<UUID, Long> kits = new HashMap<>();
    private File file;
    private FileConfiguration config;


    public PlayerKit(UUID uuid) {
        this.uuid = uuid;
        this.file = new File(Main.getPlugin().getDataFolder(), "players/" + this.uuid.toString() + ".yml");
        this.check();
        
    }

    public void check() {
        try {
            if(!this.playerFileExists()) {
             this.getPlayerFile().createNewFile();
            }

            YamlConfiguration config = new YamlConfiguration();

            config.load(this.file);
            this.config = config;
       
            for (Kit kit : KitManager.getKits().all()) {
                if(config.getString(kit.getKitUUID().toString()) == null) {
                    long time = new Date().getTime();
                    kits.put(kit.getKitUUID(), time);

                    config.set(kit.getKitUUID().toString(), time);
                } else {
                    kits.put(kit.getKitUUID(), Long.valueOf(config.getString(kit.getKitUUID().toString())));
                }
            }

            this.save();

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void edit(UUID kitUUID, long value) {
       kits.put(kitUUID, value);
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean playerFileExists() {
        return this.getPlayerFile().exists();
    }

    public boolean available(UUID uuid) {
        return new Date(this.kits.get(uuid)).before(new Date());
    }

    public File getPlayerFile() {
       return this.file;
    }
    
    public FileConfiguration getPlayerConfig() {
        return this.config;
    }

    public UUID getUuid() {
        return uuid;
    }

}
