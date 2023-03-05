package me.rootdeibis.okits.kits.editor.buttons.frozen;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.Functions;
import me.rootdeibis.mirandalib.utils.guifactory.GUIButton;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.kits.editor.menus.FreezeMenu;

public class BackButton extends GUIButton {

    private final FreezeMenu current;

    public BackButton(FreezeMenu current) {
        super(0);
        this.current = current; 

        this.setMaterial(Material.matchMaterial("ARROW"));
        this.setDisplayName(ColorUtils.parse(Config.getButtonDisplayNameFrozen("back")));
        this.setLore(ColorUtils.parse(Config.getButtonLoreFrozen("back")));

        this.onClick(this.onclick());
    }

    private Functions.Function<InventoryClickEvent> onclick() {
        return (e) -> {

            this.current.getFrom().open((Player) e.getWhoClicked());
        };
    }
    
}
