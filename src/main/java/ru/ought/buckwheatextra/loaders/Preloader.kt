package ru.ought.buckwheatextra.loaders

import gregtech.api.enums.GT_Values
import ru.ought.buckwheatextra.api.Api
import ru.ought.buckwheatextra.blocks.Block_CasingsSuperpressure
import ru.ought.buckwheatextra.enums.BWItemList
import ru.ought.buckwheatextra.enums.BWTextures
import ru.ought.buckwheatextra.tileentities.BW_MetaTileEntity_SuperTank

class Preloader : Runnable {
    override fun run() {
        BWTextures.init() // TODO: Remove hack
        Api.init()
        val superTankIDs = intArrayOf(22000, 22001, 22002, 22003, 22004, 22005)
        for (i in 0..Block_CasingsSuperpressure.MAX_TIER) {
            BWItemList.Super_Tanks[i].set(BW_MetaTileEntity_SuperTank(superTankIDs[i],
                "super.tank.tier.$i",
                "Super Tank " + GT_Values.VN[i], i).getStackForm(1))
        }
    }
}
