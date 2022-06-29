package firemuffin303.wildfirefly.item;

import firemuffin303.wildfirefly.entity.Bottleable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class MobBottleItem extends Item {
    private EntityType<?> entityType;
    private SoundEvent soundEvent;

    public MobBottleItem(Settings settings,SoundEvent soundEvent,EntityType<?> entityType) {
        super(settings);
        this.entityType = entityType;
        this.soundEvent = soundEvent;
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack itemStack = context.getStack();
        PlayerEntity user = context.getPlayer();
        BlockPos blockPos = context.getBlockPos();
        Direction direction = context.getSide();
        BlockPos blockPos2 = blockPos.offset(direction);
        this.onEmptied(context.getPlayer(), context.getWorld(), context.getStack(), blockPos2);
        user.setStackInHand(context.getHand(),getEmptiedStack(itemStack,user));
        return ActionResult.SUCCESS;

    }

    public void onEmptied(@Nullable PlayerEntity player, World world, ItemStack stack, BlockPos pos) {
        if (world instanceof ServerWorld) {
            this.spawnEntity((ServerWorld)world, stack, pos);
            world.emitGameEvent(player, GameEvent.ENTITY_PLACE, pos);
        }

    }

    public static ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
        return !player.getAbilities().creativeMode ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

    protected void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos) {
        world.playSound(player, pos, this.soundEvent, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }

    private void spawnEntity(ServerWorld world, ItemStack stack, BlockPos pos) {
        Entity entity = this.entityType.spawnFromItemStack(world, stack, (PlayerEntity)null, pos, SpawnReason.BUCKET, true, false);
        if (entity instanceof Bottleable) {
            Bottleable bottleable = (Bottleable) entity;
            bottleable.copyDataFromNbt(stack.getOrCreateNbt());
            bottleable.setFromBottle(true);
        }

    }
}
