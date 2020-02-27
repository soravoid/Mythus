package net.mythusteam.mythus.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.mythusteam.mythus.Mythus;
import net.mythusteam.mythus.items.weapons.HellfireSword;

public class MythusItems
{
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Mythus.MODID);

    public static final RegistryObject<Item> HELLFIRE_SWORD = ITEMS.register("hellfire_sword", HellfireSword::new);
}
