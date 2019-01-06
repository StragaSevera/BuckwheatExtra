package ru.ought.buckwheatextra.enums;

import gregtech.api.enums.Dyes;
import gregtech.api.enums.MaterialBuilder;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TextureSet;
import gregtech.api.interfaces.IMaterialHandler;
import gregtech.api.objects.MaterialStack;
import net.minecraft.enchantment.Enchantment;
import ru.ought.buckwheatextra.BuckwheatExtra;


public class BuckwheatMaterials implements IMaterialHandler {
    public static Materials CadmiumSolution;
    public static Materials CadmiumCoatedSteel;

    @Override
    public void onMaterialsInit() {
        CadmiumSolution = new MaterialBuilder(380, TextureSet.SET_FLUID, "Cadmium Solution")
                .addCell().addFluid().setRGB(48, 47, 47).setColor(Dyes.dyeBlack)
                .setMaterialList(new MaterialStack(Materials.Cadmium, 1))
                .constructMaterial();
        CadmiumCoatedSteel = new MaterialBuilder(381, TextureSet.SET_METALLIC, "Cadmium coated " +
                "Steel").addMetalItems().addToolHeadItems().addGearItems()
                .setToolSpeed(6.0f).setDurability(512).setMeltingPoint(0)
                .setRGB(48, 47, 47).setColor(Dyes.dyeBlack)
                .setMaterialList(new MaterialStack(Materials.Cadmium, 1),
                        new MaterialStack(Materials.Steel, 1))
                .constructMaterial();
        CadmiumCoatedSteel.setEnchantmentForTools(Enchantment.silkTouch, 1);
    }

    @Override
    public void onComponentInit() {
        OrePrefixes.dust.disableComponent(CadmiumCoatedSteel);
        OrePrefixes.dustSmall.disableComponent(CadmiumCoatedSteel);
        OrePrefixes.dustTiny.disableComponent(CadmiumCoatedSteel);
        OrePrefixes.nugget.disableComponent(CadmiumCoatedSteel);
        OrePrefixes.plateDouble.disableComponent(CadmiumCoatedSteel);
        OrePrefixes.screw.disableComponent(CadmiumCoatedSteel);
    }

    @Override
    public void onComponentIteration(Materials materials) {

    }
}
