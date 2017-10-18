package de.maxhenkel.car.proxy;

import de.maxhenkel.car.Config;
import de.maxhenkel.car.blocks.tileentity.TileEntityFuelStation;
import de.maxhenkel.car.blocks.tileentity.TileEntitySign;
import de.maxhenkel.car.blocks.tileentity.TileEntitySplitTank;
import de.maxhenkel.car.blocks.tileentity.TileEntityTank;
import de.maxhenkel.car.blocks.tileentity.render.TileEntitySpecialRendererSign;
import de.maxhenkel.car.blocks.tileentity.render.TileEntitySpecialRendererSplitTank;
import de.maxhenkel.car.blocks.tileentity.render.TileEntitySpecialRendererTank;
import de.maxhenkel.car.blocks.tileentity.render.TileentitySpecialRendererFuelStation;
import de.maxhenkel.car.entity.car.EntityCarBigWood;
import de.maxhenkel.car.entity.car.EntityCarSport;
import de.maxhenkel.car.entity.car.EntityCarTransporter;
import de.maxhenkel.car.entity.car.EntityCarWood;
import de.maxhenkel.car.entity.model.bigwood.RenderFactoryBigWoodCar;
import de.maxhenkel.car.entity.model.sport.RenderFactorySport;
import de.maxhenkel.car.entity.model.transporter.RenderFactoryTransporter;
import de.maxhenkel.car.entity.model.wood.RenderFactoryWoodCar;
import de.maxhenkel.car.events.DynamicLightEvents;
import de.maxhenkel.car.events.KeyEvents;
import de.maxhenkel.car.events.PlayerEvents;
import de.maxhenkel.car.events.RenderEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	public void preinit(FMLPreInitializationEvent event) {
		super.preinit(event);

		RenderingRegistry.registerEntityRenderingHandler(EntityCarWood.class, new RenderFactoryWoodCar());
		RenderingRegistry.registerEntityRenderingHandler(EntityCarBigWood.class, new RenderFactoryBigWoodCar());
		RenderingRegistry.registerEntityRenderingHandler(EntityCarTransporter.class, new RenderFactoryTransporter());
		RenderingRegistry.registerEntityRenderingHandler(EntityCarSport.class, new RenderFactorySport());

	}

	public void init(FMLInitializationEvent event) {
		super.init(event);
		MinecraftForge.EVENT_BUS.register(new KeyEvents());
		MinecraftForge.EVENT_BUS.register(new RenderEvents());
		MinecraftForge.EVENT_BUS.register(new PlayerEvents());

		if (Config.dynamicLights && Loader.isModLoaded("dynamiclights")) {
			MinecraftForge.EVENT_BUS.register(new DynamicLightEvents());
		}

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFuelStation.class,
				new TileentitySpecialRendererFuelStation());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySplitTank.class,
				new TileEntitySpecialRendererSplitTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new TileEntitySpecialRendererTank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySign.class,
				new TileEntitySpecialRendererSign());

	}

	public void postinit(FMLPostInitializationEvent event) {
		super.postinit(event);
	}

}
