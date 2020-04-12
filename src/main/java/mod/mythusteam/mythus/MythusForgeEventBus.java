package mod.mythusteam.mythus;

import mod.mythusteam.mythus.utils.IHasQuality;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;
import mod.mythusteam.mythus.capabilities.CapabilityMEnergy;
import mod.mythusteam.mythus.capabilities.CapabilitySmithQuality;
import mod.mythusteam.mythus.events.MEnergyChangedEvent;
import mod.mythusteam.mythus.entity.arrows.ExplodingArrowEntity;
import mod.mythusteam.mythus.network.MythusPacketHandler;
import mod.mythusteam.mythus.network.capabilities.MEnergyPacket;
import mod.mythusteam.mythus.render.RenderMEnergyBar;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Mythus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MythusForgeEventBus
{
    public static List<IChunk> loadedChunks = new ArrayList<>();

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

    @SubscribeEvent
    public static void onArrowHit(LivingHurtEvent e)
    {
        if(e.getSource().getImmediateSource() instanceof ExplodingArrowEntity)
        {
            ExplodingArrowEntity arrow = (ExplodingArrowEntity) e.getSource().getImmediateSource();
            e.getEntityLiving().world.createExplosion(null, DamageSource.causeExplosionDamage((LivingEntity) arrow.getShooter()), arrow.getPosX(), arrow.getPosY(), arrow.getPosZ(), 1.0f, false, Explosion.Mode.NONE);
        }
    }


    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent e)
    {
        if(e.phase == TickEvent.Phase.START)
        {
            if(!e.world.isRemote)
            {
                ((ServerWorld)e.world).getEntities().filter(entity -> entity instanceof ExplodingArrowEntity).forEach( entity -> {
                    ExplodingArrowEntity arrow = (ExplodingArrowEntity) entity;
                    arrow.age++;
                    if(arrow.age >= 60)
                    {
                        e.world.createExplosion(null, DamageSource.causeExplosionDamage((LivingEntity) arrow.getShooter()), arrow.getPosX(), arrow.getPosY(), arrow.getPosZ(), 1.0f, false, Explosion.Mode.NONE);
                        ((ServerWorld) e.world).removeEntityComplete(entity, false);
                    }
                });
            }
        }
    }
}
