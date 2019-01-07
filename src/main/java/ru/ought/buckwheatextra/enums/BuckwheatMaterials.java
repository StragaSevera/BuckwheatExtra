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


public class BuckwheatMaterials implements IMaterialHandler {
    public static Materials CadmiumSolution;
    public static Materials CadmiumTitaniumSolution;
    public static Materials CadmiumCoatedSteel;
    public static Materials CadmiumCoatedBlackSteel;
    public static Materials CadmiumCoatedRedSteel;
    public static Materials CadmiumTitaniumCoatedRedSteel;

    @Override
    public void onMaterialsInit() {
        CadmiumSolution = new MaterialBuilder(379, TextureSet.SET_FLUID, "Cadmium Solution")
                .setRGB(48, 47, 47).setColor(Dyes.dyeBlack).addCell().addFluid()
                .setMaterialList(new MaterialStack(Materials.Cadmium, 1))
                .constructMaterial();
        CadmiumTitaniumSolution = new MaterialBuilder(380, TextureSet.SET_FLUID, "Cadmium-Titanium Solution")
                .setRGB(80, 47, 80).setColor(Dyes.dyePurple).addCell().addFluid()
                .setMaterialList(new MaterialStack(Materials.Cadmium, 9), new MaterialStack(Materials.Titanium, 1))
                .constructMaterial();

        CadmiumCoatedSteel = createCoatedMetal(381, CadmiumSolution, 1, Materials.Steel, 1, 60, 60, 60, Dyes.dyeBlack);
        CadmiumCoatedBlackSteel = createCoatedMetal(382, CadmiumSolution, 1, Materials.BlackSteel, 1, 20, 20, 20,
                Dyes.dyeBlack);
        CadmiumCoatedRedSteel = createCoatedMetal(383, CadmiumSolution, 1, Materials.RedSteel, 1, 60, 20, 20,
                Dyes.dyeRed);
        CadmiumTitaniumCoatedRedSteel = createCoatedMetal(384, CadmiumTitaniumSolution, 1, Materials.RedSteel, 9, 80,
                30, 80, Dyes.dyePurple);

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
