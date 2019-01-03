package ru.ought.buckwheatextra.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BuckwheatCreativeTab extends CreativeTabs {
    public BuckwheatCreativeTab() {
        super("buckwheatExtra");
    }

    @Override
    public Item getTabIconItem() {
        return Items.wheat;
    }
}
