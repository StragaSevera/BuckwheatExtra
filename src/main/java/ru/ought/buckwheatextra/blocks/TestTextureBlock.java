package ru.ought.buckwheatextra.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import ru.ought.buckwheatextra.BuckwheatExtra;
import ru.ought.buckwheatextra.enums.BuckwheatTextures;

public class TestTextureBlock extends Block {
    public IIcon icon;
    public TestTextureBlock() {
        super(Material.rock);
        setBlockName(BuckwheatExtra.MOD_ID + "_testtextureblock");
        setCreativeTab(BuckwheatExtra.TAB_BUCKWHEAT);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icon = register.registerIcon("buckwheatextra:MACHINE_CASING_TANK");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int aSide, int aMeta) {
//        return BuckwheatTextures.MACHINE_CASING_TANK_DEFAULT.getIcon();
        return icon;
    }
}
