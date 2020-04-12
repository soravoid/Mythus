package mod.mythusteam.mythus.render;

import mod.mythusteam.mythus.Mythus;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import mod.mythusteam.mythus.entity.arrows.ExplodingArrowEntity;

public class ExplodingArrowRenderer extends ArrowRenderer<ExplodingArrowEntity>
{
    public ExplodingArrowRenderer(EntityRendererManager renderManager) { super(renderManager); }

    @Override
    public ResourceLocation getEntityTexture(ExplodingArrowEntity entity) {
        return new ResourceLocation(Mythus.MODID, "textures/entity/projectile/exploding_arrow.png");
    }
}
