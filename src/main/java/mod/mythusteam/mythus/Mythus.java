package mod.mythusteam.mythus;

import mod.mythusteam.mythus.capabilities.CapabilityMEnergy;
import mod.mythusteam.mythus.capabilities.instances.MEnergyInstance;
import mod.mythusteam.mythus.capabilities.instances.SmithQualityInstance;
import mod.mythusteam.mythus.capabilities.interfaces.IMEnergy;
import mod.mythusteam.mythus.init.MythusBlocks;
import mod.mythusteam.mythus.init.MythusItemGroups;
import mod.mythusteam.mythus.init.MythusItems;
import mod.mythusteam.mythus.network.MythusPacketHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import mod.mythusteam.mythus.capabilities.CapabilitySmithQuality;
import mod.mythusteam.mythus.capabilities.interfaces.ISmithQuality;
import mod.mythusteam.mythus.init.MythusEntities;
import mod.mythusteam.mythus.render.ExplodingArrowRenderer;
import mod.mythusteam.mythus.render.RenderMEnergyBar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Mythus.MODID)
public class Mythus
{
    public static final String MODID = "mythus";
    public static final Logger LOG = LogManager.getLogger(MODID);

    public static final ItemGroup MYTHUS_TAB = new MythusItemGroups.MYTHUS_GROUP(Mythus.MODID, () -> new ItemStack(Items.STICK));

    public Mythus()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MythusItems.ITEMS.register(modEventBus);
        MythusBlocks.BLOCKS.register(modEventBus);
        MythusEntities.ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onClientSetup);
    }

    public void onClientSetup(FMLClientSetupEvent e)
    {
        RenderingRegistry.registerEntityRenderingHandler(MythusEntities.EXPLODING_ARROW.get(), ExplodingArrowRenderer::new);
    }

    public void onCommonSetup(FMLCommonSetupEvent e)
    {
        MythusPacketHandler.init();
        CapabilityManager.INSTANCE.register(ISmithQuality.class, new CapabilitySmithQuality.Storage(), SmithQualityInstance::new);
        CapabilityManager.INSTANCE.register(IMEnergy.class, new CapabilityMEnergy.Storage(), () -> new MEnergyInstance(null));
        MinecraftForge.EVENT_BUS.register(new RenderMEnergyBar());
    }
}
