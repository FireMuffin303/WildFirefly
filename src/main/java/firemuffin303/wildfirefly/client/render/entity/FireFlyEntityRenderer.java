package firemuffin303.wildfirefly.client.render.entity;

import firemuffin303.wildfirefly.WildFireFly;
import firemuffin303.wildfirefly.entity.FireFlyEntity;
import firemuffin303.wildfirefly.utils.ModConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;

@Environment(EnvType.CLIENT)
public class FireFlyEntityRenderer extends EntityRenderer<FireFlyEntity> {
    private static final Identifier TEXTURE = new Identifier(WildFireFly.MODID,"textures/entity/firefly/firefly.png");
    private static final RenderLayer LAYER = RenderLayer.getItemEntityTranslucentCull(TEXTURE);

    public FireFlyEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    protected boolean hasLabel(FireFlyEntity entity) {
        return entity.hasCustomName() && entity == this.dispatcher.targetedEntity;
    }

    public void render(FireFlyEntity fireFlyEntity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
        super.render(fireFlyEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
        matrixStack.push();
        matrixStack.multiply(this.dispatcher.getRotation());
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
        float red, green, blue;
        float black[] = FireFlyEntity.getRgbColor(DyeColor.BLACK);
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
            float re = color[0];
            float g = color[1];
            float b = color[2];
            red =  re >= 1 ? 1 : re;
            green = g >= 1 ? 1 : g;
            blue = b >= 1 ? 1 : b;
        }


        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);

        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();
        vertex(vertexConsumer,matrix4f,matrix3f,0F, 0F,red,green,blue,0,1,light);
        vertex(vertexConsumer,matrix4f,matrix3f,1F, 0F,red,green,blue,1,1,light);
        vertex(vertexConsumer,matrix4f,matrix3f,1F, 1F,red,green,blue,1,0,light);
        vertex(vertexConsumer,matrix4f,matrix3f,0F, 1F,red,green,blue,0,0,light);
        matrixStack.scale(1.25F,1.25F,1.25F);
        matrixStack.pop();

    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float red, float green, float blue, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x - 0.5F, y-0.25F, 0.0F).color(Math.round(red*255), Math.round(green*255), Math.round(blue*255), 255).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0.0F, 1.0F, 0.0F).next();
    }


    @Override
    public Identifier getTexture(FireFlyEntity entity) {
        return TEXTURE;
    }

    @Override
    protected int getBlockLight(FireFlyEntity entity, BlockPos pos) {
        if(ModConfig.flickeringFirefly){
            int i = (int)MathHelper.clampedLerp(0.0F, 15.0F,(1.0F - (float)entity.getLightTicksRemaining() / 10.0F));
            return i == 15? 15 : Math.max(i, super.getBlockLight(entity, pos));
        }else {
            return 15;
        }
    }
}
