package info.inpureprojects.OpenBees.Common.Blocks;

import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.OpenBees;
import info.inpureprojects.core.API.Block.BlockBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by den on 8/8/2014.
 */
public class BlockHive extends BlockBase {

    public BlockHive(String unloc) {
        super(unloc);
        this.setHasGUI(false);
        this.setIdShift(-1);
        this.setLightLevel(5.0f);
        this.setHarvestLevel("scoop", 1);
    }

    @Override
    public void setup() {
        this.setCreativeTab(OpenBeesAPI.getAPI().getClientAPI().creativeTabBlocks);
        this.setModInstance(OpenBees.instance);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == ForgeDirection.DOWN.ordinal() || side == ForgeDirection.UP.ordinal()) {
            return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("beehive_top");
        }
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("beehive_side");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList();
    }

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        list.add(new ItemStack(this, 1, 0));
    }
}
