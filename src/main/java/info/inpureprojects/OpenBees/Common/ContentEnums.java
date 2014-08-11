package info.inpureprojects.OpenBees.Common;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeProduct;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Arrays;
import java.util.List;

/**
 * Created by den on 8/6/2014.
 */
public class ContentEnums {

    public static enum Bees {

        FOREST("species.openbees.forest.name", 0xFFFF00, 0x00CCFF, new String[]{
                "openbees|WorkspeedSLOW",
                "openbees|LifespanSHORT",
                "openbees|PollinationSLOW",
                "openbees|FlowerYELLOW",
                "openbees|ClimateTEMPERATE",
                "openbees|FertilityLOW",
                "openbees|EffectNONE",
                "openbees|BooleanFALSE",
                "openbees|BooleanFALSE",
                "openbees|BooleanFALSE",
                "openbees|TerritoryNORMAL"},
                Arrays.asList(new BeeProduct[]{new BeeProduct(OpenBeesAPI.getAPI().getCommonAPI().items.honey_comb.getStack(1), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[]{BiomeDictionary.Type.FOREST})),
        MEADOWS("species.openbees.meadows.name", 0xFFFF00, 0xFF6600, new String[]{
                "openbees|WorkspeedSLOW",
                "openbees|LifespanSHORT",
                "openbees|PollinationSLOW",
                "openbees|FlowerYELLOW",
                "openbees|ClimateTEMPERATE",
                "openbees|FertilityLOW",
                "openbees|EffectNONE",
                "openbees|BooleanFALSE",
                "openbees|BooleanFALSE",
                "openbees|BooleanFALSE",
                "openbees|TerritoryNORMAL"},
                Arrays.asList(new BeeProduct[]{new BeeProduct(OpenBeesAPI.getAPI().getCommonAPI().items.honey_comb.getStack(1), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS})),
        COMMON("species.openbees.common.name", 0xFFFF00, 0xFFFFFF, new String[]{
                "openbees|WorkspeedSLOW",
                "openbees|LifespanSHORT",
                "openbees|PollinationSLOW",
                "openbees|FlowerYELLOW",
                "openbees|ClimateTEMPERATE",
                "openbees|FertilityLOW",
                "openbees|EffectNONE",
                "openbees|BooleanFALSE",
                "openbees|BooleanFALSE",
                "openbees|BooleanFALSE",
                "openbees|TerritoryNORMAL"},
                Arrays.asList(new BeeProduct[]{new BeeProduct(OpenBeesAPI.getAPI().getCommonAPI().items.honey_comb.getStack(1), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[0]));


        private String unloc;
        private int bodyColor;
        private int outlineColor;
        private String[] genome;
        private List<BeeProduct> products;
        private List<BiomeDictionary.Type> spawnIn;

        Bees(String unloc, int bodyColor, int outlineColor, String[] genome, List<BeeProduct> products, List<BiomeDictionary.Type> spawnIn) {
            this.unloc = unloc;
            this.bodyColor = bodyColor;
            this.outlineColor = outlineColor;
            this.genome = genome;
            this.products = products;
            this.spawnIn = spawnIn;
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

        public int getOutlineColor() {
            return outlineColor;
        }

        public List<BiomeDictionary.Type> getSpawnIn() {
            return spawnIn;
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
