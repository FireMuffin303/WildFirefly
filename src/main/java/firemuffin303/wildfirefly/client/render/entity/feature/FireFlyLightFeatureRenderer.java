package firemuffin303.wildfirefly.client.render.entity.feature;

import firemuffin303.wildfirefly.WildFireFly;
import firemuffin303.wildfirefly.client.render.entity.model.FireFlyModel;
import firemuffin303.wildfirefly.client.render.entity.model.ModEntityModelLayers;
import firemuffin303.wildfirefly.entity.FireFlyEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class FireFlyLightFeatureRenderer extends FeatureRenderer<FireFlyEntity, FireFlyModel<FireFlyEntity>> {
    private static final Identifier FIREFLY_FEATURE = new Identifier(WildFireFly.MODID,"textures/entity/firefly/firefly_light.png");

    public FireFlyLightFeatureRenderer(FeatureRendererContext<FireFlyEntity, FireFlyModel<FireFlyEntity>> context) {
        super(context);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, FireFlyEntity fireFlyEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        float red, green, blue;
        float r = Math.max(0.25F,MathHelper.cos(animationProgress * 0.25F + 3.1415927F));

        boolean bl = fireFlyEntity.hasCustomName() && "jeb_".equals(fireFlyEntity.getName().getString());
        if(bl){
            int n = fireFlyEntity.age/25+fireFlyEntity.getId();
            int o = DyeColor.values().length;
            int p = n % o;
            int q = (n + 1) % o;
            float rainbow = ((float)(fireFlyEntity.age % 25) + tickDelta) / 25.0F;
            float[] fs = FireFlyEntity.getRgbColor(DyeColor.byId(p));
            float[] gs = FireFlyEntity.getRgbColor(DyeColor.byId(q));
            red = fs[0] * (1.0F - rainbow) + gs[0] * rainbow;
            blue = fs[1] * (1.0F - rainbow) + gs[1] * rainbow;
            green = fs[2] * (1.0F - rainbow) + gs[2] * rainbow;
        }else {
            float[] color = FireFlyEntity.getRgbColor(fireFlyEntity.getColor());
            float[] black = FireFlyEntity.getRgbColor(DyeColor.BLACK);
            float re = color[0] * (black[0]+r);
            float g = color[1] * (black[1]+r);
            float b = color[2] * (black[2]+r);
            red =  re > 1 ? 1 : re;
            green = g > 1 ? 1 : g;
            blue = b > 1 ? 1 : b;
        }

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentEmissive(FIREFLY_FEATURE));
       this.getContextModel().render(matrices,vertexConsumer,light, LivingEntityRenderer.getOverlay(fireFlyEntity,0.0F),red > 1 ? 1 : red,green > 1 ? 1: green,blue > 1 ? 1:blue,1.0F);
    }

}
