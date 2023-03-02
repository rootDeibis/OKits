package me.rootdeibis.okits.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.rootdeibis.okits.kits.player.PlayerKit;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PlayerKit playerKit = new PlayerKit(e.getPlayer().getUniqueId());

    } 
    
}
