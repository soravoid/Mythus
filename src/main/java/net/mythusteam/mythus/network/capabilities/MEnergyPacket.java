package net.mythusteam.mythus.network.capabilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.mythusteam.mythus.capabilities.CapabilityMEnergy;

import java.util.function.Supplier;

public class MEnergyPacket
{
    public int current;
    public int max;

    public MEnergyPacket(int current, int max)
    {
        this.current = current;
        this.max = max;
    }

    public static void encode(MEnergyPacket pkt, PacketBuffer buf)
    {
        buf.writeInt(pkt.current);
        buf.writeInt(pkt.max);
    }

    public static MEnergyPacket decode(PacketBuffer buf) { return new MEnergyPacket(buf.readInt(), buf.readInt()); }

    public static void handle(MEnergyPacket pkt, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection().getReceptionSide().isClient())
        {
            ctx.get().enqueueWork(() -> {
                ClientPlayerEntity player = Minecraft.getInstance().player;
                player.getCapability(CapabilityMEnergy.M_ENERGY_CAPABILITY).ifPresent(cap -> {
                    cap.setSilently(pkt.current, pkt.max);
                    cap.setPlayer(player);
                });
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
