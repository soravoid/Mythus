package net.mythusteam.mythus;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mythusteam.mythus.init.MythusBlocks;
import net.mythusteam.mythus.init.MythusItemGroups;
import net.mythusteam.mythus.init.MythusItems;

@Mod(Mythus.MODID)
public class Mythus
{
    public static final String MODID = "mythus";

    public static final ItemGroup MYTHUS_TAB = new MythusItemGroups.MYTHUS_GROUP(Mythus.MODID, () -> new ItemStack(Items.STICK));

    public Mythus()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MythusItems.ITEMS.register(modEventBus);
        MythusBlocks.BLOCKS.register(modEventBus);
    }
}
