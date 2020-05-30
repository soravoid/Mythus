package mod.mythusteam.mythus.items.arrows;

import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.entity.arrows.EnderArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderArrowItem extends ArrowItem
{
    public EnderArrowItem()
    {
        super(new Item.Properties().group(Mythus.MYTHUS_TAB));
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity ent) { return new EnderArrowEntity(ent, world); }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
        return false;
    }
}
