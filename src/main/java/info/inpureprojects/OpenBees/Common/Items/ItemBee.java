package info.inpureprojects.OpenBees.Common.Items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by den on 8/6/2014.
 */
public class ItemBee extends ItemGenetic {

    private String tr;
    private String iconType;

    public ItemBee(String unloc, int maxStack, String iconType) {
        super(unloc, maxStack);
        tr = "item." + unloc + ".name";
        this.iconType = iconType;
        this.setCreativeTab(OpenBeesAPI.getAPI().getClientAPI().creativeTabBees);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        switch (pass) {
            case 0:
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("bee_body");
            case 1:
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon(iconType + "_body");
            case 2:
                return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon(iconType + "_outline");
        }
        return OpenBeesAPI.getAPI().getClientAPI().icons.getIcon("");
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        switch (pass) {
            case 0:
                return OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(stack).getGenome().getSpecies().getBodyColor();
            case 2:
                return OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(stack).getGenome().getSpecies().getOutlineColor();
        }
        return 0xFFFFFF;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) {
        for (Allele.AlleleTypes t : Allele.AlleleTypes.values()) {
            list.add("§3" + LanguageRegistry.instance().getStringLocalization("allele.openbees." + t.toString() + ".name") + ": " + "\u00A7f" + LanguageRegistry.instance().getStringLocalization("allele." + stack.getTagCompound().getCompoundTag("genome").getString(t.toString()).replace("|", ".") + ".name"));
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (ISpecies s : OpenBeesAPI.getAPI().getCommonAPI().beeManager.getSpeciesMap().values()) {
            ItemStack i = new ItemStack(this, 1, 0);
            IBee bee = OpenBeesAPI.getAPI().getCommonAPI().beeManager.getTemplate(s);
            i.setTagCompound(bee.getNBT());
            list.add(i);
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return LanguageRegistry.instance().getStringLocalization(OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(stack).getGenome().getSpecies().getUnlocalizedName()) + " " + LanguageRegistry.instance().getStringLocalization(this.tr);
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 3;
    }
}
