package de.maxhenkel.car.entity.car.parts;

import de.maxhenkel.car.entity.model.obj.OBJModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

public abstract class PartBodyWoodBase extends PartBody {

    protected Vector3d bumperOffset;

    public PartBodyWoodBase(OBJModel model, ResourceLocation texture, Vector3d offset) {
        super(model, texture, offset);
    }

    public Vector3d getBumperOffset() {
        return bumperOffset;
    }
}
