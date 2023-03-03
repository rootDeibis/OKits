package me.rootdeibis.okits.kits.player;

import java.util.UUID;

import org.bukkit.Bukkit;

import me.rootdeibis.mirandalib.utils.Cache;

public class PlayerKitManager {
    

    private static Cache<PlayerKit> players = new Cache<>();


    public static void loadIfNotExists(UUID player) {
        if(!players.has(p -> p.getUuid() == player)) {
            players.add(new PlayerKit(player));
        }
    }


    public static Cache<PlayerKit> getPlayers() {
        return players;
    }

    public static void reload() {
        players = new Cache<>();

        Bukkit.getOnlinePlayers().forEach(pl -> loadIfNotExists(pl.getUniqueId()));

    }
}
