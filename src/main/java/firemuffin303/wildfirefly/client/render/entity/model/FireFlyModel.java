package firemuffin303.wildfirefly.client.render.entity.model;

import firemuffin303.wildfirefly.entity.FireFlyEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class FireFlyModel<T extends FireFlyEntity> extends SinglePartEntityModel<T> {

    private final ModelPart root;

    public FireFlyModel(ModelPart part){
        this.root = part;
    }

    public static TexturedModelData getTexturedModelData(){
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("body",ModelPartBuilder.create().uv(0,0).cuboid(0.0F,19.0F,-2.0F,0.0F,2.0F,4.0F),ModelTransform.pivot(0,0,0));
        return TexturedModelData.of(modelData,16,16);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }


}
