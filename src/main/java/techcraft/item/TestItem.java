package techcraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import techcraft.api.ITechCraftPipe;

/**
 * Created by stefa on 17-4-2016.
 */
public class TestItem extends ItemTechCraft {
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Block block = worldIn.getBlockState(pos).getBlock();
        if(block instanceof ITechCraftPipe){
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
}
