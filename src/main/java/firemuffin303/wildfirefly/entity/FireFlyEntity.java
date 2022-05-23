package firemuffin303.wildfirefly.entity;

import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import firemuffin303.wildfirefly.item.ModItems;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.AbstractRandom;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FireFlyEntity extends SchoolingFly{
    private static final Logger logger = LogUtils.getLogger();
    private static final TrackedData<Byte> COLOR;
    private static final Map<DyeColor, float[]> COLORS;

    private static float[] getDyedColor(DyeColor color) {
        if (color == DyeColor.WHITE) {
            return new float[]{0.9019608F, 0.9019608F, 0.9019608F};
        } else {
            float[] fs = color.getColorComponents();
            return new float[]{fs[0], fs[1], fs[2]};
        }
    }

    @Override
    protected void initGoals() {
        super.initGoals();
    }

    public static float[] getRgbColor(DyeColor dyeColor) {
        return COLORS.get(dyeColor);
    }

    public FireFlyEntity(EntityType<? extends  FireFlyEntity> entityType, World world){
        super(entityType,world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(COLOR,(byte)0);
    }


    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putByte("Color", (byte)this.getColor().getId());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setColor(DyeColor.byId(nbt.getByte("Color")));
    }

    public void copyDataToStack(ItemStack stack) {
        super.copyDataToStack(stack);
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putByte("Color", (byte) this.getColor().getId());
    }

    public void copyDataFromNbt(NbtCompound nbt) {
        Bottleable.copyDataFromNbt(this,nbt);
        int i = nbt.getByte("Color");
        if(i >= 0 && i < DyeColor.values().length){
            this.setColor(DyeColor.byId(i));
        }else{
            logger.error("Invalid variant: {}", i);
        }
    }

    public DyeColor getColor() {
        return DyeColor.byId(this.dataTracker.get(COLOR) & 15);
    }

    public void setColor(DyeColor color) {
        byte b = this.dataTracker.get(COLOR);
        this.dataTracker.set(COLOR, (byte)(b & 240 | color.getId() & 15));
    }

    public static boolean canSpawn(EntityType<FireFlyEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, AbstractRandom random){
        if (pos.getY() <= world.getSeaLevel()){
            return false;
        }else {
            int i = world.getLightLevel(pos);
            int j = 4;
            return i > random.nextInt(j) ? false : canMobSpawn(type,world,spawnReason,pos,random);
        }
    }


    public ItemStack getBottleItem() {
        return new ItemStack(ModItems.FIREFLY_IN_A_BOTTLE);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if (spawnReason == SpawnReason.BUCKET) {
            return entityData;
        }else {
            AbstractRandom abstractRandom = world.getRandom();
            this.setColor(FireFlyEntity.FireFlyData.getRandomColor(abstractRandom));
            return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
        }
    }

    static {
        COLOR = DataTracker.registerData(FireFlyEntity.class, TrackedDataHandlerRegistry.BYTE);
        COLORS = Maps.newEnumMap((Map)Arrays.stream(DyeColor.values()).collect(Collectors.toMap((dyeColor) -> dyeColor, FireFlyEntity::getDyedColor)));
    }

    private static class FireFlyData extends SchoolingFly.FlyData {
        final DyeColor dyeColor;


        public FireFlyData(SchoolingFly leader, DyeColor dyeColor) {
            super(leader);
            this.dyeColor = dyeColor;

        }

        public static DyeColor getRandomColor(AbstractRandom abstractRandom){
            DyeColor[] dyeColors = DyeColor.values();
            return dyeColors[abstractRandom.nextInt(dyeColors.length)];
        }
    }
}
