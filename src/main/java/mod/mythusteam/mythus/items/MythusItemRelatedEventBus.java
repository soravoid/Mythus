package mod.mythusteam.mythus.items;

import mod.mythusteam.mythus.network.MythusPacketHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import mod.mythusteam.mythus.Mythus;
import mod.mythusteam.mythus.items.swords.HellfireSword;
import mod.mythusteam.mythus.network.ExtendedReachPacket;

@Mod.EventBusSubscriber(modid = Mythus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MythusItemRelatedEventBus
{
    //private static final AttributeModifier modi = new AttributeModifier(UUID.nameUUIDFromBytes("Mythus Reach Modifier".getBytes()), () -> "Mythus Reach Modifier", 10, AttributeModifier.Operation.ADDITION);

    @SubscribeEvent
    public static void onLivingHurt(AttackEntityEvent e)
    {
        PlayerEntity player = e.getPlayer();
        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
        if(stack.getItem() instanceof HellfireSword)
        {
            if (player.getCooledAttackStrength(0) == 1.0f)
            {
                Entity ent = e.getTarget();
                ent.setFire(ent.getFireTimer() + 15);
                if(ent.getFireTimer() > 0) ent.attackEntityFrom(DamageSource.causePlayerDamage(player), 3);
            }
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent e)
    {
        ItemStack stack = e.player.getHeldItem(Hand.MAIN_HAND);
        //if(!(stack.getItem() instanceof Polearm)) e.player.getAttribute(PlayerEntity.REACH_DISTANCE).removeModifier(modi);
        if(stack.getItem() instanceof HellfireSword)
        {
            BlockPos pos = new BlockPos(new BlockPos(e.player.getPosX(), e.player.getPosY() - 1, e.player.getPosZ()));
            BlockState state = e.player.world.getBlockState(pos);
            if(state == Blocks.GRASS_BLOCK.getDefaultState()) e.player.world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            else if(state.getBlock() == Blocks.SAND || state.getBlock() == Blocks.RED_SAND) e.player.world.setBlockState(pos, Blocks.GLASS.getDefaultState());
            else if(state.getBlock() == Blocks.CLAY) e.player.world.setBlockState(pos, Blocks.TERRACOTTA.getDefaultState());
            else if(state.getBlock() == Blocks.COBBLESTONE) e.player.world.setBlockState(pos, Blocks.STONE.getDefaultState());
            else if(state.getBlock() == Blocks.WET_SPONGE) e.player.world.setBlockState(pos, Blocks.SPONGE.getDefaultState());
        }
        /* TODO until forge bug fixed?
        else if(stack.getItem() instanceof Polearm)
        {
            if(!e.player.getAttribute(PlayerEntity.REACH_DISTANCE).hasModifier(modi)) e.player.getAttribute(PlayerEntity.REACH_DISTANCE).applyModifier(modi);
        }
        */
    }

   @SubscribeEvent
    public static void onLeftClick(PlayerInteractEvent.LeftClickEmpty e)
    {
        Mythus.LOG.info("Left Click Detected!");
        if(e.getItemStack().getItem() instanceof IExtendedReach)
        {
            double dist = ((IExtendedReach) e.getPlayer().getHeldItem(Hand.MAIN_HAND).getItem()).getReach();
            Vec3d startV = e.getPlayer().getEyePosition(1f);
            Vec3d endV = startV.add(e.getPlayer().getLookVec().normalize().scale(dist));
            EntityRayTraceResult rayTrace = ProjectileHelper.rayTraceEntities(e.getPlayer(), startV, endV, new AxisAlignedBB(startV, endV), EntityPredicates.NOT_SPECTATING.and(entity -> entity != null), Math.pow(dist, 2));
            Entity ent = rayTrace != null ? rayTrace.getEntity() : null;
            if(ent != null) { MythusPacketHandler.INSTANCE.sendToServer(new ExtendedReachPacket(ent.getEntityId())); }
        }
    }
}
