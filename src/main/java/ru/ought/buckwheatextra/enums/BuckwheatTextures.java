package ru.ought.buckwheatextra.enums;

import gregtech.api.enums.Textures.BlockIcons.CustomIcon;

// TODO: Refactor texture loading.
public class BuckwheatTextures {
    public static final CustomIcon OVERLAY_STANK = new CustomIcon("buckwheat" +
            "/OVERLAY_STANK");
    public static final CustomIcon MACHINE_CASING_SUPREPRESSURE_DEFAULT = new CustomIcon("buckwheat" +
            "/MACHINE_CASING_SUPERPRESSURE");
    public static final CustomIcon[] MACHINE_CASING_SUPERPRESSURE_ARRAY;

    static {
        MACHINE_CASING_SUPERPRESSURE_ARRAY = new CustomIcon[16];
        for (int i = 0; i < MACHINE_CASING_SUPERPRESSURE_ARRAY.length; i++) {
            MACHINE_CASING_SUPERPRESSURE_ARRAY[i] = new CustomIcon("buckwheat" +
                    "/MACHINE_CASING_SUPERPRESSURE_" + i);
        }
    }

    // Used for forcing textures to load at the preload stage. UGLY HACK
    public static void init() {
    }
}