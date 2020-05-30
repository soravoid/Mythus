package mod.mythusteam.mythus.render;

import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.entity.arrows.EnderArrowEntity;
import mod.mythusteam.mythus.entity.arrows.ExplodingArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class EnderArrowRenderer extends ArrowRenderer<EnderArrowEntity>
{
    public EnderArrowRenderer(EntityRendererManager renderManager) { super(renderManager); }

    @Override
    public ResourceLocation getEntityTexture(EnderArrowEntity entity) {
        return new ResourceLocation(Mythus.MODID, "textures/entity/projectile/ender_arrow.png");
    }
}
