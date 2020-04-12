package mod.mythusteam.mythus.init;

import mod.mythusteam.mythus.Mythus;
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

    public static final RegistryObject<EntityType<ExplodingArrowEntity>> EXPLODING_ARROW = ENTITY_TYPES.register("exploding_arrow",
            () -> EntityType.Builder.<ExplodingArrowEntity>create(ExplodingArrowEntity::new, EntityClassification.MISC).build(new ResourceLocation(Mythus.MODID, "exploding_arrow").toString()));
}
