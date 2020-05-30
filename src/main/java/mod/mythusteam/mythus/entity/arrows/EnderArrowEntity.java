package mod.mythusteam.mythus.entity.arrows;

import mod.mythusteam.mythus.init.MythusEntities;
import mod.mythusteam.mythus.init.MythusItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EnderArrowEntity extends AbstractArrowEntity
{
    public EnderArrowEntity(EntityType<? extends EnderArrowEntity> type, World world)
    {
        super(type, world);
        this.pickupStatus = PickupStatus.ALLOWED;
    }

    public EnderArrowEntity(LivingEntity shooter, World world)
    {
        super(MythusEntities.ENDER_ARROW.get(), shooter, world);
        this.pickupStatus = PickupStatus.ALLOWED;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(MythusItems.ENDER_ARROW.get());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
