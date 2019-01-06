package ru.ought.buckwheatextra.loaders;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.internal.IGT_RecipeAdder;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import ru.ought.buckwheatextra.enums.BuckwheatItemList;
import ru.ought.buckwheatextra.enums.BuckwheatMaterials;

public class BuckwheatPostloader implements Runnable {
    private long bits =
            GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;
    private long bitsd = GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.NOT_REMOVABLE | GT_ModHandler.RecipeBits.REVERSIBLE | GT_ModHandler.RecipeBits.BUFFERED;

    @Override
    public void run() {
        initMachineRecipes(GT_Values.RA);

        initCraftingRecipes();
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_2.get(1L), bitsd, new Object[]{"PPP", "PIP", "PPP", 'P', OrePrefixes.plate.get(Materials.Aluminium), 'I', OrePrefixes.pipeLarge.get(Materials.Plastic)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_3.get(1L), bitsd, new Object[]{"PPP", "PIP", "PPP", 'P', OrePrefixes.plate.get(Materials.StainlessSteel), 'I', OrePrefixes.pipeLarge.get(Materials.Polytetrafluoroethylene)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_4.get(1L), bitsd, new Object[]{"PPP", "PIP", "PPP", 'P', OrePrefixes.plate.get(Materials.Titanium), 'I', OrePrefixes.pipeLarge.get(Materials.StainlessSteel)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_5.get(1L), bitsd, new Object[]{"PPP", "PIP", "PPP", 'P', OrePrefixes.plate.get(Materials.TungstenSteel), 'I', OrePrefixes.pipeLarge.get(Materials.Titanium)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_6.get(1L), bitsd, new Object[]{"PPP", "PIP", "PPP", 'P', OrePrefixes.plate.get(Materials.Chrome), 'I', OrePrefixes.pipeLarge.get(Materials.TungstenSteel)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_7.get(1L), bitsd, new Object[]{"PPP", "PIP", "PPP", 'P', OrePrefixes.plate.get(Materials.Iridium), 'I', OrePrefixes.pipeLarge.get(Materials.NiobiumTitanium)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_8.get(1L), bitsd, new Object[]{"PPP", "PIP", "PPP", 'P', OrePrefixes.plate.get(Materials.Osmium), 'I', OrePrefixes.pipeLarge.get(Materials.Enderium)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_9.get(1L), bitsd, new Object[]{"PPP", "PIP", "PPP", 'P', OrePrefixes.plate.get(Materials.Tritanium), 'I', OrePrefixes.pipeLarge.get(Materials.Naquadah)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_10.get(1L), bitsd, new Object[]{"PPP", "PIP", "PPP", 'P', OrePrefixes.plate.get(Materials.Neutronium), 'I', OrePrefixes.pipeLarge.get(Materials.Neutronium)});

//        BuckwheatItemList.Super_Tank_ULV.set(new GT_MetaTileEntity_SuperTank(139, "super.tank.tier.00", "Super Tank (Steam Age)", 0).getStackForm(1L));
//        BuckwheatItemList.Super_Tank_LV.set(new GT_MetaTileEntity_SuperTank(140, "super.tank.tier.01", "Super Tank I", 1).getStackForm(1L));
//        BuckwheatItemList.Super_Tank_MV.set(new GT_MetaTileEntity_SuperTank(141, "super.tank.tier.02", "Super Tank II", 2).getStackForm(1L));
//        BuckwheatItemList.Super_Tank_HV.set(new GT_MetaTileEntity_SuperTank(142, "super.tank.tier.03", "Super Tank III", 3).getStackForm(1L));
//        BuckwheatItemList.Super_Tank_EV.set(new GT_MetaTileEntity_SuperTank(143, "super.tank.tier.04", "Super Tank IV", 4).getStackForm(1L));
//        BuckwheatItemList.Super_Tank_IV.set(new GT_MetaTileEntity_SuperTank(144, "super.tank.tier.05", "Super Tank V", 5).getStackForm(1L));

//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Super_Tank_ULV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_LV, 'M', BuckwheatItemList.Casing_Tank_0, 'G', OrePrefixes.pipeMedium.get(Materials.Bronze), 'D', OrePrefixes.circuit.get(Materials.Primitive), 'P', OrePrefixes.plate.get(Materials.Steel)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Super_Tank_LV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_MV, 'M', BuckwheatItemList.Casing_Tank_1, 'G', OrePrefixes.pipeLarge.get(Materials.Bronze), 'D', OrePrefixes.circuit.get(Materials.Basic), 'P', OrePrefixes.plate.get(Materials.Aluminium)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Super_Tank_MV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_HV, 'M', BuckwheatItemList.Casing_Tank_2, 'G', OrePrefixes.pipeLarge.get(Materials.Steel), 'D', OrePrefixes.circuit.get(Materials.Good), 'P', OrePrefixes.plate.get(Materials.StainlessSteel)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Super_Tank_HV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_HV, 'M', BuckwheatItemList.Casing_Tank_3, 'G', ItemList.Field_Generator_LV, 'D', OrePrefixes.circuit.get(Materials.Advanced), 'P', OrePrefixes.plate.get(Materials.StainlessSteel)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Super_Tank_EV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_EV, 'M', BuckwheatItemList.Casing_Tank_4, 'G', ItemList.Field_Generator_MV, 'D', OrePrefixes.circuit.get(Materials.Data),'P', OrePrefixes.plate.get(Materials.Titanium)});
//        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Super_Tank_IV.get(1L), bitsd,  new Object[]{"DGD", "PMP", "DUD", 'U', ItemList.Electric_Pump_EV, 'M', BuckwheatItemList.Casing_Tank_5, 'G', ItemList.Field_Generator_HV, 'D', OrePrefixes.circuit.get(Materials.Elite), 'P', OrePrefixes.plate.get(Materials.Titanium)});
    }

    private void initMachineRecipes(IGT_RecipeAdder ra) {
        // Cadmium Solution
        ra.addChemicalRecipe(
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cadmium, 1), null,
                Materials.Water.getFluid(1000),
                BuckwheatMaterials.CadmiumSolution.getFluid(1000), null, 10, 2
        );
        ra.addChemicalRecipeForBasicMachineOnly(
                GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cadmium, 1),
                Materials.Water.getCells(1), null,
                GT_Values.NF, BuckwheatMaterials.CadmiumSolution.getCells(1), GT_Values.NI, 10, 2
        );
        // Cadmium coated Steel
        ra.addElectrolyzerRecipe(
                GT_OreDictUnificator.get(OrePrefixes.ingot, Materials.Steel, 1), GT_Values.NI,
                BuckwheatMaterials.CadmiumSolution.getFluid(1000), GT_Values.NF,
                GT_OreDictUnificator.get(OrePrefixes.ingot, BuckwheatMaterials.CadmiumCoatedSteel, 1),
                GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI,
                new int[]{5000}, 30, 24
        );
        ra.addElectrolyzerRecipe(
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 1), GT_Values.NI,
                BuckwheatMaterials.CadmiumSolution.getFluid(1000), GT_Values.NF,
                GT_OreDictUnificator.get(OrePrefixes.plate, BuckwheatMaterials.CadmiumCoatedSteel, 1),
                GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI,
                new int[]{5000}, 30, 24
        );
    }

    private void initCraftingRecipes() {
        // Tank Casings
        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_0.get(1L), bitsd,
                getSquareRecipe(OrePrefixes.plate.get(Materials.Steel), OrePrefixes.pipeLarge.get(Materials.Bronze))
        );
        GT_ModHandler.addCraftingRecipe(BuckwheatItemList.Casing_Tank_1.get(1L), bitsd,
                getSquareRecipe(OrePrefixes.plate.get(BuckwheatMaterials.CadmiumCoatedSteel),
                        OrePrefixes.pipeLarge.get(Materials.Steel))
        );
    }

    private Object[] getSquareRecipe(Object outer, Object inner) {
        return new Object[]{"OOO", "OIO", "OOO", 'O', outer, 'I', inner};
    }    
    
    private Object[] getHollowRecipe(Object outer) {
        return new Object[]{"OOO", "O O", "OOO", 'O', outer};
    }
}
