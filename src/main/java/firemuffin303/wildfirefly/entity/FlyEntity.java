package firemuffin303.wildfirefly.entity;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class FlyEntity extends AmbientEntity implements Bottleable {
    private static final TrackedData<Boolean> FROM_BOTTLE =  DataTracker.registerData(FlyEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int ticksInsideWater;
    private BlockPos randomPos;

    protected FlyEntity(EntityType<? extends AmbientEntity> entityType, World world) {
        super(entityType, world);
        //this.moveControl = new FlightMoveControl(this, 20, true);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0F);
    }

    protected void initGoals(){
        super.initGoals();
        this.goalSelector.add(9, new SwimGoal(this));
    }

    protected void mobTick(){
        if (this.randomPos != null && (!this.world.isAir(this.randomPos) || this.randomPos.getY() <= this.world.getBottomY())) {
            this.randomPos = null;
        }

        if (this.randomPos == null || this.random.nextInt(30) == 0 || this.randomPos.isWithinDistance(this.getPos(), 2.0D)) {
            this.randomPos = new BlockPos(this.getX() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7), this.getY() + (double)this.random.nextInt(4) - 2.0D, this.getZ() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7));
        }

        double d = this.getX() + 0.5D - randomPos.getX();
        double e = this.getY() + 0.01D - randomPos.getY();
        double f = this.getZ() + 0.5D - randomPos.getZ();

        Vec3d vec3d = this.getVelocity();
        Vec3d vec3d2 = vec3d.add((Math.signum(d) * 0.5D - vec3d.x) * 0.02000000149011612D, (Math.signum(e) * 0.699999988079071D - vec3d.y) * 0.07500000149011612D, (Math.signum(f) * 0.5D - vec3d.z) * 0.02000000149011612D);
        this.setVelocity(vec3d2);
        float g = (float)(MathHelper.atan2(vec3d2.z, vec3d2.x) * 57.2957763671875D) - 90.0F;
        float h = MathHelper.wrapDegrees(g - this.getYaw());
        this.forwardSpeed = 0.3F;
        this.setYaw(this.getYaw() + h);

        if (this.isInsideWaterOrBubbleColumn()) {
            ++this.ticksInsideWater;
        } else {
            this.ticksInsideWater = 0;
        }

        if (this.ticksInsideWater > 20) {
            this.damage(DamageSource.DROWN, 1.0F);
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.setVelocity(this.getVelocity().multiply(1.0D, 0.6D, 1.0D));
    }

    public static DefaultAttributeContainer.Builder createFlyAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D);
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FROM_BOTTLE,false);
    }

    public boolean cannotDespawn() {
        return super.cannotDespawn() || this.isFromBottle();
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }

    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        return (ActionResult)Bottleable.tryBottle(player, hand, this).orElse(super.interactMob(player, hand));
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("FromBottle", this.isFromBottle());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setFromBottle(nbt.getBoolean("FromBottle"));
    }

    @Override
    public boolean isFromBottle() {
        return this.dataTracker.get(FROM_BOTTLE);
    }

    @Override
    public void setFromBottle(boolean fromBottle) {
        this.dataTracker.set(FROM_BOTTLE,fromBottle);
    }

    public void copyDataToStack(ItemStack stack) {
        Bottleable.copyDataToStack(this, stack);
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bottleable.copyDataFromNbt(this,nbt);
    }

    @Override
    public ItemStack getBottleItem() {
        return null;
    }

    @Override
    public SoundEvent getBottleFillSound() {
        return SoundEvents.ITEM_BOTTLE_FILL;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BEE_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BEE_HURT;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height / 2.0F;
    }

}
