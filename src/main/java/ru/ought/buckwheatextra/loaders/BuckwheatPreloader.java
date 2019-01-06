package ru.ought.buckwheatextra.loaders;

import ru.ought.buckwheatextra.api.BuckwheatAPI;
import ru.ought.buckwheatextra.blocks.Buckwheat_Block_CasingsSuperPressure;
import ru.ought.buckwheatextra.enums.BuckwheatTextures;

public class BuckwheatPreloader implements Runnable {
        @Override
    public void run() {
        BuckwheatTextures.init();
        BuckwheatAPI.BlockCasingSuperPressure = new Buckwheat_Block_CasingsSuperPressure();
        
    }
}
