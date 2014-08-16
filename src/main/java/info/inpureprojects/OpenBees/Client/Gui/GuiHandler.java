package info.inpureprojects.OpenBees.Client.Gui;

import cpw.mods.fml.common.network.IGuiHandler;
import info.inpureprojects.OpenBees.API.modInfo;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileApiary;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileCarpenter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.HashMap;

/**
 * Created by den on 8/7/2014.
 */
public class GuiHandler implements IGuiHandler {

    public HashMap<Integer, ResourceLocation> backgrounds = new HashMap();

    public GuiHandler() {
        backgrounds.put(0, new ResourceLocation(modInfo.modid, "textures/gui/gui_apiary.png"));
        backgrounds.put(1, new ResourceLocation(modInfo.modid, "textures/gui/gui_carpenter.png"));
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity t = world.getTileEntity(x, y, z);
        switch (ID) {
            case 0:
                return new ContainerApiary(player, (TileApiary) t);
            case 1:
                return new ContainerCarpenter(player, (TileCarpenter) t);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity t = world.getTileEntity(x, y, z);
        switch (ID) {
            case 0:
                return new GuiApiary(new ContainerApiary(player, (TileApiary) t), backgrounds.get(ID));
            case 1:
                return new GuiCarpenter(new ContainerCarpenter(player, (TileCarpenter) t), backgrounds.get(ID));
        }
        return null;
    }
}
