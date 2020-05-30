package mod.mythusteam.mythus.items.cores;

import mod.mythusteam.mythus.Mythus;
import net.minecraft.item.Item;

public class MythusCore extends Item
{
    public MythusCore()
    {
        super(new Item.Properties().group(Mythus.MYTHUS_TAB).maxStackSize(1));
    }
}
