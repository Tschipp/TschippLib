package tschipp.tschipplib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tschipp.tschipplib.network.CTileEntitySyncPacket;
import tschipp.tschipplib.network.CTileEntitySyncPacketHandler;
import tschipp.tschipplib.network.STileEntitySyncPacket;
import tschipp.tschipplib.network.STileEntitySyncPacketHandler;

@Mod(modid = TschippLib.MODID, name = "TschippLib", version = TschippLib.VERSION, acceptableRemoteVersions = "[1.1.4,)")
public class TschippLib {
	
	public static final String MODID = "tschipplib";
	public static final String VERSION = "1.1.5";
	
	@Instance(value = MODID)
	public static TschippLib instance;
	
	public static final String CLIENT_PROXY = "tschipp.tschipplib.ClientProxy";
	public static final String COMMON_PROXY = "tschipp.tschipplib.CommonProxy";

	public static Logger LOGGER = LogManager.getFormatterLogger("TschippLib");
	
	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper network;

	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("TschippLib");
		network.registerMessage(CTileEntitySyncPacketHandler.class, CTileEntitySyncPacket.class, 0, Side.CLIENT);
		network.registerMessage(STileEntitySyncPacketHandler.class, STileEntitySyncPacket.class, 1, Side.SERVER);
	}



	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}



	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);

	}

}
