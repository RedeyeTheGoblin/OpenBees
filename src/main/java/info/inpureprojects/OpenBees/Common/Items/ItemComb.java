package info.inpureprojects.OpenBees.Common.Items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.ContentEnums;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by den on 8/6/2014.
 */
public class ItemComb extends ItemBase {

    private String unloc;

    public ItemComb(String unloc, int maxStack) {
        super(unloc, maxStack);
        this.unloc = unloc;
        this.setCreativeTab(OpenBeesAPI.getAPI().getClientAPI().creativeTabCombs);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return LanguageRegistry.instance().getStringLocalization(ContentEnums.Combs.values()[stack.getItemDamage()].getUnloc()) + " " + LanguageRegistry.instance().getStringLocalization("item." + this.unloc + ".name");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        switch (pass) {
            case 0:
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("comb_base");
            case 1:
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("comb_overlay");
        }
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("");
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        switch (pass) {
            case 0:
                return ContentEnums.Combs.values()[stack.getItemDamage()].getPrimary();
            case 1:
                return ContentEnums.Combs.values()[stack.getItemDamage()].getSecondary();
        }
        return 0;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {

    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (ContentEnums.Combs c : ContentEnums.Combs.values()) {
            list.add(new ItemStack(this, 1, c.ordinal()));
        }
    }


}
