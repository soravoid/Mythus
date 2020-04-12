package mod.mythusteam.mythus.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class MEnergyChangedEvent extends Event
{
    private PlayerEntity player;

    public MEnergyChangedEvent(PlayerEntity player)
    {
        this.player = player;
    }

    public PlayerEntity getPlayer() { return player; }
}
