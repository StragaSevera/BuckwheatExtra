package ru.ought.buckwheatextra

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import gregtech.api.GregTech_API
import net.minecraft.creativetab.CreativeTabs
import org.apache.logging.log4j.LogManager
import ru.ought.buckwheatextra.loaders.Preloader
import ru.ought.buckwheatextra.proxy.CommonProxy
import ru.ought.buckwheatextra.utils.BWCreativeTab

const val MOD_ID = "buckwheatextra"
const val MOD_NAME = "Buckwheat Extra"
const val VERSION = "1.1"

val TAB_BUCKWHEAT: CreativeTabs = BWCreativeTab()

private val Logger = LogManager.getLogger("BuckwheatExtra")
fun log(string: String) {
    Logger.info(string)
}

@Mod(modid = MOD_ID, version = VERSION, name = MOD_NAME,
    dependencies = "")
//    dependencies = "required-after:IC2;required-after:gregtech")
object BuckwheatExtra {
    @JvmStatic
    @SidedProxy(clientSide = "ru.ought.buckwheatextra.proxy.ClientProxy",
        serverSide = "ru.ought.buckwheatextra.proxy.CommonProxy")
    lateinit var proxy: CommonProxy

    @JvmStatic
    @Mod.InstanceFactory
    fun instance() = BuckwheatExtra

    init {
        GregTech_API.sAfterGTPreload.add(Preloader())
    }

    @Mod.EventHandler
    fun preInitialize(e: FMLPreInitializationEvent) {
        proxy.preInit(e)
    }

    @Mod.EventHandler
    fun initialize(e: FMLInitializationEvent) {
        proxy.init(e)
    }

    @Mod.EventHandler
    fun postInitialize(e: FMLPostInitializationEvent) {
        proxy.postInit(e)
    }
}