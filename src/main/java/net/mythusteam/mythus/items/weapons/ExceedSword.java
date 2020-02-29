package net.mythusteam.mythus.items.weapons;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.mythusteam.mythus.Mythus;
import net.mythusteam.mythus.utils.IHasQuality;

public class ExceedSword extends SwordItem implements IHasQuality
{
    public ExceedSword(IItemTier tier, int attackDamage, float attackSpeed)
    {
        super(tier, attackDamage, attackSpeed, new Item.Properties().group(Mythus.MYTHUS_TAB));
    }
}
