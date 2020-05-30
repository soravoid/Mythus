package mod.mythusteam.mythus.init;

import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.entity.arrows.EnderArrowEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import mod.mythusteam.mythus.entity.arrows.ExplodingArrowEntity;

public class MythusEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Mythus.MODID);

    //Arrows
    public static final RegistryObject<EntityType<ExplodingArrowEntity>> EXPLODING_ARROW = ENTITY_TYPES.register("exploding_arrow",
            () -> EntityType.Builder.<ExplodingArrowEntity>create(ExplodingArrowEntity::new, EntityClassification.MISC).build(new ResourceLocation(Mythus.MODID, "exploding_arrow").toString()));
    public static final RegistryObject<EntityType<EnderArrowEntity>> ENDER_ARROW = ENTITY_TYPES.register("ender_arrow",
            () -> EntityType.Builder.<EnderArrowEntity>create(EnderArrowEntity::new, EntityClassification.MISC).build(new ResourceLocation(Mythus.MODID, "ender_arrow").toString()));
}
