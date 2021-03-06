package mod.mythusteam.mythus.capabilities.instances;

import mod.mythusteam.mythus.capabilities.interfaces.IMEnergy;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import mod.mythusteam.mythus.events.MEnergyChangedEvent;

import java.util.Random;

public class MEnergyInstance implements IMEnergy
{
    private int current, max;
    private PlayerEntity player;

    public MEnergyInstance(PlayerEntity player)
    {
        this.max = 300 + new Random().nextInt(701);
        this.current = max;
        this.player = player;
    }

    @Override
    public int getCurrent() { return current; }

    @Override
    public int getMax() { return max; }

    @Override
    public void setCurrent(int val)
    {
        this.current = val;
        this.updateClient();
    }

    @Override
    public void updateClient()
    {
        MinecraftForge.EVENT_BUS.post(new MEnergyChangedEvent(this.player));
    }

    /**
     * Changes the max M-Energy
     * Will auto-adjust the current value to fit the max
     */
    @Override
    public void setMax(int val) {
        this.max = val;
        if(val < current) this.current = val;
        this.updateClient();
    }

    /**
     * Changes the current and max M-Energy without firing an event
     * Generally used in packets and the player cloning event
     */
    @Override
    public void setSilently(int current, int max) {
        this.current = current;
        this.max = max;
    }

    @Override
    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}
