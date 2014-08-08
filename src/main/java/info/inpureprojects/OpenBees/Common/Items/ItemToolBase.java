package info.inpureprojects.OpenBees.Common.Items;

import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;

import java.util.List;
import java.util.Set;

/**
 * Created by den on 8/8/2014.
 */
public abstract class ItemToolBase extends ItemTool {

    protected ItemToolBase(String unloc, ToolMaterial material, Set set, String toolClass, int level) {
        super(2.0f, material, set);
        this.setUnlocalizedName(unloc);
        this.setHarvestLevel(toolClass, level);
        this.setCreativeTab(OpenBeesAPI.getAPI().getClientAPI().creativeTabBees);
    }

    @Override
    public abstract IIcon getIcon(ItemStack stack, int pass);

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        list.add("§3" + "Max Uses: " + "\u00A7f" + String.valueOf(this.toolMaterial.getMaxUses()));
        list.add("§3" + "Uses Left: " + "\u00A7f" + String.valueOf(this.toolMaterial.getMaxUses() - stack.getItemDamage()));
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        list.add(new ItemStack(this, 1, 0));
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 1;
    }

}
