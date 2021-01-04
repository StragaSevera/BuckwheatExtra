package ru.ought.buckwheatextra.blocks

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import gregtech.api.enums.Dyes
import gregtech.api.enums.Textures
import gregtech.api.objects.GT_CopiedBlockTexture
import gregtech.api.util.GT_LanguageManager
import gregtech.common.blocks.GT_Block_Casings_Abstract
import gregtech.common.blocks.GT_Material_Casings
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import ru.ought.buckwheatextra.TAB_BUCKWHEAT
import ru.ought.buckwheatextra.enums.BWItemList
import ru.ought.buckwheatextra.enums.BWTextures
import ru.ought.buckwheatextra.utils.toRoman

class Block_CasingsSuperpressure : GT_Block_Casings_Abstract(
    Item_CasingsSuperpressure::class.java,
    "buckwheat.blockcasingssuperpressure",
    GT_Material_Casings.INSTANCE
) {
    companion object {
        // TODO: Implement tiers 5+ for casings
        const val MAX_TIER = 5
    }

    init {
        for (i in 0..16) {
            Textures.BlockIcons.casingTexturePages[1][i + 96] = GT_CopiedBlockTexture(this, 6, i)
        }
        for (i in 0..MAX_TIER) {
            GT_LanguageManager.addStringLocalization("$unlocalizedName.$i.name",
                "Super Pressure Casing " + toRoman(i).trim())
            BWItemList.Casings_Superpressure[i].set(ItemStack(this, 1, i))
        }
        setCreativeTab(TAB_BUCKWHEAT)
    }

    @SideOnly(Side.CLIENT)
    override fun getIcon(side: Int, meta: Int): IIcon? {
        return when {
            side == 0 -> Textures.BlockIcons.MACHINECASINGS_BOTTOM[meta].icon
            side == 1 -> Textures.BlockIcons.MACHINECASINGS_TOP[meta].icon
            meta in 0..15 -> BWTextures.MACHINE_CASING_SUPERPRESSURE_ARRAY[meta].icon
            else -> BWTextures.MACHINE_CASING_SUPREPRESSURE_DEFAULT.icon
        }
    }

    override fun colorMultiplier(world: IBlockAccess?, x: Int, y: Int, z: Int): Int {
        return Dyes.MACHINE_METAL.mRGBa[0].toInt() shl 16 or (Dyes.MACHINE_METAL.mRGBa[1].toInt() shl 8) or Dyes.MACHINE_METAL.mRGBa[2].toInt()
    }
}