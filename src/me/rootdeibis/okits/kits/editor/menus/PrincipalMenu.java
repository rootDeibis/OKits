package me.rootdeibis.okits.kits.editor.menus;

import java.util.function.Predicate;


import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.PlaceholderFormat;
import me.rootdeibis.mirandalib.utils.guifactory.GUIMenu;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.kits.Kit;
import me.rootdeibis.okits.kits.KitManager;
import me.rootdeibis.okits.kits.editor.buttons.CancelButton;
import me.rootdeibis.okits.kits.editor.buttons.FrozenSettingsButton;
import me.rootdeibis.okits.kits.editor.buttons.SaveButton;

public class PrincipalMenu extends GUIMenu {


    private final Kit kit;


    public PrincipalMenu(String name) {
        super(PrincipalMenu.getFormatedTitle(name), 6);

        Predicate<Kit> predication = k -> k.getName().equalsIgnoreCase(name);

        this.kit = KitManager.getKits().has(predication) ? KitManager.getKits().find(predication) : KitManager.create(name);

        this.setEditable(true);
        this.loadButtons();


        if(kit.getItems().size() > 0) {
            kit.getItems().forEach(item -> this.getInventory().addItem(item));
        }
    }


    private void loadButtons() {
        this.addButton(new SaveButton(this));
        this.addButton(new CancelButton());
        this.addButton(new FrozenSettingsButton(this));
    }

    public Kit getKit() {
        return kit;
    }

    private static String getFormatedTitle(String kit) {
        return ColorUtils.parse(PlaceholderFormat.parseParams(Config.getEditorGUITitle(), kit));
    }






    
}
