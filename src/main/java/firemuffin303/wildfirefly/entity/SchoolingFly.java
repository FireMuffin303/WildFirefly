package firemuffin303.wildfirefly.entity;

import firemuffin303.wildfirefly.entity.goal.FollowGroupLeaderFlyGoal;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class SchoolingFly extends FlyEntity {
    @Nullable
    public SchoolingFly leader;
    private int groupSize = 1;

    protected SchoolingFly(EntityType<? extends SchoolingFly> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(5, new FollowGroupLeaderFlyGoal(this));
    }

    public int getLimitPerChunk() {
        return this.getMaxGroupSize();
    }

    public int getMaxGroupSize() {
        return super.getLimitPerChunk();
    }

    protected boolean hasSelfControl() {
        return !this.hasLeader();
    }

    public boolean hasLeader() {
        return this.leader != null && this.leader.isAlive();
    }

    public SchoolingFly joinGroupOf(SchoolingFly groupLeader) {
        this.leader = groupLeader;
        groupLeader.increaseGroupSize();
        return groupLeader;
    }

    public void leaveGroup() {
        this.leader.decreaseGroupSize();
        this.leader = null;
    }

    private void increaseGroupSize() {
        ++this.groupSize;
    }

    private void decreaseGroupSize() {
        --this.groupSize;
    }

    public boolean canHaveMoreFlyInGroup() {
        return this.hasOtherFlyInGroup() && this.groupSize < this.getMaxGroupSize();
    }

    public void tick() {
        super.tick();
        if (this.hasOtherFlyInGroup() && this.world.random.nextInt(200) == 1) {
            List<? extends FlyEntity> list = this.world.getNonSpectatingEntities(this.getClass(), this.getBoundingBox().expand(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.groupSize = 1;
            }
        }

    }

    public boolean hasOtherFlyInGroup() {
        return this.groupSize > 1;
    }

    public boolean isCloseEnoughToLeader() {
        return this.squaredDistanceTo(this.leader) <= 121.0D;
    }

    public void moveTowardLeader() {
        if (this.hasLeader()) {
            this.getNavigation().startMovingTo(this.leader, 1.0D);
        }

    }

    public void pullInOtherFly(Stream<? extends SchoolingFly> fish) {
        fish.limit((long)(this.getMaxGroupSize() - this.groupSize)).filter((fishx) -> {
            return fishx != this;
        }).forEach((fishx) -> {
            fishx.joinGroupOf(this);
        });
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        super.initialize(world, difficulty, spawnReason, (EntityData)entityData, entityNbt);
        if (entityData == null) {
            entityData = new SchoolingFly.FlyData(this);
        } else {
            this.joinGroupOf(((SchoolingFly.FlyData)entityData).leader);
        }

        return (EntityData)entityData;
    }

    public static class FlyData implements EntityData {
        public final SchoolingFly leader;

        public FlyData(SchoolingFly leader) {
            this.leader = leader;
        }
    }
}
