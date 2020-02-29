package net.mythusteam.mythus.items.weapons;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mythusteam.mythus.Mythus;
import net.mythusteam.mythus.items.MythusToolTiers;

@Mod.EventBusSubscriber(modid = Mythus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HellfireSword extends ExceedSword
{
    public HellfireSword()
    {
        //TODO currently same as diamond, find custom values
        super(MythusToolTiers.MYTHUS, 3, -2.4F);
    }

    @SubscribeEvent
    public static void onLivingHurt(AttackEntityEvent e)
    {
        //TODO Effect of Hellfire Sword on attacked entity (Make sure it's at full charge)
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent e)
    {
        if(e.player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof HellfireSword)
        {
            BlockPos pos = new BlockPos(new BlockPos(e.player.getPosX(), e.player.getPosY() - 1, e.player.getPosZ()));
            BlockState state = e.player.world.getBlockState(pos);
            if(state == Blocks.GRASS_BLOCK.getDefaultState()) e.player.world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            else if(state.getBlock() == Blocks.SAND || state.getBlock() == Blocks.RED_SAND) e.player.world.setBlockState(pos, Blocks.GLASS.getDefaultState());
            else if(state.getBlock() == Blocks.CLAY) e.player.world.setBlockState(pos, Blocks.TERRACOTTA.getDefaultState());
            else if(state.getBlock() == Blocks.COBBLESTONE) e.player.world.setBlockState(pos, Blocks.STONE.getDefaultState());
            else if(state.getBlock() == Blocks.WET_SPONGE) e.player.world.setBlockState(pos, Blocks.SPONGE.getDefaultState());
        }
    }
}
