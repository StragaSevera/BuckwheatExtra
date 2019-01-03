package ru.ought.buckwheatextra;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTech_API;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.ought.buckwheatextra.blocks.TestTextureBlock;
import ru.ought.buckwheatextra.enums.BuckwheatTextures;
import ru.ought.buckwheatextra.loaders.BuckwheatPreloader;
import ru.ought.buckwheatextra.utils.BuckwheatCreativeTab;

@Mod(
        modid = BuckwheatExtra.MOD_ID,
        name = BuckwheatExtra.MOD_NAME,
        version = BuckwheatExtra.VERSION,
        dependencies = "required-after:IC2;required-after:gregtech"
)
public class BuckwheatExtra {
    public static final String MOD_ID = "buckwheatextra";
    static final String MOD_NAME = "Buckwheat Extra";
    static final String VERSION = "1.0";
    public static final CreativeTabs TAB_BUCKWHEAT = new BuckwheatCreativeTab();

    private static final Logger Logger = LogManager.getLogger("ru.ought.buckwheatextra");

    public BuckwheatExtra() {
        BuckwheatPreloader preloader = new BuckwheatPreloader();
        GregTech_API.sAfterGTPreload.add(preloader);
        if (GregTech_API.sAfterGTPreload.contains(preloader)) {
            log("Successfully added preloader!");
        }
    }

    public static void log(String string) {
        Logger.info("Buckwheat Log: " + string);
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
        TestTextureBlock = new TestTextureBlock();
        GameRegistry.registerBlock(TestTextureBlock, "Test Texture Block");
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
