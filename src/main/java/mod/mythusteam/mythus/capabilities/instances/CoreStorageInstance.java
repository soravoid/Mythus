package mod.mythusteam.mythus.capabilities.instances;

import mod.mythusteam.mythus.capabilities.interfaces.ICoreStorage;
import mod.mythusteam.mythus.items.cores.MythusCore;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class CoreStorageInstance extends ItemStackHandler implements ICoreStorage
{
    int level;

    public CoreStorageInstance()
    {
        super(5); //TODO Change?
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return stack.getItem() instanceof MythusCore;
    }
}
