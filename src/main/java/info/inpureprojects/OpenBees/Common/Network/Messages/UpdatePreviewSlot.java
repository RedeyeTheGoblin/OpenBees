package info.inpureprojects.OpenBees.Common.Network.Messages;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileCarpenter;
import info.inpureprojects.OpenBees.Common.Managers.CarpenterCraftingManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by den on 8/16/2014.
 */
public class UpdatePreviewSlot implements IMessage, IMessageHandler<UpdatePreviewSlot, IMessage> {

    private TileCarpenter tile;

    public UpdatePreviewSlot() {
    }

    public UpdatePreviewSlot(TileEntity tile) {
        this.tile = (TileCarpenter) tile;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tag = ByteBufUtils.readTag(buf);
        this.tile = (TileCarpenter) DimensionManager.getWorld(tag.getInteger("world")).getTileEntity(tag.getInteger("tileX"), tag.getInteger("tileY"), tag.getInteger("tileZ"));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("tileX", tile.xCoord);
        tag.setInteger("tileY", tile.yCoord);
        tag.setInteger("tileZ", tile.zCoord);
        tag.setInteger("world", tile.getWorldObj().provider.dimensionId);
        ByteBufUtils.writeTag(buf, tag);
    }

    @Override
    public IMessage onMessage(UpdatePreviewSlot message, MessageContext ctx) {
        CarpenterCraftingManager.getInstance().validateRecipe(message.tile);
        return null;
    }
}
