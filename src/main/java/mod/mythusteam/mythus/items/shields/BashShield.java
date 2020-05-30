package mod.mythusteam.mythus.items.shields;

import mod.mythusteam.mythus.Mythus;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.ActionResultType;

public class BashShield extends ShieldItem
{
    public BashShield()
    {
        super(new Item.Properties().group(Mythus.MYTHUS_TAB));
    }
}
