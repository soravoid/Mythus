package mod.mythusteam.mythus.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class MythusItemGroups
{
    public static class MYTHUS_GROUP extends ItemGroup
    {
        private final Supplier<ItemStack> iconSupplier;

        public MYTHUS_GROUP(final String name, final Supplier<ItemStack> iconSupplier)
        {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon() { return this.iconSupplier.get(); }
    }
}
