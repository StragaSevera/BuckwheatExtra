package ru.ought.buckwheatextra.loaders;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.internal.IGT_RecipeAdder;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import ru.ought.buckwheatextra.enums.BWItemList;
import ru.ought.buckwheatextra.enums.BWMaterials;

public class BWPostloader implements Runnable {
    private long bits =
            GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;
    private long bitsd = GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;

    @Override
    public void run() {
        initMachineRecipes(GT_Values.RA);

        initCraftingRecipes();
//        BWItemList.Super_Tank_ULV.set(new BW_MetaTileEntity_SuperTank(139, "super.tank.tier.00", "Super Tank (Steam Age)", 0).getStackForm(1L));
//        BWItemList.Super_Tank_LV.set(new BW_MetaTileEntity_SuperTank(140, "super.tank.tier.01", "Super Tank I", 1).getStackForm(1L));
//        BWItemList.Super_Tank_MV.set(new BW_MetaTileEntity_SuperTank(141, "super.tank.tier.02", "Super Tank II", 2).getStackForm(1L));
//        BWItemList.Super_Tank_HV.set(new BW_MetaTileEntity_SuperTank(142, "super.tank.tier.03", "Super Tank III", 3).getStackForm(1L));
//        BWItemList.Super_Tank_EV.set(new BW_MetaTileEntity_SuperTank(143, "super.tank.tier.04", "Super Tank IV", 4).getStackForm(1L));
//        BWItemList.Super_Tank_IV.set(new BW_MetaTileEntity_SuperTank(144, "super.tank.tier.05", "Super Tank V", 5).getStackForm(1L));

//        GT_ModHandler.addCraftingRecipe(BWItemList.Super_Tank_ULV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_LV, 'M', BWItemList.Casing_Superpressure_0, 'G', OrePrefixes.pipeMedium.get(Materials.Bronze), 'D', OrePrefixes.circuit.get(Materials.Primitive), 'P', OrePrefixes.plate.get(Materials.Steel)});
//        GT_ModHandler.addCraftingRecipe(BWItemList.Super_Tank_LV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_MV, 'M', BWItemList.Casing_Superpressure_1, 'G', OrePrefixes.pipeLarge.get(Materials.Bronze), 'D', OrePrefixes.circuit.get(Materials.Basic), 'P', OrePrefixes.plate.get(Materials.Aluminium)});
//        GT_ModHandler.addCraftingRecipe(BWItemList.Super_Tank_MV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_HV, 'M', BWItemList.Casing_Superpressure_2, 'G', OrePrefixes.pipeLarge.get(Materials.Steel), 'D', OrePrefixes.circuit.get(Materials.Good), 'P', OrePrefixes.plate.get(Materials.StainlessSteel)});
//        GT_ModHandler.addCraftingRecipe(BWItemList.Super_Tank_HV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_HV, 'M', BWItemList.Casing_Superpressure_3, 'G', ItemList.Field_Generator_LV, 'D', OrePrefixes.circuit.get(Materials.Advanced), 'P', OrePrefixes.plate.get(Materials.StainlessSteel)});
//        GT_ModHandler.addCraftingRecipe(BWItemList.Super_Tank_EV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_EV, 'M', BWItemList.Casing_Superpressure_4, 'G', ItemList.Field_Generator_MV, 'D', OrePrefixes.circuit.get(Materials.Data),'P', OrePrefixes.plate.get(Materials.Titanium)});
//        GT_ModHandler.addCraftingRecipe(BWItemList.Super_Tank_IV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_EV, 'M', BWItemList.Casing_Tank_5, 'G', ItemList.Field_Generator_HV, 'D', OrePrefixes.circuit.get(Materials.Elite), 'P', OrePrefixes.plate.get(Materials.Titanium)});
    }

    private void initMachineRecipes(IGT_RecipeAdder ra) {
        // Cadmium Solution
        ra.addChemicalRecipe(
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cadmium, 1), null,
                Materials.Water.getFluid(1000),
                BWMaterials.CadmiumSolution.getFluid(1000), null, 10 * 20, 2
        );
        ra.addChemicalRecipeForBasicMachineOnly(
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cadmium, 1),
                Materials.Water.getCells(1), null,
                GT_Values.NF, BWMaterials.CadmiumSolution.getCells(1), GT_Values.NI, 10 * 20, 2
        );
        // Cadmium-Titanium Solution
        ra.addChemicalRecipe(
                GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Titanium, 1), null,
                BWMaterials.CadmiumSolution.getFluid(1000),
                BWMaterials.CadmiumTitaniumSolution.getFluid(1000), null, 60 * 20, 40
        );
        ra.addChemicalRecipeForBasicMachineOnly(
                GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Titanium, 1),
                BWMaterials.CadmiumSolution.getCells(1), null,
                GT_Values.NF, BWMaterials.CadmiumTitaniumSolution.getCells(1), GT_Values.NI, 60 * 20, 40
        );
        // Cadmium Coated Metals
        addCoatingRecipes(ra, BWMaterials.CadmiumSolution, Materials.Steel, BWMaterials.CadmiumCoatedSteel);
        addCoatingRecipes(ra, BWMaterials.CadmiumSolution, Materials.BlackSteel, BWMaterials.CadmiumCoatedBlackSteel);
        addCoatingRecipes(ra, BWMaterials.CadmiumSolution, Materials.RedSteel,
                BWMaterials.CadmiumCoatedRedSteel);
        addCoatingRecipes(ra, BWMaterials.CadmiumTitaniumSolution, Materials.RedSteel,
                BWMaterials.CadmiumTitaniumCoatedRedSteel);
        addCoatingRecipes(ra, BWMaterials.CadmiumTitaniumSolution, Materials.TungstenSteel,
                BWMaterials.CadmiumTitaniumCoatedTungstensteel);
    }

    private void addCoatingRecipes(IGT_RecipeAdder ra, Materials coating, Materials material, Materials resultingMaterial) {
        ra.addElectrolyzerRecipe(
                GT_OreDictUnificator.get(OrePrefixes.ingot, material, 1), GT_Values.NI,
                coating.getFluid(1000), GT_Values.NF,
                GT_OreDictUnificator.get(OrePrefixes.ingot, resultingMaterial, 1),
                GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI,
                null, 30 * 20, 24
        );
        ra.addElectrolyzerRecipe(
                GT_OreDictUnificator.get(OrePrefixes.plate, material, 1), GT_Values.NI,
                coating.getFluid(1000), GT_Values.NF,
                GT_OreDictUnificator.get(OrePrefixes.plate, resultingMaterial, 1),
                GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI,
                null, 30 * 20, 24
        );
    }

    private void initCraftingRecipes() {
        // Tank Casings
        // TODO: Add high tier glass
        GT_ModHandler.addCraftingRecipe(BWItemList.Casing_Superpressure_0.get(1), bitsd,
                getSquareRecipeWithCenter(OrePrefixes.plate.get(Materials.Steel), new ItemStack(Blocks.glass, 1))
        );
        GT_ModHandler.addCraftingRecipe(BWItemList.Casing_Superpressure_1.get(1), bitsd,
                getSquareRecipeWithCenter(OrePrefixes.plate.get(BWMaterials.CadmiumCoatedSteel),
                        OrePrefixes.glass.get(Materials.Reinforced))
        );
        GT_ModHandler.addCraftingRecipe(BWItemList.Casing_Superpressure_2.get(1), bitsd,
                getSquareRecipeWithCenter(OrePrefixes.plate.get(BWMaterials.CadmiumCoatedBlackSteel),
                        OrePrefixes.glass.get(Materials.Reinforced))
        );
        GT_ModHandler.addCraftingRecipe(BWItemList.Casing_Superpressure_3.get(1), bitsd,
                getSquareRecipeWithCenter(OrePrefixes.plate.get(BWMaterials.CadmiumCoatedRedSteel),
                        OrePrefixes.glass.get(Materials.Reinforced))
        );
        GT_ModHandler.addCraftingRecipe(BWItemList.Casing_Superpressure_4.get(1), bitsd,
                getSquareRecipeWithCenter(OrePrefixes.plate.get(BWMaterials.CadmiumTitaniumCoatedRedSteel),
                        OrePrefixes.glass.get(Materials.Reinforced))
        );
        GT_ModHandler.addCraftingRecipe(BWItemList.Casing_Superpressure_5.get(1), bitsd,
                getSquareRecipeWithCenter(
                        OrePrefixes.plate.get(BWMaterials.CadmiumTitaniumCoatedTungstensteel),
                        OrePrefixes.glass.get(Materials.Reinforced))
        );
    }

    private Object[] getSquareRecipeWithCenter(Object outer, Object inner) {
        return new Object[]{"OOO", "OIO", "OOO", 'O', outer, 'I', inner};
    }

    private Object[] getHollowRecipe(Object outer) {
        return new Object[]{"OOO", "O O", "OOO", 'O', outer};
    }
}
