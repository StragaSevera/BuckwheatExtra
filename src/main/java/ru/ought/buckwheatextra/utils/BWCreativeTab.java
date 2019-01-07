package ru.ought.buckwheatextra.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BWCreativeTab extends CreativeTabs {
    public BWCreativeTab() {
        super("buckwheatExtra");
    }

    @Override
    public Item getTabIconItem() {
        return Items.wheat;
    }
}
