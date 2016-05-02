package techcraft.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import techcraft.block.BlockTechCraft;
import techcraft.block.BlockPipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefa on 2-4-2016.
 */
public class ModBlocks {

    public static final List<BlockTechCraft> BLOCKS = new ArrayList<>();

    public static BlockTechCraft testBlock = new BlockPipe();

    public static void init() {
        GameRegistry.registerBlock(testBlock);
    }

    public static void registerModels(){
        BLOCKS.forEach(BlockTechCraft::registerModels);
    }
}
