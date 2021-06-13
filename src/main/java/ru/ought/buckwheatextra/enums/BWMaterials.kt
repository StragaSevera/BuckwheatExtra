package ru.ought.buckwheatextra.enums

import gregtech.api.enums.*
import gregtech.api.interfaces.IMaterialHandler
import ru.ought.buckwheatextra.enums.BWMaterials
import gregtech.api.objects.MaterialStack
import net.minecraft.enchantment.Enchantment
import java.util.ArrayList

class BWMaterials : IMaterialHandler {
    companion object {
        lateinit var CadmiumSolution: Materials
        lateinit var CadmiumTitaniumSolution: Materials
        lateinit var CadmiumCoatedSteel: Materials
        lateinit var CadmiumCoatedBlackSteel: Materials
        lateinit var CadmiumCoatedRedSteel: Materials
        lateinit var CadmiumTitaniumCoatedRedSteel: Materials
        lateinit var CadmiumTitaniumCoatedTungstensteel: Materials
        lateinit var CadmiumSulfate: Materials
        lateinit var TitaniumSulfate: Materials
        lateinit var CadmiumTitaniumSulfate: Materials
    }

    override fun onMaterialsInit() {
        CadmiumSolution = MaterialBuilder(420, TextureSet.SET_FLUID, "Cadmium Solution")
            .setRGB(48, 47, 47).setColor(Dyes.dyeBlack).addCell().addFluid()
            .setMaterialList(MaterialStack(Materials.Cadmium, 1))
            .addElectrolyzerRecipe().constructMaterial()
        CadmiumTitaniumSolution = MaterialBuilder(421, TextureSet.SET_FLUID, "Cadmium-Titanium Solution")
            .setRGB(80, 47, 80).setColor(Dyes.dyePurple).addCell().addFluid()
            .setMaterialList(MaterialStack(Materials.Cadmium, 9), MaterialStack(Materials.Titanium, 1))
            .addElectrolyzerRecipe().constructMaterial()

        CadmiumCoatedSteel = createCoatedMetal(422, CadmiumSolution, 1, Materials.Steel, 1, 60, 60, 60, Dyes.dyeBlack)
        CadmiumCoatedBlackSteel = createCoatedMetal(423, CadmiumSolution, 1, Materials.BlackSteel, 1,
            20, 20, 20, Dyes.dyeBlack)
        CadmiumCoatedRedSteel = createCoatedMetal(424, CadmiumSolution, 1, Materials.RedSteel, 1,
            60, 20, 20, Dyes.dyeRed)
        CadmiumTitaniumCoatedRedSteel = createCoatedMetal(425, CadmiumTitaniumSolution, 1, Materials.RedSteel, 9,
            80, 30, 80, Dyes.dyePurple)
        CadmiumTitaniumCoatedTungstensteel = createCoatedMetal(426, CadmiumTitaniumSolution, 1,
            Materials.TungstenSteel, 9,
            30, 30, 60, Dyes.dyeBlue)

        CadmiumSulfate = MaterialBuilder(427, TextureSet.SET_DULL, "Cadmium Sulfate")
            .setRGB(150, 150, 50).setColor(Dyes.dyeYellow).addDustItems()
            .setMaterialList(MaterialStack(Materials.Cadmium, 1), MaterialStack(Materials.Sulfur, 1),
                MaterialStack(Materials.Oxygen, 4))
            .addElectrolyzerRecipe().constructMaterial()
        TitaniumSulfate = MaterialBuilder(428, TextureSet.SET_DULL, "Titanium Sulfate")
            .setRGB(150, 80, 50).setColor(Dyes.dyePink).addDustItems()
            .setMaterialList(MaterialStack(Materials.Titanium, 1),
                MaterialStack(Materials.Sulfur, 2), MaterialStack(Materials.Oxygen, 8))
            .addElectrolyzerRecipe().constructMaterial()
        CadmiumTitaniumSulfate = MaterialBuilder(429, TextureSet.SET_DULL, "Cadmium-Titanium Sulfate")
            .setRGB(130, 65, 50).setColor(Dyes.dyeYellow).addDustItems()
            .setMaterialList(MaterialStack(CadmiumSulfate, 9),
                MaterialStack(TitaniumSulfate, 1))
            .addCentrifugeRecipe().constructMaterial()
    }

    private val disableOrePrefixesList: MutableList<Materials> = mutableListOf()
    private fun createCoatedMetal(
        subID: Int,
        coating: Materials,
        coatingAmount: Int,
        metal: Materials,
        metalAmount: Int,
        r: Int,
        g: Int,
        b: Int,
        dye: Dyes,
        enchantment: Enchantment = Enchantment.silkTouch,
        enchantmentLevel: Int = 1
    ): Materials {
        val name = getCoatedMetalName(coating, metal)
        val coatedMetal = MaterialBuilder(subID, TextureSet.SET_METALLIC, name)
            .setRGB(r, g, b).setColor(dye)
            .addMetalItems().addToolHeadItems().addGearItems()
            .setToolSpeed(metal.mToolSpeed).setDurability(metal.mDurability)
            .setMaterialList(MaterialStack(coating, coatingAmount.toLong()),
                MaterialStack(metal, metalAmount.toLong()))
            .constructMaterial()
        coatedMetal.remove(SubTag.SMELTING_TO_FLUID)
        coatedMetal.setEnchantmentForTools(enchantment, enchantmentLevel)
        disableOrePrefixesList.add(coatedMetal)
        return coatedMetal
    }

    private fun getCoatedMetalName(coating: Materials, metal: Materials): String {
        val coatingName = coating.mDefaultLocalName.split(" ").first()
        val metalName = metal.mDefaultLocalName
        return "$coatingName Coated $metalName"
    }

    override fun onComponentInit() {
        for (material in disableOrePrefixesList) {
            disableMetalOrePrefixes(material)
        }
    }

    private fun disableMetalOrePrefixes(metal: Materials) {
        OrePrefixes.dust.disableComponent(metal)
        OrePrefixes.dustSmall.disableComponent(metal)
        OrePrefixes.dustTiny.disableComponent(metal)
        OrePrefixes.nugget.disableComponent(metal)
        OrePrefixes.plateDouble.disableComponent(metal)
        OrePrefixes.screw.disableComponent(metal)
    }

    override fun onComponentIteration(materials: Materials) {}
}