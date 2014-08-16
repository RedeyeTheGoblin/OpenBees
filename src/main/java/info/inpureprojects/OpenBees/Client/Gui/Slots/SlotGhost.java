package info.inpureprojects.OpenBees.Client.Gui.Slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by den on 8/13/2014.
 */
public class SlotGhost extends SlotCustom {

    public SlotGhost(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canTakeStack(EntityPlayer p_82869_1_) {
        return false;
    }
}
