package ru.ought.buckwheatextra.enums;

import gregtech.api.enums.Dyes;
import gregtech.api.enums.MaterialBuilder;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TextureSet;
import gregtech.api.interfaces.IMaterialHandler;
import gregtech.api.objects.MaterialStack;
import ru.ought.buckwheatextra.BuckwheatExtra;


public class BuckwheatMaterials implements IMaterialHandler {
    public static Materials CadmiumSolution;
    @Override
    public void onMaterialsInit() {
        CadmiumSolution = new MaterialBuilder(-1, TextureSet.SET_FLUID, "Cadmium Solution")
                .addCell().addFluid().setRGB(48, 47, 47).setColor(Dyes.dyeBlack)
                .setMaterialList(new MaterialStack(Materials.Cadmium, 1)).addElectrolyzerRecipe()
                .constructMaterial();
    }   

    @Override
    public void onComponentInit() {
        OrePrefixes.cell.enableComponent(CadmiumSolution);
    }

    @Override
    public void onComponentIteration(Materials materials) {

    }
}
