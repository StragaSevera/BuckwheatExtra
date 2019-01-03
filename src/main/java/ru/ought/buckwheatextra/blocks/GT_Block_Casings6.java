package ru.ought.buckwheatextra.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Textures;
import gregtech.api.objects.GT_CopiedBlockTexture;
import gregtech.api.util.GT_LanguageManager;
import gregtech.common.blocks.GT_Block_Casings_Abstract;
import gregtech.common.blocks.GT_Material_Casings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import ru.ought.buckwheatextra.BuckwheatExtra;
import ru.ought.buckwheatextra.enums.BuckwheatItemList;
import ru.ought.buckwheatextra.enums.BuckwheatTextures;

public class GT_Block_Casings6
        extends GT_Block_Casings_Abstract {
    public GT_Block_Casings6() {
        super(GT_Item_Casings6.class, "gt.blockcasings6", GT_Material_Casings.INSTANCE);
        BuckwheatExtra.log("Initialising casings");
        for (int i = 0; i < 16; i = (i + 1)) {
            Textures.BlockIcons.casingTexturePages[1][i + 96] = new GT_CopiedBlockTexture(this, 6, i);
        }
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".0.name", "Hermetic Casing");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".1.name", "Hermetic Casing I");
//        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".2.name", "Hermetic Casing II");
//        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".3.name", "Hermetic Casing III");
//        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".4.name", "Hermetic Casing IV");
//        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".5.name", "Hermetic Casing V");
//        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".6.name", "Hermetic Casing VI");
//        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".7.name", "Hermetic Casing VII");
//        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".8.name", "Hermetic Casing VIII");
//        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".9.name", "Hermetic Casing IX");
//        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".10.name", "Hermetic Casing X");
        BuckwheatItemList.Casing_Tank_0.set(new ItemStack(this, 1, 0));
        BuckwheatItemList.Casing_Tank_1.set(new ItemStack(this, 1, 1));
//        BuckwheatItemList.Casing_Tank_2.set(new ItemStack(this, 1, 2));
//        BuckwheatItemList.Casing_Tank_3.set(new ItemStack(this, 1, 3));
//        BuckwheatItemList.Casing_Tank_4.set(new ItemStack(this, 1, 4));
//        BuckwheatItemList.Casing_Tank_5.set(new ItemStack(this, 1, 5));
//        BuckwheatItemList.Casing_Tank_6.set(new ItemStack(this, 1, 6));
//        BuckwheatItemList.Casing_Tank_7.set(new ItemStack(this, 1, 7));
//        BuckwheatItemList.Casing_Tank_8.set(new ItemStack(this, 1, 8));
//        BuckwheatItemList.Casing_Tank_9.set(new ItemStack(this, 1, 9));
//        BuckwheatItemList.Casing_Tank_10.set(new ItemStack(this, 1, 10));
//        setCreativeTab(BuckwheatExtra.TAB_BUCKWHEAT);
        BuckwheatExtra.log("Test for icon...");
        if (GregTech_API.sGTBlockIconload.contains(BuckwheatTextures.MACHINE_CASING_TANK_DEFAULT)) {
            BuckwheatExtra.log("Icon is loading!");
        } else {
            BuckwheatExtra.log("ICON IS NOT LOADING!!!!!!! ERROR!!!!!");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int aSide, int aMeta) {
        if (aSide == 0) {
            return Textures.BlockIcons.MACHINECASINGS_BOTTOM[aMeta].getIcon();
        } else if (aSide == 1) {
            return Textures.BlockIcons.MACHINECASINGS_TOP[aMeta].getIcon();
        } else if ((aMeta >= 1) && (aMeta <= 15)) {
            return BuckwheatTextures.MACHINE_CASING_TANK_ARRAY[aMeta].getIcon();
        } else {
            return BuckwheatTextures.MACHINE_CASING_TANK_DEFAULT.getIcon();
        }
    }

    public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {
        return gregtech.api.enums.Dyes.MACHINE_METAL.mRGBa[0] << 16 | gregtech.api.enums.Dyes.MACHINE_METAL.mRGBa[1] << 8 | gregtech.api.enums.Dyes.MACHINE_METAL.mRGBa[2];
    }
}
