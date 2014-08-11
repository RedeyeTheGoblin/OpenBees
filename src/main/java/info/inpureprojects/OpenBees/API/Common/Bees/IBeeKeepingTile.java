package info.inpureprojects.OpenBees.API.Common.Bees;

import cofh.lib.util.position.BlockPosition;
import info.inpureprojects.OpenBees.API.Common.Tools.IFrameItem;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by den on 8/10/2014.
 */
public interface IBeeKeepingTile {

    public List<BlockPosition> getSurroundingBlocks();

    public List<IFrameItem> getFrames();

    public IBee getQueen();

    public IBee getDrone();

    public World getWorld();

    public BlockPosition getPosition();

    public void onNeighborsChanged();

}
