package ru.ought.buckwheatextra.utils

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item

class BWCreativeTab : CreativeTabs("buckwheatExtra") {
    override fun getTabIconItem(): Item {
        return Items.wheat
    }
}