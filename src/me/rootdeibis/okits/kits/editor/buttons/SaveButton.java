package me.rootdeibis.okits.kits.editor.buttons;


import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.Functions;
import me.rootdeibis.mirandalib.utils.guifactory.GUIButton;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.configurations.MessageUtils;
import me.rootdeibis.okits.kits.Kit;
import me.rootdeibis.okits.kits.editor.menus.PrincipalMenu;

public class SaveButton extends GUIButton {

    private final PrincipalMenu from;

    public SaveButton(PrincipalMenu from) {
        super(52);

        this.from = from;

        this.setMaterial(Material.matchMaterial("WOOL"));
        this.setMaterialData((byte)5);
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


          Kit kit = this.from.getKit();

          kit.getItems().clear();

          for (ItemStack item : this.from.getInventory().getContents()) 
            if(item != null)
                kit.addItem(item);
        
         
          e.getWhoClicked().closeInventory();

          kit.save();

          MessageUtils.sendTo(e.getWhoClicked(), Config.getCreatedMessage(), Config.ConfigPlacelholders, this.from.getKit().getName());
        };
    }


    
}
