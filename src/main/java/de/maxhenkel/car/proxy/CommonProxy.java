package de.maxhenkel.car.proxy;

import java.io.File;
import de.maxhenkel.car.Config;
import de.maxhenkel.car.Main;
import de.maxhenkel.car.entity.car.EntityCarBigWood;
import de.maxhenkel.car.entity.car.EntityCarSport;
import de.maxhenkel.car.entity.car.EntityCarTransporter;
import de.maxhenkel.car.entity.car.EntityCarWood;
import de.maxhenkel.car.events.ConfigEvents;
import de.maxhenkel.car.gui.GuiHandler;
import de.maxhenkel.car.net.MessageCarGui;
import de.maxhenkel.car.net.MessageCarHorn;
import de.maxhenkel.car.net.MessageControlCar;
import de.maxhenkel.car.net.MessageCrash;
import de.maxhenkel.car.net.MessageEditSign;
import de.maxhenkel.car.net.MessageOpenGui;
import de.maxhenkel.car.net.MessagePlaySoundLoop;
import de.maxhenkel.car.net.MessageRepairCar;
import de.maxhenkel.car.net.MessageSpawnCar;
import de.maxhenkel.car.net.MessageStartCar;
import de.maxhenkel.car.net.MessageStartFuel;
import de.maxhenkel.car.net.MessageSyncConfig;
import de.maxhenkel.car.net.MessageSyncTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {

	public static SimpleNetworkWrapper simpleNetworkWrapper;
	
	static {
		FluidRegistry.enableUniversalBucket();
	}
	
	public void preinit(FMLPreInitializationEvent event) {
		CommonProxy.simpleNetworkWrapper=NetworkRegistry.INSTANCE.newSimpleChannel(Main.MODID);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageControlCar.class, MessageControlCar.class, 0, Side.SERVER);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageCarGui.class, MessageCarGui.class, 1, Side.SERVER);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageStartCar.class, MessageStartCar.class, 2, Side.SERVER);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageCrash.class, MessageCrash.class, 3, Side.SERVER);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageStartFuel.class, MessageStartFuel.class, 4, Side.SERVER);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessagePlaySoundLoop.class, MessagePlaySoundLoop.class, 5, Side.CLIENT);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageSyncTileEntity.class, MessageSyncTileEntity.class, 6, Side.CLIENT);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageSpawnCar.class, MessageSpawnCar.class, 7, Side.SERVER);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageOpenGui.class, MessageOpenGui.class, 8, Side.SERVER);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageRepairCar.class, MessageRepairCar.class, 9, Side.SERVER);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageCarHorn.class, MessageCarHorn.class, 10, Side.SERVER);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageSyncConfig.class, MessageSyncConfig.class, 11, Side.CLIENT);
		CommonProxy.simpleNetworkWrapper.registerMessage(MessageEditSign.class, MessageEditSign.class, 12, Side.SERVER);

		try {
			File configFolder=new File(event.getModConfigurationDirectory(), Main.MODID);
			configFolder.mkdirs();
			Config.init(configFolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init(FMLInitializationEvent event) {
		
		EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID, "car_wood"), EntityCarWood.class,
				"car_wood", 3723, Main.instance(), 64, 1, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID, "car_big_wood"), EntityCarBigWood.class,
				"car_big_wood", 3724, Main.instance(), 64, 1, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID, "car_transporter"), EntityCarTransporter.class,
				"car_transporter", 3725, Main.instance(), 64, 1, true);
		
		EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID, "car_sport"), EntityCarSport.class,
				"car_sport", 3726, Main.instance(), 64, 1, true);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance(), new GuiHandler());
		
		MinecraftForge.EVENT_BUS.register(new ConfigEvents());
	}

	public void postinit(FMLPostInitializationEvent event) {
		Config.postInit();
	}
	
	

}
