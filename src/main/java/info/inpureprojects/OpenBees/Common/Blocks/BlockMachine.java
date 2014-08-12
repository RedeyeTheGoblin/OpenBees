package info.inpureprojects.OpenBees.Common.Blocks;

import cofh.api.block.IDismantleable;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileApiary;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by den on 8/7/2014.
 */
public class BlockMachine extends BlockBase implements IDismantleable {

    public BlockMachine(String unloc) {
        super(unloc);
        this.setHasGUI(true);
    }

    @Override
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer entityPlayer, World world, int i, int i2, int i3, boolean b) {
        int meta = world.getBlockMetadata(i, i2, i3);
        switch (meta) {
            case 0:
                TileApiary a = (TileApiary) world.getTileEntity(i, i2, i3);
                a.onRemoval();
                world.spawnEntityInWorld(new EntityItem(world, i, i2, i3, new ItemStack(world.getBlock(i, i2, i3), 1, world.getBlockMetadata(i, i2, i3))));
                world.setBlockToAir(i, i2, i3);
                break;
        }
        return null;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        int meta = world.getBlockMetadata(x, y, z);
        switch (meta) {
            case 0:
                TileApiary a = (TileApiary) world.getTileEntity(x, y, z);
                a.onNeighborsChanged();
                break;
        }
    }

    @Override
    public boolean canDismantle(EntityPlayer entityPlayer, World world, int i, int i2, int i3) {
        return true;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        switch (meta) {
            case 0:
                switch (side) {
                    case 0:
                        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_bottom");
                    case 1:
                        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_top");

                }
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_side");
            case 1:
                switch (side) {
                    case 0:
                        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("forestfire_machine_bottom");
                    case 1:
                        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("carpenter_top_off");
                }
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("carpenter_side_off");
        }
        // Just return something here so the client does not crash. Something is horribly wrong if the code makes it down this far.
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_side");
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        switch (meta) {
            case 0:
                TileApiary a = (TileApiary) world.getTileEntity(x, y, z);
                a.onRemoval();
                world.removeTileEntity(x, y, z);
                break;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        switch (meta) {
            case 0:
                return new TileApiary();
        }
        return null;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        list.add(new ItemStack(this, 1, 0));
        list.add(new ItemStack(this, 1, 1));
    }
}
