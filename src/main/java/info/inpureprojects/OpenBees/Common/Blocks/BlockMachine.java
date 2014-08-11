package info.inpureprojects.OpenBees.Common.Blocks;

import cofh.api.block.IDismantleable;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileApiary;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.ArrayList;

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
        TileApiary a = (TileApiary) world.getTileEntity(i, i2, i3);
        a.onRemoval();
        world.spawnEntityInWorld(new EntityItem(world, i, i2, i3, new ItemStack(world.getBlock(i, i2, i3), 1, world.getBlockMetadata(i, i2, i3))));
        world.setBlockToAir(i, i2, i3);
        return null;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        TileApiary a = (TileApiary) world.getTileEntity(x, y, z);
        a.onNeighborsChanged();
    }

    @Override
    public boolean canDismantle(EntityPlayer entityPlayer, World world, int i, int i2, int i3) {
        return true;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        switch(meta){
            case 0:
                switch (side) {
                    case 0:
                        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_bottom");
                    case 1:
                        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_top");

                }
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_side");
            case 1:
                break;
        }
        // Just return something here so the client does not crash. Something is horribly wrong if the code makes it down this far.
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_side");
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileApiary a = (TileApiary) world.getTileEntity(x, y, z);
        a.onRemoval();
        world.removeTileEntity(x, y, z);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        switch (meta) {
            case 0:
                return new TileApiary();
        }
        return null;
    }
}
