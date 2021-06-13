package ru.ought.buckwheatextra.enums

import gregtech.api.enums.GT_Values
import gregtech.api.interfaces.IItemContainer
import gregtech.api.util.GT_ModHandler
import gregtech.api.util.GT_OreDictUnificator
import gregtech.api.util.GT_Utility
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

// Copied from Gregtech, because there is no way to dynamically add items to enum
enum class BWItemList : IItemContainer {
    Super_Tank_ULV, Super_Tank_LV, Super_Tank_MV, Super_Tank_HV, Super_Tank_EV, Super_Tank_IV, Casing_Superpressure_0, Casing_Superpressure_1, Casing_Superpressure_2, Casing_Superpressure_3, Casing_Superpressure_4, Casing_Superpressure_5;

    companion object {
        val Casings_Superpressure = arrayOf(Casing_Superpressure_0,
            Casing_Superpressure_1,
            Casing_Superpressure_2,
            Casing_Superpressure_3,
            Casing_Superpressure_4,
            Casing_Superpressure_5)
        val Super_Tanks =
            arrayOf(Super_Tank_ULV, Super_Tank_LV, Super_Tank_MV, Super_Tank_HV, Super_Tank_EV, Super_Tank_IV)
    }

    private var _stack: ItemStack? = null
    private var _hasNotBeenSet = true
    override fun set(item: Item?): IItemContainer {
        _hasNotBeenSet = false
        if (item == null) return this
        val stack = ItemStack(item, 1, 0)
        _stack = GT_Utility.copyAmount(1, stack)
        return this
    }

    override fun set(aStack: ItemStack?): IItemContainer {
        _hasNotBeenSet = false
        _stack = GT_Utility.copyAmount(1, aStack)
        return this
    }

    override fun getItem(): Item? {
        if (_hasNotBeenSet) throw IllegalAccessError("The Enum '$name' has not been set to an Item at this time!")
        return if (GT_Utility.isStackInvalid(_stack)) null else _stack!!.item
    }

    override fun getBlock(): Block {
        if (_hasNotBeenSet) throw IllegalAccessError("The Enum '$name' has not been set to an Item at this time!")
        return GT_Utility.getBlockFromItem(item)
    }

    override fun hasBeenSet(): Boolean {
        return !_hasNotBeenSet
    }

    override fun isStackEqual(aStack: Any?): Boolean {
        return isStackEqual(aStack, false, false)
    }

    override fun isStackEqual(aStack: Any?, aWildcard: Boolean, aIgnoreNBT: Boolean): Boolean {
        return if (GT_Utility.isStackInvalid(aStack)) false else GT_Utility.areUnificationsEqual(aStack as ItemStack?,
            if (aWildcard) getWildcard(1) else get(1),
            aIgnoreNBT)
    }

    override fun get(aAmount: Long, vararg aReplacements: Any?): ItemStack {
        if (_hasNotBeenSet) throw IllegalAccessError("The Enum '$name' has not been set to an Item at this time!")
        return if (GT_Utility.isStackInvalid(_stack)) GT_Utility.copyAmount(aAmount,
            *aReplacements) else GT_Utility.copyAmount(aAmount, GT_OreDictUnificator.get(_stack))
    }

    override fun getWildcard(aAmount: Long, vararg aReplacements: Any): ItemStack {
        return getItemStack(aAmount, GT_Values.W.toLong(), aReplacements)
    }

    override fun getWithDamage(aAmount: Long, aMetaValue: Long, vararg aReplacements: Any): ItemStack {
        return getItemStack(aAmount, aMetaValue, aReplacements)
    }

    private fun getItemStack(aAmount: Long, w: Long, vararg aReplacements: Any): ItemStack {
        if (_hasNotBeenSet) throw IllegalAccessError("The Enum '$name' has not been set to an Item at this time!")
        return if (GT_Utility.isStackInvalid(_stack)) GT_Utility.copyAmount(aAmount,
            *aReplacements) else GT_Utility.copyAmountAndMetaData(aAmount, w, GT_OreDictUnificator.get(_stack))
    }

    override fun getUndamaged(aAmount: Long, vararg aReplacements: Any?): ItemStack {
        if (_hasNotBeenSet) throw IllegalAccessError("The Enum '$name' has not been set to an Item at this time!")
        return if (GT_Utility.isStackInvalid(_stack)) GT_Utility.copyAmount(aAmount,
            *aReplacements) else GT_Utility.copyAmountAndMetaData(aAmount, 0, GT_OreDictUnificator.get(_stack))
    }

    override fun getAlmostBroken(aAmount: Long, vararg aReplacements: Any?): ItemStack {
        if (_hasNotBeenSet) throw IllegalAccessError("The Enum '$name' has not been set to an Item at this time!")
        return if (GT_Utility.isStackInvalid(_stack)) GT_Utility.copyAmount(aAmount,
            *aReplacements) else GT_Utility.copyAmountAndMetaData(aAmount,
            (_stack!!.maxDamage - 1).toLong(),
            GT_OreDictUnificator.get(_stack))
    }

    override fun getWithName(aAmount: Long, aDisplayName: String?, vararg aReplacements: Any?): ItemStack? {
        val rStack = get(1, *aReplacements)
        if (GT_Utility.isStackInvalid(rStack)) return null
        rStack.setStackDisplayName(aDisplayName)
        return GT_Utility.copyAmount(aAmount, rStack)
    }

    override fun getWithCharge(aAmount: Long, aEnergy: Int, vararg aReplacements: Any?): ItemStack? {
        val rStack = get(1, *aReplacements)
        if (GT_Utility.isStackInvalid(rStack)) return null
        GT_ModHandler.chargeElectricItem(rStack, aEnergy, Int.MAX_VALUE, true, false)
        return GT_Utility.copyAmount(aAmount, rStack)
    }

    override fun registerOre(vararg aOreNames: Any?): IItemContainer {
        if (_hasNotBeenSet) throw IllegalAccessError("The Enum '$name' has not been set to an Item at this time!")
        for (tOreName in aOreNames) GT_OreDictUnificator.registerOre(tOreName, get(1))
        return this
    }

    override fun registerWildcardAsOre(vararg aOreNames: Any?): IItemContainer {
        if (_hasNotBeenSet) throw IllegalAccessError("The Enum '$name' has not been set to an Item at this time!")
        for (tOreName in aOreNames) GT_OreDictUnificator.registerOre(tOreName, getWildcard(1))
        return this
    }
}