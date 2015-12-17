package OpenBees.enums;

import java.util.Locale;

public class combEnum {

    public enum Combs
    {
        HONEY(0xf3c503, 0xffff99, "item.openbees.comb.honey.name"),
        COCOA(0x674016, 0xffb26b, "item.openbees.comb.cocoa.name"),
        PARCHED(0x4c4c00, 0xffff32, "item.openbees.comb.parched.name"),
        FROZEN(0x53868b, 0x7fffd4, "item.openbees.comb.frozen.name"),
        SILKY(0x458b00, 0x7fff00, "item.openbees.comb.silky.name");

        private int primary;
        private int secondary;
        private String unloc;

        Combs(int primary, int secondary, String unloc)
        {
            this.primary = primary;
            this.secondary = secondary;
            this.unloc = unloc;
        }

        public int getPrimary()
        {
            return primary;
        }

        public int getSecondary()
        {
            return secondary;
        }

        public String getUnloc()
        {
            return unloc;
        }

    }
}
