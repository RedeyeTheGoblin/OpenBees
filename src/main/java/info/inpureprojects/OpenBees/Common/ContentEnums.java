package info.inpureprojects.OpenBees.Common;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeProduct;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;

import java.util.Arrays;
import java.util.List;

/**
 * Created by den on 8/6/2014.
 */
public class ContentEnums {

    public static enum Bees {

        FOREST("species.openbees.forest.name", 0x55FFFF, new String[]{
                "openbees|WorkspeedSLOW",
                "openbees|LifespanSHORT",
                "openbees|PollinationSLOW",
                "openbees|FlowerYELLOW",
                "openbees|ClimateFOREST",
                "openbees|FertilityLOW",
                "openbees|EffectNONE",
                "openbees|BooleanFALSE",
                "openbees|BooleanFALSE",
                "openbees|BooleanFALSE",
                "openbees|TerritoryNORMAL"},
                Arrays.asList(new BeeProduct[]{new BeeProduct(OpenBeesAPI.getAPI().getCommonAPI().items.honey_comb.getStack(1), 1.0f)}));

        private String unloc;
        private int bodyColor;
        private String[] genome;
        private List<BeeProduct> products;

        Bees(String unloc, int bodyColor, String[] genome, List<BeeProduct> products) {
            this.unloc = unloc;
            this.bodyColor = bodyColor;
            this.genome = genome;
            this.products = products;
        }

        public String getUnloc() {
            return unloc;
        }

        public int getBodyColor() {
            return bodyColor;
        }

        public String[] getGenome() {
            return genome;
        }

        public List<BeeProduct> getProducts() {
            return products;
        }
    }

    public static enum Combs {

        HONEY(0xf3c503, 0xffff99, "item.openbees.comb.honey.name");

        private int primary;
        private int secondary;
        private String unloc;

        Combs(int primary, int secondary, String unloc) {
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
