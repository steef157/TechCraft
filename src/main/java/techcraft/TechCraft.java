package techcraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import techcraft.init.ModBlocks;
import techcraft.proxy.IProxy;
import techcraft.util.Reference;

/**
 * Created by stefa on 31-3-2016.
 */
@Mod(modid = Reference.MOD_ID)
public class TechCraft {

    @Instance
    public static TechCraft instance;
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static IProxy proxy;
    
    @EventHandler
    public static void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }
    
    @EventHandler
    public static void init(FMLInitializationEvent event){
        proxy.init(event);
    }
    
    @EventHandler
    public static void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
    
}
