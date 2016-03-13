package OpenBees.enums;

public class dropEnum {

    public enum Drops {
        HONEY(0xfec128, 0xdca824, "item.openbees.drop.honey.name"),
        STICKY(0x239b1a, 0x114b0d, "item.openbees.drop.sticky.name"),
        FROSTED(0x89ecdf, 0x40ad9f, "item.openbees.drop.frosted.name");

        private int primary;
        private int secondary;
        private String unloc;

        Drops(int primary, int secondary, String unloc){
            this.primary = primary;
            this.secondary = secondary;
            this.unloc = unloc;
        }

        public int getPrimary() {
            return primary;
        }

        public int getSecondary() {
            return secondary;
        }

        public String getUnloc() {
            return unloc;
        }
    }
}
