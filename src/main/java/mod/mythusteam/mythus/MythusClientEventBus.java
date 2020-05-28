package mod.mythusteam.mythus;

import mod.mythusteam.mythus.network.MythusCoreStorageGUIPacket;
import mod.mythusteam.mythus.network.MythusPacketHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mythus.MODID, value = Dist.CLIENT)
public class MythusClientEventBus
{
    @SubscribeEvent
    public static void onClientTickEvent(final TickEvent.ClientTickEvent event)
    {
        if (event.phase != TickEvent.Phase.END) return;

        if(Mythus.keyBindings[0].isPressed())
        {
            MythusPacketHandler.INSTANCE.sendToServer(new MythusCoreStorageGUIPacket());
        }
    }
}
