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

public class ExplodingArrowEntity extends AbstractArrowEntity
{
    public int age = 0; //in ticks

    public ExplodingArrowEntity(EntityType<? extends ExplodingArrowEntity> type, World world)
    {
        super(type, world);
        this.pickupStatus = PickupStatus.DISALLOWED;
    }

    public ExplodingArrowEntity(LivingEntity shooter, World world)
    {
        super(MythusEntities.EXPLODING_ARROW.get(), shooter, world);
        this.pickupStatus = PickupStatus.DISALLOWED;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(MythusItems.EXPLODING_ARROW.get());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
