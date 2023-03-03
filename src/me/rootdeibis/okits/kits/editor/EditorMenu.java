package me.rootdeibis.okits.kits.editor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
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

    private String FROZEN_TIME = "1h";

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


        if(items.all().size() != 0) {
            for (ItemStack item : items.all()) {
                menu.getInventory().addItem(item);
            }
        }

        

        createActionsButons(menu);
    }

    private void createActionsButons(GUIMenu menu) {
        GUIButton saveButton = new GUIButton(52);
        GUIButton cancelButton = new GUIButton(53);
        GUIButton infoButton = new GUIButton(45);

        saveButton.setMaterial(Material.matchMaterial("WOOL"));
        saveButton.setMaterialData((byte) 5);
        saveButton.setDisplayName(Config.getEditorSaveBtnDisplayName());
        saveButton.setLore(Config.getEditorSaveBtnLore());

        cancelButton.setMaterial(Material.matchMaterial("BARRIER"));
        cancelButton.setDisplayName(Config.getEditorCancelBtnDisplayName());
        cancelButton.setLore(Config.getEditorCancelBtnLore());

        infoButton.setMaterial(Material.matchMaterial("PAPER"));

        infoButton.setDisplayName("&eKit Information");


        List<String> information = new ArrayList<>();

        information.add("");
        information.add("&6Name: &f" + this.kitName);
        information.add("&6Frozen Time:" + this.FROZEN_TIME);

        infoButton.setLore(information);
        saveButton.onClick(e -> {
            e.getWhoClicked().closeInventory();

            menu.removeButtons();
            this.saveKit();

            MessageUtils.sendTo(e.getWhoClicked(), Config.getCreatedMessage(), Config.ConfigPlacelholders, kitName);

        });

        cancelButton.onClick(e -> {
            e.getWhoClicked().closeInventory();
        });

        menu.addButton(cancelButton);
        menu.addButton(saveButton);
        menu.addButton(infoButton);
    }


    private void saveKit() {
        KitManager.createFromInv(kitName,  this.menu.getInventory()).save();
    }

    public void open(Player player) {
        menu.open(player);
    }



    
}
