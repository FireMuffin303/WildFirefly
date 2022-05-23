package firemuffin303.wildfirefly.item;

import firemuffin303.wildfirefly.WildFireFly;
import firemuffin303.wildfirefly.entity.FireFlyEntity;
import firemuffin303.wildfirefly.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {
    public static final Item FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFYLE_TAB), SoundEvents.ITEM_BOTTLE_EMPTY, ModEntities.FIREFLY);
    public static final Item FIREFLY_SPAWN_EGG = new SpawnEggItem(ModEntities.FIREFLY,3684408,16580402,new FabricItemSettings().group(WildFireFly.WILDFIREFYLE_TAB));

    public static void init(){
        register("firefly_in_a_bottle",FIREFLY_IN_A_BOTTLE);
        register("firefly_spawn_egg",FIREFLY_SPAWN_EGG);
    }

    private static void register(String id,Item item){
        Registry.register(Registry.ITEM,new Identifier(WildFireFly.MODID,id),item);
    }
}
