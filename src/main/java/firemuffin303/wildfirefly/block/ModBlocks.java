package firemuffin303.wildfirefly.block;

import firemuffin303.wildfirefly.WildFireFly;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block WHITE_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block ORANGE_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block MAGENTA_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block LIGHT_BLUE_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block YELLOW_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block LIME_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block PINK_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block GRAY_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block LIGHT_GRAY_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block CYAN_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block PURPLE_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block BLUE_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block BROWN_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block GREEN_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block RED_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block BLACK_FIREFLY_LANTERN = createLanternBlock(8);
    public static final Block RAINBOW_FIREFLY_LANTERN = createLanternBlock(12);
    public static final Block UNLIT_LANTERN = new UnlitLanternBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN));

    private static LanternBlock createLanternBlock(int luminance){
        return new LanternBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(luminance));
    }

    private static void addBlock(String id,Block block){
        Registry.register(Registry.BLOCK, new Identifier(WildFireFly.MODID,id),block);
        Registry.register(Registry.ITEM, new Identifier(WildFireFly.MODID,id),new BlockItem(block,new FabricItemSettings().group(WildFireFly.WILDFIREFYLE_TAB)));
    }

    public static void init(){
        addBlock("white_firefly_lantern",WHITE_FIREFLY_LANTERN);
        addBlock("orange_firefly_lantern",ORANGE_FIREFLY_LANTERN);
        addBlock("magenta_firefly_lantern",MAGENTA_FIREFLY_LANTERN);
        addBlock("light_blue_firefly_lantern",LIGHT_BLUE_FIREFLY_LANTERN);
        addBlock("yellow_firefly_lantern",YELLOW_FIREFLY_LANTERN);
        addBlock("lime_firefly_lantern",LIME_FIREFLY_LANTERN);
        addBlock("pink_firefly_lantern",PINK_FIREFLY_LANTERN);
        addBlock("gray_firefly_lantern",GRAY_FIREFLY_LANTERN);
        addBlock("light_gray_firefly_lantern",LIGHT_GRAY_FIREFLY_LANTERN);
        addBlock("cyan_firefly_lantern",CYAN_FIREFLY_LANTERN);
        addBlock("purple_firefly_lantern",PURPLE_FIREFLY_LANTERN);
        addBlock("blue_firefly_lantern",BLUE_FIREFLY_LANTERN);
        addBlock("brown_firefly_lantern",BROWN_FIREFLY_LANTERN);
        addBlock("green_firefly_lantern",GREEN_FIREFLY_LANTERN);
        addBlock("red_firefly_lantern",RED_FIREFLY_LANTERN);
        addBlock("black_firefly_lantern",BLACK_FIREFLY_LANTERN);
        addBlock("rainbow_firefly_lantern",RAINBOW_FIREFLY_LANTERN);
        addBlock("unlit_lantern",UNLIT_LANTERN);
    }
}
