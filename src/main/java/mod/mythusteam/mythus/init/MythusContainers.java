package mod.mythusteam.mythus.init;

import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.capabilities.CapabilityCoreStorage;
import mod.mythusteam.mythus.inventory.CoreStorageContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythusContainers
{
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Mythus.MODID);

    public static final RegistryObject<ContainerType<CoreStorageContainer>> CORE_STORAGE_CONTAINER = CONTAINERS.register("core_storage_container",
            () -> new ContainerType<>((id, inv) -> new CoreStorageContainer(id, inv, inv.player.getCapability(CapabilityCoreStorage.CORE_STORAGE_CAPABILITY).orElseThrow(() -> new NullPointerException("Core Storage Capability not found!")))));

}
