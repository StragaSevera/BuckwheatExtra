package ru.ought.buckwheatextra.loaders;

import gregtech.GT_Mod;
import ru.ought.buckwheatextra.api.BuckwheatAPI;
import ru.ought.buckwheatextra.blocks.GT_Block_Casings6;
import ru.ought.buckwheatextra.enums.BuckwheatItemList;
import ru.ought.buckwheatextra.enums.BuckwheatTextures;

public class BuckwheatPreloader implements Runnable {
        @Override
    public void run() {
        BuckwheatTextures.init();
        BuckwheatAPI.sBlockCasings6 = new GT_Block_Casings6();
        BuckwheatItemList.sCadmiumSolution = GT_Mod.gregtechproxy.addFluid("cadmiumsolution",
                "Cadmium solution", null, 1, 295);
    }
}
