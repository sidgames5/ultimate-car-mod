package de.maxhenkel.car.entity.car.parts;

import de.maxhenkel.car.Main;
import de.maxhenkel.car.entity.car.base.EntityGenericCar;
import de.maxhenkel.car.entity.model.obj.OBJModel;
import de.maxhenkel.car.entity.model.obj.OBJModelInstance;
import de.maxhenkel.car.entity.model.obj.OBJModelOptions;
import de.maxhenkel.tools.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class PartLicensePlateHolder extends PartModel {

    protected Vector3d textOffset;

    public PartLicensePlateHolder(ResourceLocation texture) {
        super(new OBJModel(new ResourceLocation(Main.MODID, "models/entity/license_plate.obj")), texture, new Vector3d(0D, 0D, 0D), new Rotation(90F, Vector3f.ZP));
        this.textOffset = new Vector3d(0D, -0.5D / 16D, -0.5D / 16D - 0.001D);

    }

    public Vector3d getTextOffset() {
        return textOffset;
    }

    @Override
    public List<OBJModelInstance> getInstances(EntityGenericCar car) {
        PartBody chassis = car.getPartByClass(PartBody.class);

        if (chassis == null) {
            return super.getInstances(car);
        }

        List<OBJModelInstance> list = new ArrayList<>();
        list.add(new OBJModelInstance(model, new OBJModelOptions(texture, chassis.getNumberPlateOffset(), rotation)));
        onPartAdd(list);
        return list;
    }
}
