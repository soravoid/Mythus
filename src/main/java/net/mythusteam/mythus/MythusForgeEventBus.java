package net.mythusteam.mythus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;
import net.mythusteam.mythus.capabilities.CapabilityMEnergy;
import net.mythusteam.mythus.capabilities.CapabilitySmithQuality;
import net.mythusteam.mythus.events.MEnergyChangedEvent;
import net.mythusteam.mythus.network.MythusPacketHandler;
import net.mythusteam.mythus.network.capabilities.MEnergyPacket;
import net.mythusteam.mythus.render.RenderMEnergyBar;
import net.mythusteam.mythus.utils.IHasQuality;

@Mod.EventBusSubscriber(modid = Mythus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MythusForgeEventBus
{
    @SubscribeEvent
    public static void onItemStackAttachCapability(AttachCapabilitiesEvent<ItemStack> e)
    {
        if(e.getObject().getItem() instanceof IHasQuality) e.addCapability(CapabilitySmithQuality.LOCATION, new CapabilitySmithQuality());
    }

    @SubscribeEvent
    public static void onEntityAttachCapability(AttachCapabilitiesEvent<Entity> e)
    {
        if(e.getObject() instanceof PlayerEntity)
        {
            e.addCapability(CapabilityMEnergy.LOCATION, new CapabilityMEnergy());
            e.getObject().getCapability(CapabilityMEnergy.M_ENERGY_CAPABILITY).ifPresent(cap -> cap.setPlayer((PlayerEntity) e.getObject()));
        }
    }

    @SubscribeEvent
    public static void onMEnergyChanged(MEnergyChangedEvent e)
    {
        if(!e.getPlayer().getEntityWorld().isRemote)
        {
            e.getPlayer().getCapability(CapabilityMEnergy.M_ENERGY_CAPABILITY).ifPresent(cap -> {
                MythusPacketHandler.INSTANCE.sendTo(new MEnergyPacket(cap.getCurrent(), cap.getMax()), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
                RenderMEnergyBar.timeSinceLastUpdate = 0;
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e)
    {
        e.getPlayer().getCapability(CapabilityMEnergy.M_ENERGY_CAPABILITY).ifPresent(cap -> {
            cap.setPlayer(e.getPlayer());
            cap.updateClient();
        });
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent e)
    {
        e.getPlayer().getCapability(CapabilityMEnergy.M_ENERGY_CAPABILITY).ifPresent(cap -> {
            cap.setPlayer(e.getPlayer());
            cap.updateClient();
        });
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone e)
    {
        if(e.isWasDeath())
        {
            e.getPlayer().getCapability(CapabilityMEnergy.M_ENERGY_CAPABILITY).ifPresent(cap1 -> {
                e.getOriginal().getCapability(CapabilityMEnergy.M_ENERGY_CAPABILITY).ifPresent(cap2 -> {
                    cap1.setSilently(cap2.getMax(), cap2.getMax());
                });
            });
        }
    }
}
