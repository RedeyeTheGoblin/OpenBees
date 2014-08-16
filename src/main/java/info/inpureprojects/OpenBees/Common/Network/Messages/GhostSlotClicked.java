package info.inpureprojects.OpenBees.Common.Network.Messages;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.IGhostSlotTile;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by den on 8/15/2014.
 */
public class GhostSlotClicked implements IMessage, IMessageHandler<GhostSlotClicked, IMessage> {

    private TileEntity tile;
    private int indexClicked = -1;
    private ItemStack stack = null;
    private boolean isNull = false;

    public GhostSlotClicked() {
    }

    public GhostSlotClicked(TileEntity tile, int indexClicked, ItemStack stack) {
        this.tile = tile;
        this.indexClicked = indexClicked;
        this.stack = stack;
        if (this.stack == null) {
            this.isNull = true;
        }
    }

    @Override
    public IMessage onMessage(GhostSlotClicked message, MessageContext ctx) {
        IGhostSlotTile ghost = (IGhostSlotTile) message.tile;
        ghost.setGhostSlot(message.indexClicked, message.stack);
        return null;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tag = ByteBufUtils.readTag(buf);
        isNull = tag.getBoolean("isNull");
        if (!isNull) {
            stack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("stack"));
        }
        this.indexClicked = tag.getInteger("index");
        this.tile = DimensionManager.getWorld(tag.getInteger("world")).getTileEntity(tag.getInteger("tileX"), tag.getInteger("tileY"), tag.getInteger("tileZ"));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("isNull", isNull);
        if (!isNull) {
            tag.setTag("stack", stack.writeToNBT(new NBTTagCompound()));
        }
        tag.setInteger("index", indexClicked);
        tag.setInteger("tileX", tile.xCoord);
        tag.setInteger("tileY", tile.yCoord);
        tag.setInteger("tileZ", tile.zCoord);
        tag.setInteger("world", tile.getWorldObj().provider.dimensionId);
        ByteBufUtils.writeTag(buf, tag);
    }
}
