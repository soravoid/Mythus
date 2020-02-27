package net.mythusteam.mythus.items.weapons;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mythusteam.mythus.Mythus;
import net.mythusteam.mythus.items.MythusToolTiers;
//event listener
@Mod.EventBusSubscriber(modid = Mythus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HellfireSword extends ExceedSword
{
    public HellfireSword()
    {
        //TODO currently same as diamond, find custom values
        super(MythusToolTiers.MYTHUS, 3, -2.4F);
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent e)
    {
        //TODO affect blocks under player's feet
    }
}
