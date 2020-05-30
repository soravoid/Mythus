package mod.mythusteam.mythus.inventory;

import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.capabilities.interfaces.ICoreStorage;
import mod.mythusteam.mythus.init.MythusContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
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

        //TODO Change texture locations after new texture
        for(int i = 0; i < cores.getSlots(); i++)
        {
            this.addSlot(new SlotItemHandler(cores, i, slotCoords.get(i).left, slotCoords.get(i).right));
        }

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 132 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
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

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(index > CORES_SIZE - 1)
            {
                if(!this.mergeItemStack(itemstack1, 0, CORES_SIZE, false))
                {
                    return ItemStack.EMPTY;
                }
                else if(!this.mergeItemStack(itemstack1, CORES_SIZE, this.inventorySlots.size(), false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if(!this.mergeItemStack(itemstack1, CORES_SIZE, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }

            if(itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}
