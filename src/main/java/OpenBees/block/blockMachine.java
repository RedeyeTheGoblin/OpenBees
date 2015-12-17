package OpenBees.block;

import OpenBees.OpenBees;
import OpenBees.block.tileEntities.IRemoveTile;
import OpenBees.block.tileEntities.tileApiary;
import OpenBees.handler.creativeTabHandler;
import cofh.api.block.IDismantleable;
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

public class blockMachine extends blockBase implements IDismantleable {

    public blockMachine(String unloc)
    {
        super(unloc);
        this.setHasGUI(true);
    }

    @Override
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world,int x, int y, int z, boolean bool)
    {
        IRemoveTile aTile = (IRemoveTile) world.getTileEntity(x, y, z);
        aTile.onRemoval();
        world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z))));
        world.setBlockToAir(x, y, z);
        return null;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        super.onNeighborBlockChange(world, x, y, z, block);
        int meta = world.getBlockMetadata(x, y, z);
        switch (meta)
        {
            case 0:
                tileApiary apiary = (tileApiary) world.getTileEntity(x, y, z);
                apiary.onNeighboursChanged();
                break;
        }
    }

    @Override
    public void setup()
    {
        this.setCreativeTab(creativeTabHandler.creativeTabBlocks);
        this.setModInstance(OpenBees.instance);
    }

    @Override
    public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        switch (meta) {
            case 0:
                switch (side) {
                    case 0:
                        return OpenBees.coreTexHandler.getIcon("apiary_bottom");
                    case 1:
                        return OpenBees.coreTexHandler.getIcon("apiary_top");

                }
                return OpenBees.coreTexHandler.getIcon("apiary_side");
        }
        // Just return something here so the client does not crash. Something is horribly wrong if the code makes it down this far.
        return OpenBees.coreTexHandler.getIcon("apiary_side");
    }

    /*@Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        IRemoveTile aTile = (IRemoveTile) world.getTileEntity(x, y, x);
        aTile.onRemoval();
        world.removeTileEntity(x, y, z);
    }*/

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        switch (meta)
        {
            case 0:
                return new tileApiary();
        }

        return null;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List machines)
    {
        machines.add(new ItemStack(this, 1, 0));
    }

}
