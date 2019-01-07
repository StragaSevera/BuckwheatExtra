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
import ru.ought.buckwheatextra.utils.RomanNumber;

public class Buckwheat_Block_CasingsSuperPressure extends GT_Block_Casings_Abstract {
    
    // TODO: Implement casings 5+ tier
    private static final int MAX_TIER = 4;

    public Buckwheat_Block_CasingsSuperPressure() {
        super(GT_Item_CasingsSuperPressure.class, "buckwheat.blockcasingssuperpressure", GT_Material_Casings.INSTANCE);
        for (int i = 0; i < 16; i = (i + 1)) {
            Textures.BlockIcons.casingTexturePages[1][i + 96] = new GT_CopiedBlockTexture(this, 6, i);
        }

        for (int i = 0; i < MAX_TIER; i++) {
            GT_LanguageManager.addStringLocalization(getUnlocalizedName() + "." + i + ".name",
                    "Super Pressure Casing " + RomanNumber.toRoman(i).trim());
            BuckwheatItemList.CasingsSuperpressure[i].set(new ItemStack(this, 1, i));
        }

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
