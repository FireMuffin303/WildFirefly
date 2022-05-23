package firemuffin303.wildfirefly;

import firemuffin303.wildfirefly.block.ModBlocks;
import firemuffin303.wildfirefly.entity.ModEntities;
import firemuffin303.wildfirefly.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class WildFireFly implements ModInitializer {
    public static final String MODID = "wildfirefly";
    public static final ItemGroup WILDFIREFYLE_TAB = FabricItemGroupBuilder.build( new Identifier(MODID,"main"),() -> new ItemStack(ModItems.FIREFLY_IN_A_BOTTLE));

    @Override
    public void onInitialize() {
        ModItems.init();
        ModBlocks.init();
        ModEntities.init();

        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.AMBIENT,ModEntities.FIREFLY,10,8,8);
    }
}
