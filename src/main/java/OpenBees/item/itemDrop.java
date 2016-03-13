package OpenBees.item;

import OpenBees.OpenBees;
import OpenBees.enums.dropEnum;
import OpenBees.handler.creativeTabHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class itemDrop extends itemBase {

    private String unloc;

    public itemDrop(String unloc, int maxStack) {
        super(unloc, maxStack);
        this.unloc = unloc;
        this.setCreativeTab(creativeTabHandler.creativeTabBees);
        this.setHasSubtypes(true);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return LanguageRegistry.instance().getStringLocalization(dropEnum.Drops.values()[stack.getItemDamage()].getUnloc()) + " " + LanguageRegistry.instance().getStringLocalization("item." + this.unloc + ".name");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        switch (pass) {
            case 0:
                return OpenBees.coreTexHandler.getIcon("drop_base");

            case 1:
                return OpenBees.coreTexHandler.getIcon("drop_outline");
        }

        return OpenBees.coreTexHandler.getIcon("");
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass){
        switch (pass) {
            case 0:
                return dropEnum.Drops.values()[stack.getItemDamage()].getSecondary();

            case 1:
                return dropEnum.Drops.values()[stack.getItemDamage()].getPrimary();
        }

        return 0;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {

    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List validDrops){
        for (dropEnum.Drops drop : dropEnum.Drops.values()) {
            validDrops.add(new ItemStack(this, 1, drop.ordinal()));
            OreDictionary.registerOre("beeDrop", OpenBees.items.bee_drop.getStack(1, drop.ordinal()));
        }
    }

    @Override
    public int getRenderPasses(int meta){
        return 2;
    }
}
