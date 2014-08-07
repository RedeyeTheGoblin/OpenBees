package info.inpureprojects.OpenBees.Client.Gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by den on 8/7/2014.
 */
public class ContainerApiary extends Container {

    public ContainerApiary() {
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
}
