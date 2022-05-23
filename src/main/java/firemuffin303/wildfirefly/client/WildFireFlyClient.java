package firemuffin303.wildfirefly.client;

import firemuffin303.wildfirefly.client.render.entity.FireFlyEntityRenderer;
import firemuffin303.wildfirefly.client.render.entity.model.ModEntityModelLayers;
import firemuffin303.wildfirefly.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;

@Environment(EnvType.CLIENT)
public class WildFireFlyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.FIREFLY,(context) -> {
            return new FireFlyEntityRenderer(context);
        });

        ModEntityModelLayers.init();
        ModModelPredicateProviderRegistry.init();
    }
}
