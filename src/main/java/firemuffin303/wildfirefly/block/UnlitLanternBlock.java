package firemuffin303.wildfirefly.block;

import firemuffin303.wildfirefly.item.ModItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UnlitLanternBlock extends LanternBlock implements Waterloggable {

    public UnlitLanternBlock(Settings settings) {
        super(settings);

    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        ItemStack itemStack = player.getStackInHand(hand);
         if(itemStack.isIn(ModItemTags.LANTERN_INGREDIENT)) {
            world.setBlockState(pos, Blocks.LANTERN.getStateWithProperties(state));
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            world.playSound(player,pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.NEUTRAL,1.0F,1.0F);
            return ActionResult.SUCCESS;

        }else if(itemStack.isIn(ModItemTags.SOUL_LANTERN_INGREDIENT)){
            world.setBlockState(pos, Blocks.SOUL_LANTERN.getStateWithProperties(state));
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            world.playSound(player,pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.NEUTRAL,1.0F,1.0F);
            return ActionResult.SUCCESS;


        } else if(itemStack.isOf(Items.FLINT_AND_STEEL)){
            world.setBlockState(pos, Blocks.LANTERN.getStateWithProperties(state));
            itemStack.damage(1,player,(p) -> {
                p.sendToolBreakStatus(hand);
            });
            world.playSound(player,pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.NEUTRAL,1.0F,1.0F);
            return ActionResult.SUCCESS;

        }
        return  ActionResult.FAIL;
    }

}
