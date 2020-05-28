package mod.mythusteam.mythus.capabilities.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public interface ICoreStorage extends IItemHandler
{
    int getLevel();
    void setLevel(int level);
    void setSize(int size);
}
