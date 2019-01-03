package ru.ought.buckwheatextra.enums;

import gregtech.api.enums.Textures;
import gregtech.api.enums.Textures.BlockIcons.CustomIcon;
import gregtech.api.interfaces.IIconContainer;

public class BuckwheatTextures {
    public static final CustomIcon OVERLAY_STANK = new CustomIcon("buckwheat" +
            "/OVERLAY_STANK");
    public static final CustomIcon MACHINE_CASING_TANK_DEFAULT = new CustomIcon("buckwheat/MACHINE_CASING_TANK");
    public static final CustomIcon[] MACHINE_CASING_TANK_ARRAY;
    
    static {
        MACHINE_CASING_TANK_ARRAY = new CustomIcon[16];
        for (int i = 0; i < MACHINE_CASING_TANK_ARRAY.length; i++) {
            MACHINE_CASING_TANK_ARRAY[i] = new CustomIcon("buckwheat" +
                    "/MACHINE_CASING_TANK_" + i);
        }
    }
}