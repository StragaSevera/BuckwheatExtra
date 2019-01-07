package ru.ought.buckwheatextra.loaders;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.internal.IGT_RecipeAdder;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.ought.buckwheatextra.api.BW_API;
import ru.ought.buckwheatextra.blocks.BW_Block_CasingsSuperpressure;
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
        // Superpressure Casings
        Materials[] platesCasing = {Materials.Steel, BWMaterials.CadmiumCoatedSteel,
                BWMaterials.CadmiumCoatedBlackSteel, BWMaterials.CadmiumCoatedRedSteel, 
                BWMaterials.CadmiumTitaniumCoatedRedSteel,
                BWMaterials.CadmiumTitaniumCoatedTungstensteel};
        for (int i = 0; i <= BW_Block_CasingsSuperpressure.MAX_TIER; i++) {
            // TODO: Add high tier glass
            Object glass = (i == 0) ? new ItemStack(Blocks.glass, 1) : OrePrefixes.glass.get(Materials.Reinforced);
            GT_ModHandler.addCraftingRecipe(BWItemList.Casings_Superpressure[i].get(1), bitsd,
                    new Object[]{"OOO", "OIO", "OOO", 
                            'O', OrePrefixes.plate.get(platesCasing[i]), 
                            'I', glass});
        }
        
        // Super Tanks
        Materials[] pipesTank = {Materials.Bronze, Materials.Steel, Materials.Plastic,
                Materials.Polytetrafluoroethylene, Materials.Titanium, Materials.Ultimate};
        for (int i = 0; i <= BW_Block_CasingsSuperpressure.MAX_TIER; i++) {
            GT_ModHandler.addCraftingRecipe(BWItemList.Super_Tanks[i].get(1), bitsd, 
                    new Object[]{"DGD", "PMP", "DUD", 'U', BW_API.getPumpTiered(i), 
                            'M', BWItemList.Casings_Superpressure[i], 
                            'G', OrePrefixes.pipeMedium.get(pipesTank[i]), 
                            'D', BW_API.getCircuitTiered(i), 
                            'P', OrePrefixes.plate.get(platesCasing[i])});
        }
    }
}
