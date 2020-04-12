package mod.mythusteam.mythus.items.polearms;

import mod.mythusteam.mythus.Mythus;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import mod.mythusteam.mythus.items.IExtendedReach;

public abstract class Polearm extends SwordItem implements IExtendedReach
{
    public Polearm(IItemTier tier, int atkDmg, float atkSpd)
    {
        //TODO add parameters? for customization/generalization?
        super(tier, atkDmg, atkSpd, new Item.Properties().group(Mythus.MYTHUS_TAB));
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World world, BlockPos pos, PlayerEntity player)
    {
        return !player.isCreative();
    }
}
