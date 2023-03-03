package me.rootdeibis.okits.kits.editor.buttons;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.Functions;
import me.rootdeibis.mirandalib.utils.guifactory.GUIButton;
import me.rootdeibis.okits.configurations.Config;

public class CancelButton extends GUIButton {

    public CancelButton() {
        super(53);

        this.setMaterial(Material.matchMaterial("BARRIER"));
        this.setDisplayName();
        this.setLore();
        
        this.onClick(this.onclick());
    }


    private void setDisplayName() {
        this.setDisplayName(ColorUtils.parse(Config.getEditorCancelBtnDisplayName()));
    }

    private void setLore() {
        this.setLore(ColorUtils.parse(Config.getEditorCancelBtnLore()));
    }

    private Functions.Function<InventoryClickEvent> onclick() {
        return (e) -> {

            e.getWhoClicked().closeInventory();
        };
    }

}
