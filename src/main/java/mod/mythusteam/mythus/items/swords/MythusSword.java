package mod.mythusteam.mythus.items.swords;

import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.capabilities.CapabilitySmithQuality;
import mod.mythusteam.mythus.utils.IHasQuality;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/*
Base for all of the mod's sword
 */
public abstract class MythusSword extends SwordItem implements IHasQuality
{
    public MythusSword(IItemTier tier, int attackDamage, float attackSpeed) { super(tier, attackDamage, attackSpeed, new Item.Properties().group(Mythus.MYTHUS_TAB)); }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
        super.addInformation(stack, world, list, flag);
        stack.getCapability(CapabilitySmithQuality.SMITH_QUALITY_CAPABILITY).ifPresent(cap -> {
            list.add(new StringTextComponent(String.format("Quality: %s", cap.getQuality().toString())));
        });
    }
}
