package ru.ought.buckwheatextra.proxy

import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import ru.ought.buckwheatextra.log

open class CommonProxy {
    fun preInit(e: FMLPreInitializationEvent) {

    }

    fun init(e: FMLInitializationEvent) {
        log("Hurray! BUCKWHEAT!")
    }

    fun postInit(e: FMLPostInitializationEvent) {

    }
}