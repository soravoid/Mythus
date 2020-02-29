package net.mythusteam.mythus;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.mythusteam.mythus.capabilities.CapabilityMEnergy;
import net.mythusteam.mythus.capabilities.CapabilitySmithQuality;
import net.mythusteam.mythus.capabilities.instances.MEnergyInstance;
import net.mythusteam.mythus.capabilities.instances.SmithQualityInstance;
import net.mythusteam.mythus.capabilities.interfaces.IMEnergy;
import net.mythusteam.mythus.capabilities.interfaces.ISmithQuality;
import net.mythusteam.mythus.init.MythusBlocks;
import net.mythusteam.mythus.init.MythusItemGroups;
import net.mythusteam.mythus.init.MythusItems;
import net.mythusteam.mythus.network.MythusPacketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Mythus.MODID)
public class Mythus
{
    public static final String MODID = "mythus";
    private static final Logger LOG = LogManager.getLogger(MODID);

    public static final ItemGroup MYTHUS_TAB = new MythusItemGroups.MYTHUS_GROUP(Mythus.MODID, () -> new ItemStack(Items.STICK));

    public Mythus()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MythusItems.ITEMS.register(modEventBus);
        MythusBlocks.BLOCKS.register(modEventBus);
        modEventBus.addListener(this::onCommonSetup);
    }

    public void onCommonSetup(FMLCommonSetupEvent e)
    {
        MythusPacketHandler.init();
        CapabilityManager.INSTANCE.register(ISmithQuality.class, new CapabilitySmithQuality.Storage(), SmithQualityInstance::new);
        CapabilityManager.INSTANCE.register(IMEnergy.class, new CapabilityMEnergy.Storage(), () -> new MEnergyInstance(null));
    }
}
