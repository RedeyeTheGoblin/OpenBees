package OpenBees.enums;

import OpenBees.OpenBees;
import OpenBees.init.modItems;
import net.minecraft.item.ItemStack;

import java.util.Locale;

public class typeEnum
{

    public enum Types
    {
        QUEEN,
        PRINCESS,
        DRONE;

        private String name;

        Types()
        {
            this.name = toString().toLowerCase(Locale.ENGLISH);
        }

        public String getName()
        {
            return name;
        }

        public ItemStack createStack()
        {
            switch (this.ordinal())
            {
                case 2:
                    return OpenBees.items.drone.getStack(1);

                case 1:
                    return OpenBees.items.princess.getStack(1);

                case 0:
                    return OpenBees.items.queen.getStack(1);
            }
            return null;
        }
    }
}
