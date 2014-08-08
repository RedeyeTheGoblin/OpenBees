package info.inpureprojects.OpenBees.Common.Items;

import com.google.common.collect.Sets;
import info.inpureprojects.OpenBees.API.Common.Tools.ScoopItem;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by den on 8/8/2014.
 */
@ScoopItem
public class ItemScoop extends ItemToolBase {

    private String icon;

    public ItemScoop(String unloc, ToolMaterial material, String icon) {
        super(unloc, material, Sets.newHashSet(OpenBeesAPI.getAPI().getCommonAPI().blocks.beehive.getBlock()), "scoop", 1);
        this.icon = icon;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon(this.icon);
    }
}
