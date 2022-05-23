package firemuffin303.wildfirefly.client;

import com.google.common.collect.Maps;
import firemuffin303.wildfirefly.WildFireFly;
import firemuffin303.wildfirefly.entity.FireFlyEntity;
import firemuffin303.wildfirefly.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.item.UnclampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class ModModelPredicateProviderRegistry {
        public static void init() {
            FabricModelPredicateProviderRegistry.register(ModItems.FIREFLY_IN_A_BOTTLE, new Identifier(WildFireFly.MODID, "color"), (stack, world, entity, seed) -> {
                        int i = stack.hasCustomName() && stack.getOrCreateNbt().getCompound("display").getString("Name").equals("{\"text\":\"jeb_\"}") ? 16 : (int) stack.getOrCreateNbt().getByte("Color");
                        return i / 16.0F;
                    }
            );
        }
}
