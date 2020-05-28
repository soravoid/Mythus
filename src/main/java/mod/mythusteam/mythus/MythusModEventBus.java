package mod.mythusteam.mythus;

import mod.mythusteam.mythus.init.MythusBlocks;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;

@Mod.EventBusSubscriber(modid = Mythus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MythusModEventBus
{
    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> e)
    {
        MythusBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(Mythus.MYTHUS_TAB);
            final BlockItem blockItem = new BlockItem(block, properties);
            e.getRegistry().register(setup(blockItem, block.getRegistryName()));
        });
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name)
    {
        return setup(entry, new ResourceLocation(Mythus.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName)
    {
        entry.setRegistryName(registryName);
        return entry;
    }
}
