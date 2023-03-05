package me.rootdeibis.okits.kits.editor.menus;

import me.rootdeibis.mirandalib.utils.ColorUtils;
import me.rootdeibis.mirandalib.utils.PlaceholderFormat;
import me.rootdeibis.mirandalib.utils.guifactory.GUIMenu;
import me.rootdeibis.okits.configurations.Config;
import me.rootdeibis.okits.kits.editor.buttons.frozen.BackButton;

public class FreezeMenu extends GUIMenu {

    private final PrincipalMenu from;

    public FreezeMenu(PrincipalMenu from) {
        super(getFormatedTitle(from.getKit().getName()), 3);

        this.from = from;
       

        this.loadButtons();
    }

    private void loadButtons() {
        this.addButton(new BackButton(this));
    }

    private static String getFormatedTitle(String kit) {
        return ColorUtils.parse(PlaceholderFormat.parseParams(Config.getFrozenGUITitle(), kit));
    }

    public PrincipalMenu getFrom() {
        return from;
    }
    
}
