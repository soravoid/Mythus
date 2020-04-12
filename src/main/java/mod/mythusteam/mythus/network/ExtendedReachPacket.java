package mod.mythusteam.mythus.network;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;
import mod.mythusteam.mythus.items.IExtendedReach;

import java.util.function.Supplier;

public class ExtendedReachPacket
{
    public int ent;

    public ExtendedReachPacket(int entId) { this.ent = entId; }

    public static ExtendedReachPacket decode(PacketBuffer buf) { return new ExtendedReachPacket(buf.readInt()); }

    public static void encode(ExtendedReachPacket pkt, PacketBuffer buf) { buf.writeInt(pkt.ent); }

    public static void handle(ExtendedReachPacket pkt, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection().getReceptionSide().isServer())
        {
            ctx.get().enqueueWork(() -> {
                PlayerEntity player = ctx.get().getSender();
                Entity ent = player.getEntityWorld().getEntityByID(pkt.ent);
                if(player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof IExtendedReach)
                {
                    if(ent.getDistanceSq(player) < Math.pow(((IExtendedReach) player.getHeldItem(Hand.MAIN_HAND).getItem()).getReach(), 2)) player.attackTargetEntityWithCurrentItem(ent);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
