package OpenBees.genetics;

import OpenBees.item.interfaces.IFrameItem;
import cofh.lib.util.position.BlockPosition;
import net.minecraft.world.World;

import java.util.List;

public interface IBeeKeepingTile {

    public List<BlockPosition> getSurroundingBlocks();

    public List<BlockPosition> getSurroundingBlocks(int distance);

    public List<IFrameItem> getFrames();

    public IBee getQueen();

    public IBee getDrone();

    public World getWorld();

    public BlockPosition getPosition();

    public void onNeighboursChanged();
}
