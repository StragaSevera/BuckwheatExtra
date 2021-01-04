package ru.ought.buckwheatextra.tileentities

import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank
import ru.ought.buckwheatextra.tileentities.BW_MetaTileEntity_SuperTank
import gregtech.api.interfaces.ITexture
import gregtech.api.interfaces.tileentity.IGregTechTileEntity
import gregtech.api.enums.Textures
import gregtech.api.objects.GT_RenderedTexture
import ru.ought.buckwheatextra.enums.BWTextures
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumChatFormatting
import gregtech.api.metatileentity.MetaTileEntity

// Big thanks to Spartak for this code
class BW_MetaTileEntity_SuperTank : GT_MetaTileEntity_BasicTank {
    constructor(aID: Int, aName: String?, aNameRegional: String?, aTier: Int) : super(aID,
        aName,
        aNameRegional,
        aTier,
        3,
        String.format("Stores %,d L of fluid", commonSizeCompute(aTier)))

    private constructor (
        aName: String, aTier: Int, aDescription: String,
        aTextures: Array<Array<Array<ITexture?>?>?>,
    ) : super(aName, aTier, 3, aDescription, aTextures)

    constructor(
        aName: String?, aTier: Int, aDescription: Array<String?>?,
        aTextures: Array<Array<Array<ITexture>?>?>?,
    ) : super(aName, aTier, 3, aDescription, aTextures)

    companion object {
        private val commonSizeInBuckets = intArrayOf(250, 1000, 4000, 8000, 16000, 32000)
        private fun commonSizeCompute(tier: Int): Int {
            return if (tier < 0 || tier >= commonSizeInBuckets.size) {
                0
            } else {
                commonSizeInBuckets[tier] * 1000
            }
        }
    }

    override fun getTextureSet(aTextures: Array<ITexture>): Array<Array<Array<ITexture?>?>?> {
        return Array(0) { Array(0) { arrayOfNulls(0) } }
    }

    override fun getTexture(
        aBaseMetaTileEntity: IGregTechTileEntity,
        aSide: Byte, aFacing: Byte, aColorIndex: Byte,
        aActive: Boolean, aRedstone: Boolean,
    ): Array<ITexture> {
        return if (aSide.toInt() == 1)
            arrayOf(Textures.BlockIcons.MACHINE_CASINGS[mTier.toInt()][aColorIndex + 1],
                GT_RenderedTexture(BWTextures.OVERLAY_STANK))
        else
            arrayOf(Textures.BlockIcons.MACHINE_CASINGS[mTier.toInt()][aColorIndex + 1])
    }

    override fun saveNBTData(aNBT: NBTTagCompound) {
        super.saveNBTData(aNBT)
    }

    override fun onRightclick(aBaseMetaTileEntity: IGregTechTileEntity, aPlayer: EntityPlayer): Boolean {
        if (aBaseMetaTileEntity.isClientSide) return true
        aBaseMetaTileEntity.openGUI(aPlayer)
        return true
    }

    override fun isSimpleMachine(): Boolean {
        return true
    }

    override fun isFacingValid(aFacing: Byte): Boolean {
        return true
    }

    override fun isAccessAllowed(aPlayer: EntityPlayer): Boolean {
        return true
    }

    override fun loadNBTData(aNBT: NBTTagCompound) {
        super.loadNBTData(aNBT)
    }

    override fun getUpdateData(): Byte {
        return 0x00
    }

    override fun doesFillContainers(): Boolean {
        return true
    }

    override fun doesEmptyContainers(): Boolean {
        return true
    }

    override fun canTankBeFilled(): Boolean {
        return true
    }

    override fun canTankBeEmptied(): Boolean {
        return true
    }

    override fun displaysItemStack(): Boolean {
        return true
    }

    override fun displaysStackSize(): Boolean {
        return false
    }

    override fun getInfoData(): Array<String> {
        return if (mFluid == null) {
            formatInfoData("No Fluid", 0, capacity)
        } else {
            formatInfoData(mFluid.localizedName, mFluid.amount, capacity)
        }
    }

    private fun formatInfoData(fluidName: String, fluidAmount: Int, fluidCapacity: Int): Array<String> {
        return arrayOf(
            EnumChatFormatting.BLUE.toString() + "Super Tank" + EnumChatFormatting.RESET,
            "Stored Fluid:",
            EnumChatFormatting.GOLD.toString() + fluidName + EnumChatFormatting.RESET,
            EnumChatFormatting.GREEN.toString() + fluidAmount.toString() + " L" +
                    EnumChatFormatting.RESET + " " + EnumChatFormatting.YELLOW +
                    fluidCapacity + " L" + EnumChatFormatting.RESET
        )
    }

    override fun isGivingInformation(): Boolean {
        return true
    }

    @Suppress("DEPRECATION")
    override fun newMetaEntity(aTileEntity: IGregTechTileEntity): MetaTileEntity {
        return BW_MetaTileEntity_SuperTank(mName, mTier.toInt(), mDescription, mTextures)
    }

    override fun getCapacity(): Int {
        return commonSizeCompute(mTier.toInt())
    }

    override fun getTankPressure(): Int {
        return 100
    }
}