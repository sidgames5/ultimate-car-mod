package de.maxhenkel.car.reciepe;

import de.maxhenkel.car.entity.car.EntityCarBigWood;
import de.maxhenkel.car.entity.car.base.EntityCarBase;
import de.maxhenkel.car.items.ItemKey;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.world.World;

public class CarBuilderWoodCarBig implements ICarbuilder{
	
	private EnumType type;
	
	public CarBuilderWoodCarBig(EnumType type) {
		this.type=type;
	}
	
	@Override
	public EntityCarBase build(World world) {
		EntityCarBigWood car=new EntityCarBigWood(world, type);
		car.setFuelAmount(100);
		car.setInventorySlotContents(0, ItemKey.getKeyForCar(car.getUniqueID()));
		car.setInventorySlotContents(1, ItemKey.getKeyForCar(car.getUniqueID()));
		return car;
	}
	
}
