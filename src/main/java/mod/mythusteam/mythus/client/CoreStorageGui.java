package mod.mythusteam.mythus.client;

import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.inventory.CoreStorageContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.opengl.GL11;

public class CoreStorageGui extends ContainerScreen<CoreStorageContainer>
{
    private static final ResourceLocation location = new ResourceLocation(Mythus.MODID, "textures/gui/core_storage.png");

    public CoreStorageGui(CoreStorageContainer container, PlayerInventory inv, ITextComponent title)
    {
        super(container, inv, title);
        this.xSize = 176;
        this.ySize = 214;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().getTextureManager().bindTexture(location);
        int x = (this.width - this.xSize)/2;
        int y = (this.height - this.ySize)/2;
        this.blit(x, y, 0, 0, this.xSize, this.ySize);
    }
}
