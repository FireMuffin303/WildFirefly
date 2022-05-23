package firemuffin303.wildfirefly.client.render.entity;

import com.google.common.collect.Maps;
import firemuffin303.wildfirefly.WildFireFly;
import firemuffin303.wildfirefly.client.render.entity.feature.FireFlyLightFeatureRenderer;
import firemuffin303.wildfirefly.client.render.entity.model.FireFlyModel;
import firemuffin303.wildfirefly.client.render.entity.model.ModEntityModelLayers;
import firemuffin303.wildfirefly.entity.FireFlyEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class FireFlyEntityRenderer extends MobEntityRenderer<FireFlyEntity, FireFlyModel<FireFlyEntity>> {
    private static final Identifier TEXTURE = new Identifier(WildFireFly.MODID,"textures/entity/firefly/firefly_base.png");

    public FireFlyEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new FireFlyModel<>(context.getPart(ModEntityModelLayers.FIREFLY)), 0.3F);
        this.addFeature(new FireFlyLightFeatureRenderer(this));
    }


    @Override
    public Identifier getTexture(FireFlyEntity entity) {
        return TEXTURE;
    }

}
