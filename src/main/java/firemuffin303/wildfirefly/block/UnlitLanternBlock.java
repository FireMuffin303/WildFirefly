package firemuffin303.wildfirefly.block;

import com.google.common.collect.Maps;
import firemuffin303.wildfirefly.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class UnlitLanternBlock extends LanternBlock implements Waterloggable {

    public UnlitLanternBlock(Settings settings) {
        super(settings);

    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        ItemStack itemStack = player.getStackInHand(hand);
        if (hand == Hand.MAIN_HAND && !isLightItem(itemStack) && isLightItem(player.getStackInHand(Hand.OFF_HAND))){
            return ActionResult.PASS;

        }else if(isLightItem(itemStack)) {
            world.setBlockState(pos, LightItem().get(itemStack.getItem()).getStateWithProperties(state));
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            return ActionResult.SUCCESS;

        } else if(itemStack.isOf(Items.FLINT_AND_STEEL)){
            world.setBlockState(pos, Blocks.LANTERN.getStateWithProperties(state));
            itemStack.damage(1,player,(p) -> {
                p.sendToolBreakStatus(hand);
            });
            return ActionResult.SUCCESS;

        }
        return  ActionResult.FAIL;
    }

    private boolean isLightItem(ItemStack itemStack){
        return LightItem().containsKey(itemStack.getItem());
    }



    private Map<Item, Block> LightItem(){
        Map<Item,Block> map = Maps.newLinkedHashMap();
        map.put(Items.TORCH,Blocks.LANTERN);
        map.put(Items.SOUL_TORCH,Blocks.SOUL_LANTERN);
        return map;
    }
}
