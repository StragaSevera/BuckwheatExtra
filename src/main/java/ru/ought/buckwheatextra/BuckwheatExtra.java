package ru.ought.buckwheatextra;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ought.buckwheatextra.enums.BWMaterials;
import ru.ought.buckwheatextra.loaders.BWPostloader;
import ru.ought.buckwheatextra.loaders.BWPreloader;
import ru.ought.buckwheatextra.utils.BWCreativeTab;

@Mod(
        modid = BuckwheatExtra.MOD_ID,
        name = BuckwheatExtra.MOD_NAME,
        version = BuckwheatExtra.VERSION,
        dependencies = "required-after:IC2;required-after:gregtech"
)
public class BuckwheatExtra {
    @SuppressWarnings("WeakerAccess")
    public static final String MOD_ID = "buckwheatextra";
    static final String MOD_NAME = "Buckwheat Extra";
    static final String VERSION = "1.0";
    public static final CreativeTabs TAB_BUCKWHEAT = new BWCreativeTab();

    private static final Logger Logger = LogManager.getLogger("ru.ought.buckwheatextra");

    public BuckwheatExtra() {
        GregTech_API.sAfterGTPreload.add(new BWPreloader());
        GregTech_API.sAfterGTPostload.add(new BWPostloader());
        Materials.add(new BWMaterials());
    }

    public static void log(String string) {
        Logger.info("Buckwheat bLog: " + string);
    }

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static BuckwheatExtra INSTANCE;
    
    public static Block TestTextureBlock;
    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        log("Hurray! BUCKWHEAT!");
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
    }
}
