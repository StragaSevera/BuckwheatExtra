package ru.ought.buckwheatextra.api

import gregtech.api.enums.ItemList
import gregtech.api.enums.Materials
import gregtech.api.enums.OrePrefixes
import gregtech.api.interfaces.IItemContainer
import net.minecraft.block.Block
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import ru.ought.buckwheatextra.blocks.Block_CasingsSuperpressure

object Api {
    lateinit var BlockCasingSuperpressure: Block

    private val Electric_Pumps = arrayOf<IItemContainer?>(null, ItemList.Electric_Pump_LV, ItemList.Electric_Pump_MV,
        ItemList.Electric_Pump_HV, ItemList.Electric_Pump_EV, ItemList.Electric_Pump_IV,
        ItemList.Electric_Pump_LuV, ItemList.Electric_Pump_ZPM, ItemList.Electric_Pump_UV)
    private val Circuit_Materials = arrayOf(Materials.Primitive, Materials.Basic, Materials.Good,
        Materials.Advanced, Materials.Data, Materials.Elite, Materials.Master, Materials.Ultimate,
        Materials.Superconductor, Materials.Infinite)

    fun init() {
        BlockCasingSuperpressure = Block_CasingsSuperpressure()
    }

    // TODO: Fix the Any passing
    fun getCircuitTiered(tier: Int): Any {
        return OrePrefixes.circuit[Circuit_Materials[tier]]
    }

    fun getPumpTiered(tier: Int): Any {
        return Electric_Pumps[tier] ?: ItemStack(Items.bucket, 1)
    }
}