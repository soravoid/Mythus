package net.mythusteam.mythus.capabilities.instances;

import net.mythusteam.mythus.capabilities.interfaces.ISmithQuality;
import net.mythusteam.mythus.utils.SmithQuality;

import java.util.Random;

public class SmithQualityInstance implements ISmithQuality
{
    private SmithQuality quality;

    public SmithQualityInstance()
    {
        //TODO Weight the randomness
        this.quality = SmithQuality.values()[new Random().nextInt(SmithQuality.values().length)];
    }

    public SmithQualityInstance(SmithQuality quality) { this.quality = quality; }

    @Override
    public SmithQuality getQuality() { return this.quality; }

    @Override
    public void setQuality(SmithQuality quality) { this.quality = quality; }
}
