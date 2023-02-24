package me.rootdeibis.okits.kits.editor;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.rootdeibis.mirandalib.utils.Cache;
import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.PlaceholderFormat;
import me.rootdeibis.mirandalib.utils.guifactory.GUIButton;
import me.rootdeibis.mirandalib.utils.guifactory.GUIMenu;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.configurations.MessageUtils;
import me.rootdeibis.okits.kits.KitManager;

public class EditorMenu {



    private final String kitName;
    private GUIMenu menu;
    private Cache<ItemStack> items = new Cache<>();

    public EditorMenu(String kitName, Cache<ItemStack> items) {
        this.kitName = kitName;
        
        this.items = items;

        this.load();
    }

    public EditorMenu(String kitName) {
        this.kitName = kitName;

        this.load();
    } 

    private void load() {
        menu = new GUIMenu(ColorUtils.parse(PlaceholderFormat.parseParams(Config.getEditorGUITitle(), kitName)), 6);

        menu.setEditable(true);

        GUIButton saveButton = new GUIButton(52);


        if(items.all().size() != 0) {
            for (ItemStack item : items.all()) {
                menu.getInventory().addItem(item);
            }
        }

        saveButton.setDisplayName("&aSave Kit");

        saveButton.onClick(e -> {
            e.getWhoClicked().closeInventory();

            menu.removeButton(btn -> true);
            this.saveKit();

            MessageUtils.sendTo(e.getWhoClicked(), Config.getCreatedMessage(), Config.ConfigPlacelholders, kitName);

        });

        menu.addButton(saveButton);
    }

    private void saveKit() {
        KitManager.createFromInv(kitName,  this.menu.getInventory()).save();

    }

    public void open(Player player) {
        menu.open(player);
    }



    
}
