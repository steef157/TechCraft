package techcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import techcraft.api.ITechCraftPipe;

import java.util.List;

/**
 * Created by stefa on 2-4-2016.
 */
public class BlockPipe extends BlockTechCraft implements ITechCraftPipe {

    private static final String NAME = "pipe";

    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool STRAIGHT = PropertyBool.create("straight");
    public static final PropertyEnum COLOR = PropertyEnum.create("color", EnumDyeColor.class);

    public BlockPipe() {
        super(NAME);
        setDefaultState(this.blockState.getBaseState().withProperty(SOUTH, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(WEST, false).withProperty(UP, false).withProperty(DOWN, false).withProperty(STRAIGHT, false).withProperty(COLOR, EnumDyeColor.BLACK));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SOUTH, NORTH, EAST, WEST, UP, DOWN, STRAIGHT, COLOR);
    }
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {

        boolean south = canConnect(world, pos.south());
        boolean north = canConnect(world, pos.north());
        boolean east = canConnect(world, pos.east());
        boolean west = canConnect(world, pos.west());
        boolean up = canConnect(world, pos.up());
        boolean down = canConnect(world, pos.down());
        boolean straight = isStraight(south, north, east, west, up, down);

        return state
                .withProperty(SOUTH, south)
                .withProperty(NORTH, north)
                .withProperty(EAST, east)
                .withProperty(WEST, west)
                .withProperty(UP, up)
                .withProperty(DOWN, down)
                .withProperty(STRAIGHT, straight)
                .withProperty(COLOR, EnumDyeColor.BLACK);
    }

    private boolean isStraight(boolean south, boolean north, boolean east, boolean west, boolean up, boolean down) {
        if(south && north)
            return !(east || west || up || down);
        if(east && west)
            return !(south || north || up || down);
        if(up && down)
            return !(south || north || east || west);
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
        return blockState.getBoundingBox(worldIn, pos);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        float   x1 = 5f / 16f,
                x2 = 1.0f - x1,
                y1 = x1,
                y2 = 1.0f - y1,
                z1 = x1,
                z2 = 1.0f - z1;
        if(canConnect(source, pos.west())){
            x1 = 0f;
        }
        if(canConnect(source, pos.east())){
            x2 = 1.0f;
        }
        if(canConnect(source, pos.down())){
            y1 = 0.0f;
        }
        if(canConnect(source, pos.up())){
            y2 = 1.0f;
        }
        if(canConnect(source, pos.north())){
            z1 = 0.0f;
        }
        if(canConnect(source, pos.south())){
            z2 = 1.0f;
        }
        return new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB box, List<AxisAlignedBB> list, Entity entity) {
        this.getBoundingBox(state, world, pos);
        super.addCollisionBoxToList(state, world, pos, box, list, entity);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isTranslucent(IBlockState state) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean canRenderInLayer(BlockRenderLayer layer) {
        return layer == BlockRenderLayer.SOLID;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }

    public boolean canConnect(IBlockAccess world, BlockPos pos){
        return world.getBlockState(pos).getBlock() instanceof ITechCraftPipe;
    }
}
