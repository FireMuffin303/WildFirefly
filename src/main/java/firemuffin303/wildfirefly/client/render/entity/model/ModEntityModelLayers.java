package firemuffin303.wildfirefly.client.render.entity.model;

import firemuffin303.wildfirefly.WildFireFly;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModEntityModelLayers {
    public static final EntityModelLayer FIREFLY = new EntityModelLayer(new Identifier(WildFireFly.MODID,"firefly"),"main");
    public static final EntityModelLayer FIREFLY_LIGHT = new EntityModelLayer(new Identifier(WildFireFly.MODID,"firefly_light"),"main");

    public static void init(){
        EntityModelLayerRegistry.registerModelLayer(FIREFLY,FireFlyModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(FIREFLY_LIGHT,FireFlyModel::getTexturedModelData);
    }
}
