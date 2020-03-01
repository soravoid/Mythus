package net.mythusteam.mythus.capabilities.interfaces;

import net.minecraft.entity.player.PlayerEntity;

public interface IMEnergy
{
    int getCurrent();
    int getMax();

    void setCurrent(int val);
    void setMax(int val);

    void updateClient();

    //Dangerous!
    void setSilently(int current, int max);
    void setPlayer(PlayerEntity player);
}
