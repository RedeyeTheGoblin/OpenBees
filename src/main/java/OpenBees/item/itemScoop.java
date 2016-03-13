package OpenBees.item;

import OpenBees.OpenBees;
import OpenBees.item.interfaces.scoopItem;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

@scoopItem
public class itemScoop extends itemToolBase {

   private String icon;

    public itemScoop(String unloc, ToolMaterial material, String icon) {
        super(unloc, material, Sets.newHashSet(new Block[] {OpenBees.blocks.beehive.getBlock()}), "scoop", 1);
        this.icon = icon;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return OpenBees.coreTexHandler.getIcon(this.icon);
    }
}
