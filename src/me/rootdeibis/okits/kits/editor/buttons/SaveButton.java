package me.rootdeibis.okits.kits.editor.buttons;

import org.bukkit.event.inventory.InventoryClickEvent;

import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.Functions;
import me.rootdeibis.mirandalib.utils.guifactory.GUIButton;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.kits.KitManager;
import me.rootdeibis.okits.kits.editor.menus.PrincipalMenu;

public class SaveButton extends GUIButton {

    private final PrincipalMenu from;

    public SaveButton(PrincipalMenu from) {
        super(52);

        this.from = from;

        this.setDisplayName();
        this.setLore();
        
        this.onClick(this.onclick());

        

    }

    private void setDisplayName() {

        this.setDisplayName(ColorUtils.parse(Config.getEditorSaveBtnDisplayName()));

    }

    private void setLore() {
        this.setLore(ColorUtils.parse(Config.getEditorSaveBtnLore()));
    }

    private Functions.Function<InventoryClickEvent> onclick() {
        return (e) -> {

            this.from.removeButtons();

            KitManager.createFromInv(this.from.getKitName(), this.from.getInventory());

        };
    }


    
}
