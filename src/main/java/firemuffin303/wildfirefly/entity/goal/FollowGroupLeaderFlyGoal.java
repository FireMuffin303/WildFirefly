package firemuffin303.wildfirefly.entity.goal;

import com.mojang.datafixers.DataFixUtils;
import firemuffin303.wildfirefly.entity.SchoolingFly;
import net.minecraft.entity.ai.goal.Goal;

import java.util.List;
import java.util.function.Predicate;

public class FollowGroupLeaderFlyGoal extends Goal {
    private final SchoolingFly schoolingFly;
    private int checkSurroundingDelay;
    private int moveDelay;

    public FollowGroupLeaderFlyGoal(SchoolingFly schoolingFly) {
        this.schoolingFly = schoolingFly;
        this.checkSurroundingDelay = this.getSurroundingSearchDelay(schoolingFly);
    }

    protected int getSurroundingSearchDelay(SchoolingFly schoolingFly) {
        return toGoalTicks(200 + schoolingFly.getRandom().nextInt(200) % 20);
    }

    @Override
    public boolean canStart() {
        if (this.schoolingFly.hasOtherFlyInGroup()) {
            return false;
        } else if (this.schoolingFly.hasLeader()) {
            return true;
        } else if (this.checkSurroundingDelay > 0) {
            --this.checkSurroundingDelay;
            return false;
        } else {
            this.checkSurroundingDelay = this.getSurroundingSearchDelay(this.schoolingFly);
            Predicate<SchoolingFly> predicate = (fish) -> {
                return fish.canHaveMoreFlyInGroup() || !fish.hasLeader();
            };
            List<? extends SchoolingFly> list = this.schoolingFly.world.getEntitiesByClass(this.schoolingFly.getClass(), this.schoolingFly.getBoundingBox().expand(8.0D, 8.0D, 8.0D), predicate);
            SchoolingFly schoolingFly = DataFixUtils.orElse(list.stream().filter(SchoolingFly::canHaveMoreFlyInGroup).findAny(), this.schoolingFly);
            schoolingFly.pullInOtherFly(list.stream().filter((fish) -> {
                return !schoolingFly.hasLeader();
            }));
            return this.schoolingFly.hasLeader();
        }
    }

    public boolean shouldContinue() {
        return this.schoolingFly.hasLeader() && this.schoolingFly.isCloseEnoughToLeader();
    }

    public void start() {
        this.moveDelay = 0;
    }

    public void stop() {
        this.schoolingFly.leaveGroup();
    }

    public void tick() {
        if (--this.moveDelay <= 0) {
            this.moveDelay = this.getTickCount(10);
            this.schoolingFly.moveTowardLeader();
        }
    }
}
