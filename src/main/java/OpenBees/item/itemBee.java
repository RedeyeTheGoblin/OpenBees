package OpenBees.item;

import OpenBees.OpenBees;
import OpenBees.genetics.Allele;
import OpenBees.genetics.IBee;
import OpenBees.genetics.ISpecies;
import OpenBees.handler.creativeTabHandler;
import OpenBees.utility.logHelper;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class itemBee extends itemGenetic {

    private String tr;
    private String iconType;

    public itemBee(String unloc, int maxStack, String iconType)
    {
        super(unloc, maxStack);
        tr = "item." + unloc + ".name";
        this.iconType = iconType;
        this.setCreativeTab(creativeTabHandler.creativeTabBees);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass)
    {
        switch (pass)
        {
            case 0:
                return OpenBees.coreTexHandler.getIcon("bee_body");

            case 1:
                return OpenBees.coreTexHandler.getIcon(iconType + "_body");

            case 2:
                return OpenBees.coreTexHandler.getIcon(iconType + "_outline");
        }

        return  OpenBees.coreTexHandler.getIcon("");
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass)
    {
        switch (pass)
        {
            case 0:
                return OpenBees.coreBeeHandler.convertStacktoBee(stack).getDominantGenome().getSpecies().getBodyColour();

            case 2:
                return OpenBees.coreBeeHandler.convertStacktoBee(stack).getDominantGenome().getSpecies().getOutlineColour();
        }

        return 0xFFFFFF;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
    {
        for (Allele.AlleleTypes type : Allele.AlleleTypes.values())
        {
            if (OpenBees.proxy.isShiftKey())
            {
                list.add(LanguageRegistry.instance().getStringLocalization("allele.openbees." + type.toString() + ".name") + ": " + LanguageRegistry.instance().getStringLocalization("allele." + stack.getTagCompound().getCompoundTag("PrimaryGenome").getString(type.toString()) + ".name"));
            }

            if (OpenBees.proxy.isShiftCtrlKey())
            {
                list.add(LanguageRegistry.instance().getStringLocalization("allele.openbees." + type.toString() + ".name") + ": " + LanguageRegistry.instance().getStringLocalization("allele." + stack.getTagCompound().getCompoundTag("SecondaryGenome").getString(type.toString()) + ".name"));
            }
        }

        if (!OpenBees.proxy.isShiftKey() && !OpenBees.proxy.isCtrlKey())
        {
            list.add("Hold Shift for dominant genome");
        } else if (!OpenBees.proxy.isShiftCtrlKey())
        {
            list.add("Hold ctrl + shift for recessive genome");
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List validBees)
    {
        for (ISpecies species : OpenBees.coreBeeHandler.getSpeciesMap().values())
        {
            ItemStack stack = new ItemStack(this, 1, 0);
            IBee bee = OpenBees.coreBeeHandler.getTemplate(species);
            stack.setTagCompound(bee.getNBT());
            validBees.add(stack);
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        IBee bee = OpenBees.coreBeeHandler.convertStacktoBee(stack);
        if (bee.isHybrid())
        {
            return LanguageRegistry.instance().getStringLocalization(bee.getDominantGenome().getSpecies().getUnlocalizedName()) + "-" + LanguageRegistry.instance().getStringLocalization(bee.getRecessiveGenome().getSpecies().getUnlocalizedName()) + " " + LanguageRegistry.instance().getStringLocalization(this.tr);
        } else
        {
            return LanguageRegistry.instance().getStringLocalization(bee.getDominantGenome().getSpecies().getUnlocalizedName()) + " " + LanguageRegistry.instance().getStringLocalization(this.tr);
        }
    }

    @Override
    public int getRenderPasses(int metadata)
    {
        return 3;
    }
}
