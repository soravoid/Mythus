package mod.mythusteam.mythus.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.network.capabilities.MEnergyPacket;

public class MythusPacketHandler
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Mythus.MODID, "main_simple"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION:: equals
    );

    public static void init()
    {
        int id = 0;

        INSTANCE.registerMessage(id++, MEnergyPacket.class, MEnergyPacket::encode, MEnergyPacket::decode, MEnergyPacket::handle);
        INSTANCE.registerMessage(id++, ExtendedReachPacket.class, ExtendedReachPacket::encode, ExtendedReachPacket::decode, ExtendedReachPacket::handle);
    }
}
