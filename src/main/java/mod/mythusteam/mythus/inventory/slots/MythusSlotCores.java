package mod.mythusteam.mythus.inventory.slots;

import mod.mythusteam.mythus.items.cores.MythusCore;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class MythusSlotCores extends SlotItemHandler
{
    //private CoreType coreType;

    public MythusSlotCores(IItemHandler inv, int index, int x, int y/*, CoreType coreType*/)
    {
        super(inv, index, x, y);
        //this.coreType = coreType;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return stack.getItem() instanceof MythusCore;
    }

    //public CoreType getCoreType() { return coreType; }
}
