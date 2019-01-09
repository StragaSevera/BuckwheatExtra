package ru.ought.buckwheatextra.enums;

import gregtech.api.enums.Dyes;
import gregtech.api.enums.MaterialBuilder;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.SubTag;
import gregtech.api.enums.TextureSet;
import gregtech.api.interfaces.IMaterialHandler;
import gregtech.api.objects.MaterialStack;
import net.minecraft.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("WeakerAccess")
public class BWMaterials implements IMaterialHandler {
    public static Materials CadmiumSolution;
    public static Materials CadmiumTitaniumSolution;
    public static Materials CadmiumCoatedSteel;
    public static Materials CadmiumCoatedBlackSteel;
    public static Materials CadmiumCoatedRedSteel;
    public static Materials CadmiumTitaniumCoatedRedSteel;
    public static Materials CadmiumTitaniumCoatedTungstensteel;
    public static Materials CadmiumSulfate;
    public static Materials TitaniumSulfate;
    public static Materials CadmiumTitaniumSulfate;

    @Override
    public void onMaterialsInit() {
        CadmiumSolution = new MaterialBuilder(420, TextureSet.SET_FLUID, "Cadmium Solution")
                .setRGB(48, 47, 47).setColor(Dyes.dyeBlack).addCell().addFluid()
                .setMaterialList(new MaterialStack(Materials.Cadmium, 1))
                .addElectrolyzerRecipe().constructMaterial();
        CadmiumTitaniumSolution = new MaterialBuilder(421, TextureSet.SET_FLUID, "Cadmium-Titanium Solution")
                .setRGB(80, 47, 80).setColor(Dyes.dyePurple).addCell().addFluid()
                .setMaterialList(new MaterialStack(Materials.Cadmium, 9), new MaterialStack(Materials.Titanium, 1))
                .addElectrolyzerRecipe().constructMaterial();

        CadmiumCoatedSteel = createCoatedMetal(422, CadmiumSolution, 1, Materials.Steel, 1, 60, 60, 60, Dyes.dyeBlack);
        CadmiumCoatedBlackSteel = createCoatedMetal(423, CadmiumSolution, 1, Materials.BlackSteel, 1,
                20, 20, 20, Dyes.dyeBlack);
        CadmiumCoatedRedSteel = createCoatedMetal(424, CadmiumSolution, 1, Materials.RedSteel, 1,
                60, 20, 20, Dyes.dyeRed);
        CadmiumTitaniumCoatedRedSteel = createCoatedMetal(425, CadmiumTitaniumSolution, 1, Materials.RedSteel, 9,
                80, 30, 80, Dyes.dyePurple);
        CadmiumTitaniumCoatedTungstensteel = createCoatedMetal(426, CadmiumTitaniumSolution, 1,
                Materials.TungstenSteel, 9,
                30, 30, 60, Dyes.dyeBlue);

        CadmiumSulfate = new MaterialBuilder(427, TextureSet.SET_DULL, "Cadmium Sulfate")
                .setRGB(150, 150, 50).setColor(Dyes.dyeYellow).addDustItems()
                .setMaterialList(new MaterialStack(Materials.Cadmium, 1), new MaterialStack(Materials.Sulfur, 1),
                        new MaterialStack(Materials.Oxygen, 4))
                .addElectrolyzerRecipe().constructMaterial();
        TitaniumSulfate = new MaterialBuilder(428, TextureSet.SET_DULL, "Titanium Sulfate")
                .setRGB(150, 80, 50).setColor(Dyes.dyePink).addDustItems()
                .setMaterialList(new MaterialStack(Materials.Titanium, 1),
                        new MaterialStack(Materials.Sulfur, 2), new MaterialStack(Materials.Oxygen, 8))
                .addElectrolyzerRecipe().constructMaterial();
        CadmiumTitaniumSulfate = new MaterialBuilder(429, TextureSet.SET_DULL, "Cadmium-Titanium Sulfate")
                .setRGB(130, 65, 50).setColor(Dyes.dyeYellow).addDustItems()
                .setMaterialList(new MaterialStack(CadmiumSulfate, 9),
                        new MaterialStack(TitaniumSulfate, 1))
                .addCentrifugeRecipe().constructMaterial();
    }

    private List<Materials> disableOrePrefixesList = new ArrayList<>();

    private Materials createCoatedMetal(int subID, Materials coating, int coatingAmount, Materials metal,
                                        int metalAmount, int r, int g, int b, Dyes dye) {
        return createCoatedMetal(subID, coating, coatingAmount, metal, metalAmount, r, g, b, dye, Enchantment.silkTouch, 1);
    }

    private Materials createCoatedMetal(int subID, Materials coating, int coatingAmount, Materials metal,
                                        int metalAmount, int r, int g, int b, Dyes dye, Enchantment enchantment, int enchantmentLevel) {
        String name = getCoatedMetalName(coating, metal);
        Materials coatedMetal = new MaterialBuilder(subID, TextureSet.SET_METALLIC, name)
                .setRGB(r, g, b).setColor(dye)
                .addMetalItems().addToolHeadItems().addGearItems()
                .setToolSpeed(metal.mToolSpeed).setDurability(metal.mDurability)
                .setMaterialList(new MaterialStack(coating, coatingAmount),
                        new MaterialStack(metal, metalAmount))
                .constructMaterial();
        coatedMetal.remove(SubTag.SMELTING_TO_FLUID);
        coatedMetal.setEnchantmentForTools(enchantment, enchantmentLevel);
        disableOrePrefixesList.add(coatedMetal);
        return coatedMetal;
    }

    private String getCoatedMetalName(Materials coating, Materials metal) {
        return coating.mDefaultLocalName.split(" ")[0] + " Coated " + metal.mDefaultLocalName;
    }

    @Override
    public void onComponentInit() {
        for (Materials material : disableOrePrefixesList) {
            disableMetalOrePrefixes(material);
        }
    }

    private void disableMetalOrePrefixes(Materials metal) {
        OrePrefixes.dust.disableComponent(metal);
        OrePrefixes.dustSmall.disableComponent(metal);
        OrePrefixes.dustTiny.disableComponent(metal);
        OrePrefixes.nugget.disableComponent(metal);
        OrePrefixes.plateDouble.disableComponent(metal);
        OrePrefixes.screw.disableComponent(metal);
    }

    @Override
    public void onComponentIteration(Materials materials) {

    }
}
