package techcraft.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import techcraft.item.ItemTechCraft;
import techcraft.item.TestItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefa on 17-4-2016.
 */
public class ModItems {

    public static final List<ItemTechCraft> BLOCKS = new ArrayList<>();

    public static ItemTechCraft testItem = new TestItem();

    public static void init() {
        GameRegistry.registerItem(testItem);
    }

    public static void registerModels(){
        BLOCKS.forEach(ItemTechCraft::registerModels);
    }
}
