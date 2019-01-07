package ru.ought.buckwheatextra.tileentities;

import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtech.api.objects.GT_RenderedTexture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import ru.ought.buckwheatextra.enums.BWTextures;

// Big thanks to Spartak for this code
public class BW_MetaTileEntity_SuperTank extends GT_MetaTileEntity_BasicTank {
    public BW_MetaTileEntity_SuperTank(int aID, String aName, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier, 3,
                String.format("Stores %,d L of fluid", commonSizeCompute(aTier)));
    }

    private BW_MetaTileEntity_SuperTank(String aName, int aTier, String aDescription,
                                        ITexture[][][] aTextures) {
        super(aName, aTier, 3, aDescription, aTextures);
    }

    public BW_MetaTileEntity_SuperTank(String aName, int aTier, String[] aDescription,
                                       ITexture[][][] aTextures) {
        super(aName, aTier, 3, aDescription, aTextures);
    }

    @Override
    public ITexture[][][] getTextureSet(ITexture[] aTextures) {
        return new ITexture[0][0][0];
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity,
                                 byte aSide, byte aFacing, byte aColorIndex,
                                 boolean aActive, boolean aRedstone) {
        if (aSide == 1)
            return new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[mTier][aColorIndex + 1],
                    new GT_RenderedTexture(BWTextures.OVERLAY_STANK)};
        else return new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[mTier][aColorIndex + 1]};
    }


    @Override
    public void saveNBTData(NBTTagCompound aNBT) {
        super.saveNBTData(aNBT);
    }

    @Override
    public boolean onRightclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer) {
        if (aBaseMetaTileEntity.isClientSide()) return true;
        aBaseMetaTileEntity.openGUI(aPlayer);
        return true;
    }

    @Override
    public boolean isSimpleMachine() {
        return true;
    }

    @Override
    public boolean isFacingValid(byte aFacing) {
        return true;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public void loadNBTData(NBTTagCompound aNBT) {
        super.loadNBTData(aNBT);
    }

    @Override
    public final byte getUpdateData() {
        return 0x00;
    }

    @Override
    public boolean doesFillContainers() {
        return true;
    }

    @Override
    public boolean doesEmptyContainers() {
        return true;
    }

    @Override
    public boolean canTankBeFilled() {
        return true;
    }

    @Override
    public boolean canTankBeEmptied() {
        return true;
    }

    @Override
    public boolean displaysItemStack() {
        return true;
    }

    @Override
    public boolean displaysStackSize() {
        return false;
    }

    @Override
    public String[] getInfoData() {
        if (mFluid == null) {
            return formatInfoData("No Fluid", 0, getCapacity());
        } else {
            return formatInfoData(mFluid.getLocalizedName(), mFluid.amount, getCapacity());
        }
    }

    private String[] formatInfoData(String fluidName, int fluidAmount, int fluidCapacity) {
        return new String[]{
                EnumChatFormatting.BLUE + "Super Tank" + EnumChatFormatting.RESET,
                "Stored Fluid:",
                EnumChatFormatting.GOLD + fluidName + EnumChatFormatting.RESET,
                EnumChatFormatting.GREEN + Integer.toString(fluidAmount) + " L" +
                        EnumChatFormatting.RESET + " " + EnumChatFormatting.YELLOW +
                        fluidCapacity + " L" + EnumChatFormatting.RESET
        };
    }

    @Override
    public boolean isGivingInformation() {
        return true;
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        //noinspection deprecation
        return new BW_MetaTileEntity_SuperTank(mName, mTier, mDescription, mTextures);
    }

    private static final int[] commonSizes = {250, 1000, 4000, 8000, 16000, 32000};

    private static int commonSizeCompute(int tier) {
        if ((tier < 0) || (tier >= commonSizes.length)) {
            return 0;
        } else {
            return commonSizes[tier] * 1000;
        }
    }

    @Override
    public int getCapacity() {
        return commonSizeCompute(mTier);
    }

    @Override
    public int getTankPressure() {
        return 100;
    }
}
