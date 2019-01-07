package ru.ought.buckwheatextra.api;


import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IItemContainer;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BW_API {
    public static Block BlockCasingSuperpressure;

    private static IItemContainer[] Electric_Pumps = {null, ItemList.Electric_Pump_LV, ItemList.Electric_Pump_MV,
            ItemList.Electric_Pump_HV, ItemList.Electric_Pump_EV, ItemList.Electric_Pump_IV,
            ItemList.Electric_Pump_LuV, ItemList.Electric_Pump_ZPM, ItemList.Electric_Pump_UV};
    private static Materials[] Circuit_Materials = {Materials.Primitive, Materials.Basic, Materials.Good,
            Materials.Advanced, Materials.Data, Materials.Elite, Materials.Master, Materials.Ultimate,
            Materials.Superconductor, Materials.Infinite};

    public static Object getCircuitTiered(int tier) {
        return OrePrefixes.circuit.get(Circuit_Materials[tier]);
    }

    public static Object getPumpTiered(int tier) {
        return (tier == 0) ? new ItemStack(Items.bucket, 1) : Electric_Pumps[tier];
    }
}
