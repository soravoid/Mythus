package net.mythusteam.mythus.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.mythusteam.mythus.Mythus;
import net.mythusteam.mythus.capabilities.interfaces.ISmithQuality;
import net.mythusteam.mythus.utils.SmithQuality;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilitySmithQuality implements ICapabilitySerializable<IntNBT>
{
    public static final ResourceLocation LOCATION = new ResourceLocation(Mythus.MODID, "smith_quality");

    @CapabilityInject(ISmithQuality.class)
    public static final Capability<ISmithQuality> SMITH_QUALITY_CAPABILITY = null;

    private LazyOptional<ISmithQuality> instance = LazyOptional.of(SMITH_QUALITY_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return SMITH_QUALITY_CAPABILITY.orEmpty(cap, instance);
    }

    @Override
    public void deserializeNBT(IntNBT nbt) { SMITH_QUALITY_CAPABILITY.getStorage().readNBT(SMITH_QUALITY_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt); }

    @Override
    public IntNBT serializeNBT() { return (IntNBT) SMITH_QUALITY_CAPABILITY.getStorage().writeNBT(SMITH_QUALITY_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null); }

    public static class Storage implements Capability.IStorage<ISmithQuality>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<ISmithQuality> capability, ISmithQuality instance, Direction side) { return IntNBT.valueOf(instance.getQuality().ordinal()); }

        @Override
        public void readNBT(Capability<ISmithQuality> capability, ISmithQuality instance, Direction side, INBT nbt) { instance.setQuality(SmithQuality.values()[((IntNBT)nbt).getInt()]); }
    }
}
