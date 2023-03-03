package me.rootdeibis.okits.kits.editor.menus;

import org.bukkit.inventory.ItemStack;

import me.rootdeibis.mirandalib.utils.Cache;
import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.PlaceholderFormat;
import me.rootdeibis.mirandalib.utils.guifactory.GUIMenu;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.kits.editor.buttons.CancelButton;
import me.rootdeibis.okits.kits.editor.buttons.SaveButton;

public class PrincipalMenu extends GUIMenu {

    private final String kitName;

    private Cache<ItemStack> items = new Cache<>();

    public PrincipalMenu(String kitName) {
        super(PrincipalMenu.getFormatedTitle(kitName), 6);

        this.setEditable(true);

        this.kitName = kitName;

        this.loadButtons();
        
    }

    public PrincipalMenu(String kitName, Cache<ItemStack> items) {
        super(PrincipalMenu.getFormatedTitle(kitName), 6);

        this.setEditable(true);

        this.kitName = kitName;
        this.items = items;

        items.all().forEach(item -> this.getInventory().addItem(item));


        this.loadButtons();
        
    }


    private void loadButtons() {
        this.addButton(new SaveButton(this));
        this.addButton(new CancelButton());
    }

    public String getKitName() {
        return kitName;
    }

    public Cache<ItemStack> getItems() {
        return items;
    }

    private static String getFormatedTitle(String kit) {
        return ColorUtils.parse(PlaceholderFormat.parseParams(Config.getEditorGUITitle(), kit));
    }




    
}
