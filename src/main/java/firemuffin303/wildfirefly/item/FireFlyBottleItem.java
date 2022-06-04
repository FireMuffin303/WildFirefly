package firemuffin303.wildfirefly.item;

import com.google.common.collect.Maps;
import firemuffin303.wildfirefly.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class FireFlyBottleItem extends MobBottleItem{
    public FireFlyBottleItem(Settings settings, SoundEvent soundEvent, EntityType<?> entityType) {
        super(settings, soundEvent, entityType);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbtCompound = stack.getNbt();
        if(nbtCompound != null && nbtCompound.contains("Color")){
            DyeColor dyeColor = DyeColor.byId(nbtCompound.getInt("Color"));
            MutableText mutableText;
            if(stack.hasCustomName() && stack.getName().getString().equals("jeb_")){
                mutableText = Text.translatable("color.wildfirefly.rainbow");
            }else{
                mutableText = Text.translatable("color.minecraft."+dyeColor.getName());
            }
            mutableText.formatted(Formatting.GRAY);
            tooltip.add(mutableText);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world,user, RaycastContext.FluidHandling.NONE);
        BlockPos blockPos = blockHitResult.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        System.out.println(blockState.getBlock());
        if(blockState.isOf(ModBlocks.UNLIT_LANTERN)){
            if(itemStack.hasCustomName() && itemStack.getName().getString().equals("jeb_")){
                user.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, user, new ItemStack(Items.GLASS_BOTTLE)));
                world.playSound(user, blockPos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                world.setBlockState(blockPos, ModBlocks.RAINBOW_FIREFLY_LANTERN.getStateWithProperties(blockState));
            }else{
                int i = itemStack.getOrCreateNbt().getInt("Color");
                user.setStackInHand(hand, ItemUsage.exchangeStack(itemStack, user, new ItemStack(Items.GLASS_BOTTLE)));
                world.playSound(user, blockPos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                world.setBlockState(blockPos, FireflyColor().get(i).getStateWithProperties(blockState));
            }
            return TypedActionResult.success(getEmptiedStack(itemStack,user));
        }else{
            return super.use(world, user, hand);
        }
    }

    private Map<Integer, Block> FireflyColor(){
        Map<Integer,Block> map = Maps.newLinkedHashMap();
        map.put(0,ModBlocks.WHITE_FIREFLY_LANTERN);
        map.put(1,ModBlocks.ORANGE_FIREFLY_LANTERN);
        map.put(2,ModBlocks.MAGENTA_FIREFLY_LANTERN);
        map.put(3,ModBlocks.LIGHT_BLUE_FIREFLY_LANTERN);
        map.put(4,ModBlocks.YELLOW_FIREFLY_LANTERN);
        map.put(5,ModBlocks.LIME_FIREFLY_LANTERN);
        map.put(6,ModBlocks.PINK_FIREFLY_LANTERN);
        map.put(7,ModBlocks.GRAY_FIREFLY_LANTERN);
        map.put(8,ModBlocks.LIGHT_GRAY_FIREFLY_LANTERN);
        map.put(9,ModBlocks.CYAN_FIREFLY_LANTERN);
        map.put(10,ModBlocks.PURPLE_FIREFLY_LANTERN);
        map.put(11,ModBlocks.BLUE_FIREFLY_LANTERN);
        map.put(12,ModBlocks.BROWN_FIREFLY_LANTERN);
        map.put(13,ModBlocks.GREEN_FIREFLY_LANTERN);
        map.put(14,ModBlocks.RED_FIREFLY_LANTERN);
        map.put(15,ModBlocks.BLACK_FIREFLY_LANTERN);
        return map;
    }
}
