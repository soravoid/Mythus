package net.mythusteam.mythus.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.mythusteam.mythus.Mythus;
import net.mythusteam.mythus.capabilities.interfaces.IMEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityMEnergy implements ICapabilitySerializable<CompoundNBT>
{
    public static final ResourceLocation LOCATION = new ResourceLocation(Mythus.MODID, "m_energy");

    @CapabilityInject(IMEnergy.class)
    public static final Capability<IMEnergy> M_ENERGY_CAPABILITY = null;

    private LazyOptional<IMEnergy> instance = LazyOptional.of(M_ENERGY_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) { return M_ENERGY_CAPABILITY.orEmpty(cap, instance); }

    @Override
    public CompoundNBT serializeNBT() { return(CompoundNBT) M_ENERGY_CAPABILITY.getStorage().writeNBT(M_ENERGY_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null); }

    @Override
    public void deserializeNBT(CompoundNBT nbt) { M_ENERGY_CAPABILITY.getStorage().readNBT(M_ENERGY_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt); }

    public static class Storage implements Capability.IStorage<IMEnergy>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<IMEnergy> capability, IMEnergy instance, Direction side)
        {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("current", instance.getCurrent());
            nbt.putInt("max", instance.getMax());
            return nbt;
        }

        @Override
        public void readNBT(Capability<IMEnergy> capability, IMEnergy instance, Direction side, INBT nbt)
        {
            CompoundNBT cNbt = (CompoundNBT) nbt;
            instance.setSilently(cNbt.getInt("current"), cNbt.getInt("max"));
        }
    }
}
