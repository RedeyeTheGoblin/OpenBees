package OpenBees.item;

import OpenBees.OpenBees;
import OpenBees.enums.combEnum;
import OpenBees.handler.creativeTabHandler;
import OpenBees.handler.textureHandler;
import OpenBees.item.interfaces.combItem;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import java.util.List;

@combItem
public class itemComb extends itemBase {

    private String unloc;

    public itemComb(String unloc, int maxStack)
    {
        super(unloc, maxStack);
        this.unloc = unloc;
        this.setCreativeTab(creativeTabHandler.creativeTabBees);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return LanguageRegistry.instance().getStringLocalization(combEnum.Combs.values()[stack.getItemDamage()].getUnloc()) + " " + LanguageRegistry.instance().getStringLocalization("item." + this.unloc + ".name");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass)
    {
        switch (pass)
        {
            case 0:
                return OpenBees.coreTexHandler.getIcon("comb_base");

            case 1:
                return OpenBees.coreTexHandler.getIcon("comb_overlay");
        }

        return OpenBees.coreTexHandler.getIcon("");
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass)
    {
        switch (pass)
        {
            case 0:
                return combEnum.Combs.values()[stack.getItemDamage()].getPrimary();

            case 1:
                return combEnum.Combs.values()[stack.getItemDamage()].getSecondary();
        }

        return 0;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
    {

    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List validCombs)
    {
        for (combEnum.Combs comb : combEnum.Combs.values())
        {
            validCombs.add(new ItemStack(this, 1, comb.ordinal()));
        }
    }

    @Override
    public int getRenderPasses(int metadata)
    {
        return 2;
    }
}
