package mod.mythusteam.mythus.capabilities;

import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.capabilities.interfaces.ICoreStorage;
import mod.mythusteam.mythus.items.cores.MythusCore;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityCoreStorage implements ICapabilitySerializable<CompoundNBT>
{
    public static final ResourceLocation LOCATION = new ResourceLocation(Mythus.MODID, "core_storage");

    @CapabilityInject(ICoreStorage.class)
    public static Capability<ICoreStorage> CORE_STORAGE_CAPABILITY = null;

    private LazyOptional<ICoreStorage> instance = LazyOptional.of(CORE_STORAGE_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CORE_STORAGE_CAPABILITY.orEmpty(cap, instance);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) CORE_STORAGE_CAPABILITY.getStorage().writeNBT(CORE_STORAGE_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        CORE_STORAGE_CAPABILITY.getStorage().readNBT(CORE_STORAGE_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt);
    }

    public static class Storage implements Capability.IStorage<ICoreStorage>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<ICoreStorage> capability, ICoreStorage instance, Direction side)
        {
            ListNBT nbtTagList = new ListNBT();
            for (int i = 0; i < instance.getSlots(); i++)
            {
                if (!instance.getStackInSlot(i).isEmpty())
                {
                    CompoundNBT itemTag = new CompoundNBT();
                    itemTag.putInt("Slot", i);
                    instance.getStackInSlot(i).write(itemTag);
                    nbtTagList.add(itemTag);
                }
            }
            CompoundNBT nbt = new CompoundNBT();
            nbt.put("Items", nbtTagList);
            nbt.putInt("Size", instance.getSlots());
            nbt.putInt("Level", instance.getLevel());
            return nbt;
        }

        @Override
        public void readNBT(Capability<ICoreStorage> capability, ICoreStorage instance, Direction side, INBT amNBT) {
            CompoundNBT nbt = (CompoundNBT) amNBT;
            instance.setSize(nbt.contains("Size", Constants.NBT.TAG_INT) ? nbt.getInt("Size") : instance.getSlots());
            ListNBT tagList = nbt.getList("Items", Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < tagList.size(); i++)
            {
                CompoundNBT itemTags = tagList.getCompound(i);
                int slot = itemTags.getInt("Slot");

                if (slot >= 0 && slot < instance.getSlots())
                {
                    instance.insertItem(slot, ItemStack.read(itemTags), false);
                }
            }
            instance.setLevel(nbt.getInt("Level"));
        }
    }
}
