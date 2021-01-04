package ru.ought.buckwheatextra.loaders

import gregtech.api.enums.GT_Values
import gregtech.api.enums.Materials
import gregtech.api.enums.OrePrefixes
import gregtech.api.interfaces.internal.IGT_RecipeAdder
import gregtech.api.util.GT_ModHandler
import gregtech.api.util.GT_OreDictUnificator
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import ru.ought.buckwheatextra.api.Api
import ru.ought.buckwheatextra.blocks.Block_CasingsSuperpressure
import ru.ought.buckwheatextra.enums.BWItemList
import ru.ought.buckwheatextra.enums.BWMaterials

class Postloader : Runnable {
//    private val bits: Long =
//        GT_ModHandler.RecipeBits.NOT_REMOVABLE or GT_ModHandler.RecipeBits.REVERSIBLE or GT_ModHandler.RecipeBits.BUFFERED
    private val bitsd: Long =
        GT_ModHandler.RecipeBits.DISMANTLEABLE or GT_ModHandler.RecipeBits.NOT_REMOVABLE or GT_ModHandler.RecipeBits.REVERSIBLE or GT_ModHandler.RecipeBits.BUFFERED

    override fun run() {
        initMachineRecipes(GT_Values.RA)
        initCraftingRecipes()
    }

    private fun initMachineRecipes(ra: IGT_RecipeAdder) {
        // Cadmium Solution
        ra.addChemicalRecipe(
            GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cadmium, 1), null,
            Materials.Water.getFluid(1000),
            BWMaterials.CadmiumSolution.getFluid(1000), null, 10 * 20, 2
        )
        ra.addChemicalRecipeForBasicMachineOnly(
            GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Cadmium, 1),
            Materials.Water.getCells(1), null,
            GT_Values.NF, BWMaterials.CadmiumSolution.getCells(1), GT_Values.NI, 10 * 20, 2
        )
        // Cadmium-Titanium Solution
        ra.addChemicalRecipe(
            GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Titanium, 1), null,
            BWMaterials.CadmiumSolution.getFluid(1000),
            BWMaterials.CadmiumTitaniumSolution.getFluid(1000), null, 60 * 20, 40
        )
        ra.addChemicalRecipeForBasicMachineOnly(
            GT_OreDictUnificator.get(OrePrefixes.dustTiny, Materials.Titanium, 1),
            BWMaterials.CadmiumSolution.getCells(1), null,
            GT_Values.NF, BWMaterials.CadmiumTitaniumSolution.getCells(1), GT_Values.NI, 60 * 20, 40
        )
        ra.addChemicalRecipe(
            GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Titanium, 1), null,
            BWMaterials.CadmiumSolution.getFluid(9000),
            BWMaterials.CadmiumTitaniumSolution.getFluid(9000), null, 60 * 20, 40
        )
        ra.addChemicalRecipeForBasicMachineOnly(
            GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Titanium, 1),
            BWMaterials.CadmiumSolution.getCells(9), null,
            GT_Values.NF, BWMaterials.CadmiumTitaniumSolution.getCells(9), GT_Values.NI, 60 * 20, 40
        )
        // Cadmium Coated Metals
        addCoatingRecipes(ra,
            BWMaterials.CadmiumSolution,
            Materials.Steel,
            BWMaterials.CadmiumCoatedSteel,
            BWMaterials.CadmiumSulfate)
        addCoatingRecipes(ra,
            BWMaterials.CadmiumSolution,
            Materials.BlackSteel,
            BWMaterials.CadmiumCoatedBlackSteel,
            BWMaterials.CadmiumSulfate)
        addCoatingRecipes(ra, BWMaterials.CadmiumSolution, Materials.RedSteel,
            BWMaterials.CadmiumCoatedRedSteel, BWMaterials.CadmiumSulfate)
        addCoatingRecipes(ra, BWMaterials.CadmiumTitaniumSolution, Materials.RedSteel,
            BWMaterials.CadmiumTitaniumCoatedRedSteel, BWMaterials.CadmiumTitaniumSulfate)
        addCoatingRecipes(ra, BWMaterials.CadmiumTitaniumSolution, Materials.TungstenSteel,
            BWMaterials.CadmiumTitaniumCoatedTungstensteel, BWMaterials.CadmiumTitaniumSulfate)
    }

    private fun addCoatingRecipes(
        ra: IGT_RecipeAdder,
        coating: Materials,
        material: Materials,
        resultingMaterial: Materials,
        coatingSalt: Materials
    ) {
        ra.addElectrolyzerRecipe(
            GT_OreDictUnificator.get(OrePrefixes.ingot, material, 1), GT_Values.NI,
            coating.getFluid(1000), GT_Values.NF,
            GT_OreDictUnificator.get(OrePrefixes.ingot, resultingMaterial, 1),
            GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI,
            null, 30 * 20, 24
        )
        ra.addElectrolyzerRecipe(
            GT_OreDictUnificator.get(OrePrefixes.plate, material, 1), GT_Values.NI,
            coating.getFluid(1000), GT_Values.NF,
            GT_OreDictUnificator.get(OrePrefixes.plate, resultingMaterial, 1),
            GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI,
            null, 30 * 20, 24
        )
        ra.addChemicalRecipe(resultingMaterial.getIngots(1), GT_Values.NI,
            Materials.SulfuricAcid.getFluid(1000),
            Materials.DilutedSulfuricAcid.getFluid(1000), material.getIngots(1),
            coatingSalt.getDust(3), 10 * 20, 10)
        ra.addChemicalRecipe(resultingMaterial.getPlates(1), GT_Values.NI,
            Materials.SulfuricAcid.getFluid(1000),
            Materials.DilutedSulfuricAcid.getFluid(1000), material.getPlates(1),
            coatingSalt.getDust(3), 10 * 20, 10)
    }

    private fun initCraftingRecipes() {
        // Superpressure Casings
        val platesCasing = arrayOf(Materials.Steel, BWMaterials.CadmiumCoatedSteel,
            BWMaterials.CadmiumCoatedBlackSteel, BWMaterials.CadmiumCoatedRedSteel,
            BWMaterials.CadmiumTitaniumCoatedRedSteel,
            BWMaterials.CadmiumTitaniumCoatedTungstensteel)
        for (i in 0..Block_CasingsSuperpressure.MAX_TIER) {
            // TODO: Add high tier glass
            val glass: Any = if (i == 0) ItemStack(Blocks.glass, 1) else OrePrefixes.glass.get(Materials.Reinforced)
            GT_ModHandler.addCraftingRecipe(BWItemList.Casings_Superpressure.get(i).get(1),
                bitsd,
                arrayOf("OOO", "OIO", "OOO",
                    'O', OrePrefixes.plate.get(platesCasing[i]),
                    'I', glass))
        }

        // Super Tanks
        val pipesTank = arrayOf(Materials.Bronze, Materials.Steel, Materials.Plastic,
            Materials.Polytetrafluoroethylene, Materials.Titanium, Materials.Ultimate)
        for (i in 0..Block_CasingsSuperpressure.MAX_TIER) {
            GT_ModHandler.addCraftingRecipe(BWItemList.Super_Tanks.get(i).get(1),
                bitsd,
                arrayOf<Any>("DGD", "PMP", "DUD", 'U', Api.getPumpTiered(i),
                    'M', BWItemList.Casings_Superpressure.get(i),
                    'G', OrePrefixes.pipeMedium.get(pipesTank[i]),
                    'D', Api.getCircuitTiered(i),
                    'P', OrePrefixes.plate.get(platesCasing[i])))
        }
    }
}