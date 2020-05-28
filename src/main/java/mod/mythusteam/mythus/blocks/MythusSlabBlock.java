package mod.mythusteam.mythus.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.util.math.shapes.VoxelShape;

public class MythusSlabBlock extends Block implements IWaterLoggable
{
    public static final VoxelShape NORTH_HALF = Block.makeCuboidShape(0, 0, 0, 16, 16, 8);
    public static final VoxelShape SOUTH_HALF = Block.makeCuboidShape(0, 0, 8, 16, 16, 16);
    public static final VoxelShape WEST_HALF = Block.makeCuboidShape(0, 0, 0, 8, 16, 16);
    public static final VoxelShape EAST_HALF = Block.makeCuboidShape(8, 0, 0, 16, 16, 16);

    public MythusSlabBlock(Properties props)
    {
        super(props);
    }
}
