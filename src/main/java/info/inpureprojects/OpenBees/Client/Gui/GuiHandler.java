package info.inpureprojects.OpenBees.Client.Gui;

import cpw.mods.fml.common.network.IGuiHandler;
import info.inpureprojects.OpenBees.API.modInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.HashMap;

/**
 * Created by den on 8/7/2014.
 */
public class GuiHandler implements IGuiHandler {

    public HashMap<Integer, ResourceLocation> backgrounds = new HashMap();

    public GuiHandler() {
        backgrounds.put(0, new ResourceLocation(modInfo.modid, "textures/gui/apiary.png"));
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                return new ContainerApiary(player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                return new GuiApiary(new ContainerApiary(player), backgrounds.get(ID));
        }
        return null;
    }
}
