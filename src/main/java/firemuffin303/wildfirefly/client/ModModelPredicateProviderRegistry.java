package firemuffin303.wildfirefly.client;

import firemuffin303.wildfirefly.WildFireFly;
import firemuffin303.wildfirefly.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModModelPredicateProviderRegistry {
        public static void init() {
            FabricModelPredicateProviderRegistry.register(ModItems.FIREFLY_IN_A_BOTTLE, new Identifier(WildFireFly.MODID, "color"), (stack, world, entity, seed) -> {
                        int i = stack.hasCustomName() && stack.getName().getString().equals("jeb_") ? 16 : (int) stack.getOrCreateNbt().getByte("Color");
                        return i / 16.0F;
                    }
            );
        }
}
