package mod.mythusteam.mythus.inventory;

import mod.mythusteam.mythus.capabilities.interfaces.ICoreStorage;
import mod.mythusteam.mythus.init.MythusContainers;
import mod.mythusteam.mythus.inventory.slots.MythusSlotCores;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

public class CoreStorageContainer extends Container
{
    private final int CORES_SIZE;
    private IItemHandler playerInventory;
    private static final List<ImmutablePair<Integer,Integer>> slotCoords = new ArrayList<ImmutablePair<Integer, Integer>>(5)
    {{
        //(x,y)
        add(new ImmutablePair<>(80, 17));
        add(new ImmutablePair<>(44, 44));
        add(new ImmutablePair<>(116, 44));
        add(new ImmutablePair<>(61, 84));
        add(new ImmutablePair<>(101, 84));
    }};

    public CoreStorageContainer(int id, PlayerInventory inventory, ICoreStorage cores)
    {
        super(MythusContainers.CORE_STORAGE_CONTAINER.get(), id);

        CORES_SIZE = cores.getSlots();
        this.playerInventory = new InvWrapper(inventory);

        int i;

        //TODO Change texture locations after new texture
        for(i = 0; i < cores.getSlots(); i++)
        {
            this.addSlot(new MythusSlotCores(cores, i, slotCoords.get(i).left, slotCoords.get(i).right));
        }

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 132 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 190));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }

    //TODO FIX, BROKEN
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(index >= 0 && index < CORES_SIZE)
            {
                if (!this.mergeItemStack(itemstack1, CORES_SIZE+1, CORES_SIZE+36+1, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if(index >= CORES_SIZE)
            {
                if(!this.mergeItemStack(itemstack1, 0, CORES_SIZE, false))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }

            if (itemstack1.getCount() == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return null;
            }

        }

        return itemstack;
    }
}
