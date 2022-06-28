package firemuffin303.wildfirefly.mixin;

import com.google.common.collect.Maps;
import firemuffin303.wildfirefly.block.ModBlocks;
import firemuffin303.wildfirefly.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(GlassBottleItem.class)
public class GlassBottleItemMixin extends Item {
    public GlassBottleItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "use",at = @At("TAIL"),cancellable = true)
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world,user, RaycastContext.FluidHandling.NONE);
        BlockPos blockPos = blockHitResult.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if(user.isSneaking()){
            ItemStack itemStack1 = new ItemStack(ModItems.FIREFLY_IN_A_BOTTLE);
            NbtCompound nbtCompound = itemStack1.getOrCreateNbt();
            byte i = FireflyColor().get(blockState.getBlock()).byteValue();
            nbtCompound.putByte("Color",i);
            world.setBlockState(blockPos,ModBlocks.UNLIT_LANTERN.getStateWithProperties(blockState));
            if(blockState.isOf(ModBlocks.RAINBOW_FIREFLY_LANTERN)){
                itemStack1.setCustomName(Text.literal("jeb_"));
            }
            world.playSound(user, blockPos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            user.setStackInHand(hand,itemStack1);
        }
    }

    private Map<Block, Integer> FireflyColor(){
        Map<Block,Integer> map = Maps.newLinkedHashMap();
        map.put(ModBlocks.WHITE_FIREFLY_LANTERN,0 );
        map.put(ModBlocks.ORANGE_FIREFLY_LANTERN,1);
        map.put(ModBlocks.MAGENTA_FIREFLY_LANTERN,2);
        map.put(ModBlocks.LIGHT_BLUE_FIREFLY_LANTERN,3);
        map.put(ModBlocks.YELLOW_FIREFLY_LANTERN,4);
        map.put(ModBlocks.LIME_FIREFLY_LANTERN,5);
        map.put(ModBlocks.PINK_FIREFLY_LANTERN,6);
        map.put(ModBlocks.GRAY_FIREFLY_LANTERN,7);
        map.put(ModBlocks.LIGHT_GRAY_FIREFLY_LANTERN,8);
        map.put(ModBlocks.CYAN_FIREFLY_LANTERN,9);
        map.put(ModBlocks.PURPLE_FIREFLY_LANTERN,10);
        map.put(ModBlocks.BLUE_FIREFLY_LANTERN,11);
        map.put(ModBlocks.BROWN_FIREFLY_LANTERN,12);
        map.put(ModBlocks.GREEN_FIREFLY_LANTERN,13);
        map.put(ModBlocks.RED_FIREFLY_LANTERN,14);
        map.put(ModBlocks.BLACK_FIREFLY_LANTERN,15);
        return map;
    }
}
