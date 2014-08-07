package info.inpureprojects.OpenBees.Common.Blocks;

import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileApiary;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by den on 8/7/2014.
 */
public class BlockMachine extends BlockBase {

    public BlockMachine(String unloc) {
        super(unloc);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 0:
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_bottom");
            case 1:
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_top");

        }
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("apiary_side");
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
