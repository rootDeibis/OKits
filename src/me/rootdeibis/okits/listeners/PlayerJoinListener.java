package me.rootdeibis.okits.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.rootdeibis.okits.kits.player.PlayerKitManager;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PlayerKitManager.loadIfNotExists(e.getPlayer().getUniqueId());

    } 
    
}
