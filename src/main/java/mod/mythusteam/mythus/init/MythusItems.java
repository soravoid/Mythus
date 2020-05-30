package mod.mythusteam.mythus.init;

import mod.mythusteam.mythus.items.arrows.EnderArrowItem;
import mod.mythusteam.mythus.items.axes.SmallAxe;
import mod.mythusteam.mythus.items.cores.MythusCore;
import mod.mythusteam.mythus.items.polearms.Halberd;
import mod.mythusteam.mythus.items.swords.BasicSword;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.items.arrows.ExplodingArrowItem;
import mod.mythusteam.mythus.items.polearms.VortexPolearm;
import mod.mythusteam.mythus.items.swords.HellfireSword;
import mod.mythusteam.mythus.items.swords.ScimitarTest;

import java.rmi.registry.Registry;

public class MythusItems
{
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Mythus.MODID);

    public static final RegistryObject<Item> HELLFIRE_SWORD = ITEMS.register("hellfire_sword", HellfireSword::new);
    public static final RegistryObject<Item> VORTEX_POLEARM = ITEMS.register("vortex_polearm", VortexPolearm::new);
    public static final RegistryObject<Item> MYTHUS_LANCE = ITEMS.register("mythus_lance", () -> new Item(new Item.Properties().group(Mythus.MYTHUS_TAB)));
    public static final RegistryObject<Item> SCIMITARTEST = ITEMS.register("scimitar", () -> new ScimitarTest());
    public static final RegistryObject<Item> SMALL_AXE = ITEMS.register("small_axe", () -> new SmallAxe());
    public static final RegistryObject<Item> HALBERD = ITEMS.register("halberd", Halberd::new);
    public static final RegistryObject<Item> BASIC_SWORD = ITEMS.register("basic_sword", BasicSword::new);

    public static final RegistryObject<Item> TEST_CORE = ITEMS.register("test_core", MythusCore::new);

    //Arrows
    public static final RegistryObject<Item> EXPLODING_ARROW = ITEMS.register("exploding_arrow", ExplodingArrowItem::new);
    public static final RegistryObject<Item> ENDER_ARROW = ITEMS.register("ender_arrow", EnderArrowItem::new);
}
