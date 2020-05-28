package mod.mythusteam.mythus.inventory.providers;

import mod.mythusteam.mythus.capabilities.CapabilityCoreStorage;
import mod.mythusteam.mythus.inventory.CoreStorageContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public class CoreStorageContainerProvider implements INamedContainerProvider
{
    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Elemental Cores");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return player.getCapability(CapabilityCoreStorage.CORE_STORAGE_CAPABILITY).map(cap -> new CoreStorageContainer(id, inv, cap)).orElse(null);
    }
}
