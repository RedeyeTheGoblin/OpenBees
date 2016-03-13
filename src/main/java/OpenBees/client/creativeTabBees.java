package OpenBees.client;

import OpenBees.OpenBees;
import OpenBees.enums.typeEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class creativeTabBees extends CreativeTabs {

    public creativeTabBees() {
        super("openbees.bees");
    }

    @Override
    public Item getTabIconItem() {
        return null;
    }

    @Override
    public ItemStack getIconItemStack() {
        return OpenBees.instance.coreBeeHandler.getBeeBySpeciesTag("FOREST", typeEnum.Types.QUEEN);
    }
}
