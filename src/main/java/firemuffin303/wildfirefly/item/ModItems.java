package firemuffin303.wildfirefly.item;

import firemuffin303.wildfirefly.WildFireFly;
import firemuffin303.wildfirefly.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.sound.Sound;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {
    //public static final Item FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new Item.Settings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB), SoundEvents.ITEM_BOTTLE_EMPTY, ModEntities.FIREFLY);
    public static final Item FIREFLY_SPAWN_EGG = new SpawnEggItem(ModEntities.FIREFLY,3684408,16580402,new Item.Settings().group(WildFireFly.WILDFIREFLY_TAB));
    public static final Item WHITE_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item ORANGE_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item MAGENTA_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item LIGHT_BLUE_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item YELLOW_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item LIME_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item PINK_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item GRAY_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item LIGHT_GRAY_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item CYAN_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item PURPLE_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item BLUE_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item BROWN_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item GREEN_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item RED_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);
    public static final Item BLACK_FIREFLY_IN_A_BOTTLE = new FireFlyBottleItem(new FabricItemSettings().maxCount(1).group(WildFireFly.WILDFIREFLY_TAB).recipeRemainder(Items.GLASS_BOTTLE), SoundEvents.ITEM_BOTTLE_EMPTY,ModEntities.FIREFLY);


    public static void init(){
        //register("firefly_in_a_bottle",FIREFLY_IN_A_BOTTLE);
        register("firefly_spawn_egg",FIREFLY_SPAWN_EGG);
        register("white_firefly_in_a_bottle",WHITE_FIREFLY_IN_A_BOTTLE);
        register("orange_firefly_in_a_bottle",ORANGE_FIREFLY_IN_A_BOTTLE);
        register("magenta_firefly_in_a_bottle",MAGENTA_FIREFLY_IN_A_BOTTLE);
        register("light_blue_firefly_in_a_bottle",LIGHT_BLUE_FIREFLY_IN_A_BOTTLE);
        register("yellow_firefly_in_a_bottle",YELLOW_FIREFLY_IN_A_BOTTLE);
        register("lime_firefly_in_a_bottle",LIME_FIREFLY_IN_A_BOTTLE);
        register("pink_firefly_in_a_bottle",PINK_FIREFLY_IN_A_BOTTLE);
        register("gray_firefly_in_a_bottle",GRAY_FIREFLY_IN_A_BOTTLE);
        register("light_gray_firefly_in_a_bottle",LIGHT_GRAY_FIREFLY_IN_A_BOTTLE);
        register("cyan_firefly_in_a_bottle",CYAN_FIREFLY_IN_A_BOTTLE);
        register("purple_firefly_in_a_bottle",PURPLE_FIREFLY_IN_A_BOTTLE);
        register("blue_firefly_in_a_bottle",BLUE_FIREFLY_IN_A_BOTTLE);
        register("brown_firefly_in_a_bottle",BROWN_FIREFLY_IN_A_BOTTLE);
        register("green_firefly_in_a_bottle",GREEN_FIREFLY_IN_A_BOTTLE);
        register("red_firefly_in_a_bottle",RED_FIREFLY_IN_A_BOTTLE);
        register("black_firefly_in_a_bottle",BLACK_FIREFLY_IN_A_BOTTLE);

    }

    private static void register(String id,Item item){
        Registry.register(Registry.ITEM,new Identifier(WildFireFly.MODID,id),item);
    }
}
