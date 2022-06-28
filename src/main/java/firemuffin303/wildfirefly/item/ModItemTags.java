package firemuffin303.wildfirefly.item;

import firemuffin303.wildfirefly.WildFireFly;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItemTags {
    public static TagKey<Item> LANTERN_INGREDIENT;
    public static TagKey<Item> SOUL_LANTERN_INGREDIENT;

    public static void init(){
       TagKey<Item> LANTERN_INGREDIENT = register("lantern_ingredient");
       TagKey<Item> SOUL_LANTERN_INGREDIENT = register("soul_lantern_ingredient");
    }

    private static TagKey<Item> register(String key){
        return TagKey.of(Registry.ITEM_KEY,new Identifier(WildFireFly.MODID,key));
    }
}
