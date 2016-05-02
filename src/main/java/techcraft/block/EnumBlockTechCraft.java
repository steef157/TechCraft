package techcraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by stefa on 5-4-2016.
 */
public class EnumBlockTechCraft<T extends Enum<T> & EnumBlockTechCraft.IEnumMeta & IStringSerializable> extends BlockTechCraft {

    private final T[] variants;

    public EnumBlockTechCraft(String name, Material material, Class<T> clazz) {
        super(name, material);
        this.variants = clazz.getEnumConstants();
    }

    public EnumBlockTechCraft(String name, Class<T> clazz){
        this(name, Material.rock, clazz);
    }

    protected T fromMeta(int meta){
        if(meta < 0 || meta > variants.length)
            meta = 0;
        return variants[0];
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for(T variant : variants)
            list.add(new ItemStack(this, 1, variant.getMeta()));
    }

    public interface IEnumMeta extends IStringSerializable{
        int getMeta();

        default String getName() {
            return ((Enum)this).name().toLowerCase();
        }
    }
}
