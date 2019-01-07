package ru.ought.buckwheatextra.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

public class Buckwheat_Block_CasingsSuperPressure
        extends GT_Block_Casings_Abstract {
    public Buckwheat_Block_CasingsSuperPressure() {
        super(GT_Item_CasingsSuperPressure.class, "buckwheat.blockcasingssuperpressure", GT_Material_Casings.INSTANCE);
        for (int i = 0; i < 16; i = (i + 1)) {
            Textures.BlockIcons.casingTexturePages[1][i + 96] = new GT_CopiedBlockTexture(this, 6, i);
        }

        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".0.name", "Super Pressure Casing");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".1.name", "Super Pressure Casing I");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".2.name", "Super Pressure Casing II");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".3.name", "Super Pressure Casing III");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".4.name", "Super Pressure Casing IV");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".5.name", "Super Pressure Casing V");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".6.name", "Super Pressure Casing VI");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".7.name", "Super Pressure Casing VII");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".8.name", "Super Pressure Casing VIII");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".9.name", "Super Pressure Casing IX");
        GT_LanguageManager.addStringLocalization(getUnlocalizedName() + ".10.name", "Super Pressure Casing X");
        BuckwheatItemList.Casing_Tank_0.set(new ItemStack(this, 1, 0));
        BuckwheatItemList.Casing_Tank_1.set(new ItemStack(this, 1, 1));
        BuckwheatItemList.Casing_Tank_2.set(new ItemStack(this, 1, 2));
        BuckwheatItemList.Casing_Tank_3.set(new ItemStack(this, 1, 3));
        BuckwheatItemList.Casing_Tank_4.set(new ItemStack(this, 1, 4));
        // TODO: Invent recipes for casings 5+ tier
//        BuckwheatItemList.Casing_Tank_5.set(new ItemStack(this, 1, 5));
//        BuckwheatItemList.Casing_Tank_6.set(new ItemStack(this, 1, 6));
//        BuckwheatItemList.Casing_Tank_7.set(new ItemStack(this, 1, 7));
//        BuckwheatItemList.Casing_Tank_8.set(new ItemStack(this, 1, 8));
//        BuckwheatItemList.Casing_Tank_9.set(new ItemStack(this, 1, 9));
//        BuckwheatItemList.Casing_Tank_10.set(new ItemStack(this, 1, 10));
        setCreativeTab(BuckwheatExtra.TAB_BUCKWHEAT);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int aSide, int aMeta) {
        if (aSide == 0) {
            return Textures.BlockIcons.MACHINECASINGS_BOTTOM[aMeta].getIcon();
        } else if (aSide == 1) {
            return Textures.BlockIcons.MACHINECASINGS_TOP[aMeta].getIcon();
        } else if ((aMeta >= 0) && (aMeta <= 15)) {
            return BuckwheatTextures.MACHINE_CASING_SUPERPRESSURE_ARRAY[aMeta].getIcon();
        } else {
            return BuckwheatTextures.MACHINE_CASING_SUPREPRESSURE_DEFAULT.getIcon();
        }
    }

    public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {
        return gregtech.api.enums.Dyes.MACHINE_METAL.mRGBa[0] << 16 | gregtech.api.enums.Dyes.MACHINE_METAL.mRGBa[1] << 8 | gregtech.api.enums.Dyes.MACHINE_METAL.mRGBa[2];
    }
}
