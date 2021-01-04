package ru.ought.buckwheatextra.enums

import gregtech.api.enums.Textures.BlockIcons

object BWTextures {
    val OVERLAY_STANK = BlockIcons.CustomIcon("buckwheat" +
            "/OVERLAY_STANK")
    val MACHINE_CASING_SUPREPRESSURE_DEFAULT =
        BlockIcons.CustomIcon("buckwheat/MACHINE_CASING_SUPERPRESSURE")
    val MACHINE_CASING_SUPERPRESSURE_ARRAY: Array<BlockIcons.CustomIcon> = Array(16) { i ->
        BlockIcons.CustomIcon("buckwheat/MACHINE_CASING_SUPERPRESSURE_$i")
    }

    // Used for forcing textures to load at the preload stage. UGLY HACK
    // TODO: Remove hack
    fun init() {}
}