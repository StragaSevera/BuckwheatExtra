package ru.ought.buckwheatextra.loaders;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import ru.ought.buckwheatextra.api.BW_API;
import ru.ought.buckwheatextra.blocks.BW_Block_CasingsSuperpressure;
import ru.ought.buckwheatextra.enums.BWItemList;
import ru.ought.buckwheatextra.enums.BWTextures;
import ru.ought.buckwheatextra.tileentities.BW_MetaTileEntity_SuperTank;

public class BWPreloader implements Runnable {
    @Override
    public void run() {
        BWTextures.init();
        BW_API.BlockCasingSuperpressure = new BW_Block_CasingsSuperpressure();

        int[] superTankIDs = {22000, 22001, 22002, 22003, 22004, 22005};
        for (int i = 0; i <= BW_Block_CasingsSuperpressure.MAX_TIER; i++) {
            BWItemList.Super_Tank_ULV.set(new BW_MetaTileEntity_SuperTank(superTankIDs[i], 
                    "super.tank.tier." + i, 
                    "Super Tank " + GT_Values.VN[i], i).getStackForm(1));
        }
    }
}
