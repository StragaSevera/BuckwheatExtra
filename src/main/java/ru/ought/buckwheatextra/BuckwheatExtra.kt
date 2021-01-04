package ru.ought.buckwheatextra

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import ru.ought.buckwheatextra.proxy.CommonProxy

const val MOD_ID = "buckwheatextra"
const val MOD_NAME = "Buckwheat Extra"
const val VERSION = "1.1"

private val Logger = LogManager.getLogger("BuckwheatExtra")
fun log(string: String) {
    Logger.info(string)
}

@Mod(modid = MOD_ID, version = VERSION, name = MOD_NAME,
    dependencies = "required-after:IC2;required-after:gregtech")
object BuckwheatExtra {
    @JvmStatic
    @SidedProxy(clientSide = "ru.ought.buckwheatextra.proxy.ClientProxy",
        serverSide = "ru.ought.buckwheatextra.proxy.CommonProxy")
    lateinit var proxy: CommonProxy

    @JvmStatic
    @Mod.InstanceFactory
    fun instance() = BuckwheatExtra

    @Mod.EventHandler
    fun preInit(e: FMLPreInitializationEvent) {
        proxy.preInit(e)
    }

    @Mod.EventHandler
    fun init(e: FMLInitializationEvent) {
        proxy.init(e)
    }

    @Mod.EventHandler
    fun postInit(e: FMLPostInitializationEvent) {
        proxy.postInit(e)
    }
}