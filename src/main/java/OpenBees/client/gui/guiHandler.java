package OpenBees.client.gui;

import OpenBees.block.tileEntities.tileApiary;
import OpenBees.block.tileEntities.tileExtractor;
import OpenBees.info.modInfo;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.HashMap;

public class guiHandler implements IGuiHandler {

    public HashMap<Integer, ResourceLocation> backgrounds = new HashMap();

    public guiHandler() {
        backgrounds.put(0, new ResourceLocation(modInfo.modid, "textures/gui/gui_apiary.png"));
        backgrounds.put(1, new ResourceLocation(modInfo.modid, "textures/gui/gui_extractor.png"));
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        switch (ID) {
            case 0:
                return new containerApiary(player, (tileApiary) tile);

            case 1:
                return new containerExtractor(player, (tileExtractor) tile);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);

        switch (ID) {
            case 0:
                return new guiApiary(new containerApiary(player, (tileApiary) tile), backgrounds.get(ID));

            case 1:
                return new guiExtractor(new containerExtractor(player, (tileExtractor) tile), backgrounds.get(ID));
        }

        return null;
    }
}
