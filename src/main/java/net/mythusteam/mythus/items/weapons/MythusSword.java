package net.mythusteam.mythus.items.weapons;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.mythusteam.mythus.Mythus;
import net.mythusteam.mythus.utils.IHasQuality;

/*
Base for all of the mod's sword
 */
public class MythusSword extends SwordItem implements IHasQuality
{
    public MythusSword(IItemTier tier, int attackDamage, float attackSpeed)
    {
        super(tier, attackDamage, attackSpeed, new Item.Properties().group(Mythus.MYTHUS_TAB));
    }
}
