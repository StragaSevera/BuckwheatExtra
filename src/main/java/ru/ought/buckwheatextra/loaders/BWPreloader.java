package ru.ought.buckwheatextra.loaders;

import ru.ought.buckwheatextra.api.BW_API;
import ru.ought.buckwheatextra.blocks.BW_Block_CasingsSuperPressure;
import ru.ought.buckwheatextra.enums.BWTextures;

public class BWPreloader implements Runnable {
        @Override
    public void run() {
        BWTextures.init();
        BW_API.BlockCasingSuperPressure = new BW_Block_CasingsSuperPressure();
        
    }
}
