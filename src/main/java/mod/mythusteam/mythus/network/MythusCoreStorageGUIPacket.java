package mod.mythusteam.mythus.network;

import mod.mythusteam.mythus.capabilities.CapabilityCoreStorage;
import mod.mythusteam.mythus.inventory.providers.CoreStorageContainerProvider;
import mod.mythusteam.mythus.items.IExtendedReach;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Supplier;

public class MythusCoreStorageGUIPacket
{
    public MythusCoreStorageGUIPacket() { ; }

    public static MythusCoreStorageGUIPacket decode(PacketBuffer buf) { return new MythusCoreStorageGUIPacket(); }

    public static void encode(MythusCoreStorageGUIPacket pkt, PacketBuffer buf) {  }

    public static void handle(MythusCoreStorageGUIPacket pkt, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection().getReceptionSide().isServer())
        {
            ctx.get().enqueueWork(() -> {
                PlayerEntity player = ctx.get().getSender();
                player.getCapability(CapabilityCoreStorage.CORE_STORAGE_CAPABILITY).ifPresent(cap -> {
                    NetworkHooks.openGui((ServerPlayerEntity) player, new CoreStorageContainerProvider());
                });
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
