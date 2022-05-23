package firemuffin303.wildfirefly.entity;

import firemuffin303.wildfirefly.WildFireFly;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

public class ModEntities {
    public static final EntityType<FireFlyEntity> FIREFLY;

    public static void init(){
        FabricDefaultAttributeRegistry.register(FIREFLY,FireFlyEntity.createFlyAttributes());
    }

    static {
        FIREFLY = Registry.register(Registry.ENTITY_TYPE,new Identifier(WildFireFly.MODID,"firefly"), FabricEntityTypeBuilder.createMob().dimensions(EntityDimensions.fixed(0.5F,0.5F)).spawnGroup(SpawnGroup.AMBIENT).entityFactory(FireFlyEntity::new).spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.WORLD_SURFACE,FireFlyEntity::canSpawn).build());
    }

}
