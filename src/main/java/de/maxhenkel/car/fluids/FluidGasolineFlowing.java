package de.maxhenkel.car.fluids;

import de.maxhenkel.car.blocks.ModBlocks;
import de.maxhenkel.car.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class FluidGasolineFlowing extends CarFluidFlowing {

    protected FluidGasolineFlowing() {
        super(new Properties(
                ModFluids.GASOLINE_TYPE,
                ModFluids.GASOLINE,
                ModFluids.GASOLINE_FLOWING)
                .block(ModBlocks.GASOLINE)
                .bucket(ModItems.GASOLINE_BUCKET)
        );
    }

    @Override
    public void applyEffects(Entity entity, BlockState state, Level worldIn, BlockPos pos) {
        ModFluids.GASOLINE.get().applyEffects(entity, state, worldIn, pos);
    }
}
