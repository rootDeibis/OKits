package me.rootdeibis.okits.kits.editor.buttons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.Functions;
import me.rootdeibis.mirandalib.utils.guifactory.GUIButton;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.kits.editor.menus.FreezeMenu;
import me.rootdeibis.okits.kits.editor.menus.PrincipalMenu;

public class FrozenSettingsButton extends GUIButton {

    private final PrincipalMenu from;

    public FrozenSettingsButton(PrincipalMenu from) {
        super(46);

        this.from = from;

        this.setMaterial(Material.matchMaterial("ICE"));

        this.setDisplayName();
        this.setLore();
        this.onClick(this.onclick());
    }

    private void setDisplayName() {

        this.setDisplayName(ColorUtils.parse(Config.getEditorFrozenBtnDisplayName()));

    }

    private void setLore() {
        this.setLore(ColorUtils.parse(Config.getEditorFrozenBtnLore()));
    }

    private Functions.Function<InventoryClickEvent> onclick() {
        return (e) -> {

            new FreezeMenu(this.from).open((Player) e.getWhoClicked());
        };
    }

}
