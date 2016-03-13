package OpenBees.enums;

import OpenBees.OpenBees;
import OpenBees.genetics.beeProduct;
import OpenBees.init.modItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class speciesEnum
{

    public enum Species {
        FOREST("openbees.species.forest.name", 0x00cc00, 0x006600, new String[]{
                "Workspeed.SLOW",
                "Lifespan.SHORT",
                "Pollination.SLOW",
                "Flower.YELLOW",
                "Climate.TEMPERATE",
                "Fertility.LOW",
                "Effect.NONE",
                "Nocturnal.FALSE",
                "Cave.FALSE",
                "Rain.FALSE",
                "Territory.NORMAL"},
                Arrays.asList(new beeProduct[]{new beeProduct(OpenBees.items.honey_comb.getStack(1, 0), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[]{BiomeDictionary.Type.FOREST})),
        MEADOWS("openbees.species.meadows.name", 0xe0dc98, 0xcfbc83, new String[]{
                "Workspeed.FAST",
                "Lifespan.SHORT",
                "Pollination.SLOW",
                "Flower.YELLOW",
                "Climate.TEMPERATE",
                "Fertility.LOW",
                "Effect.NONE",
                "Nocturnal.FALSE",
                "Cave.FALSE",
                "Rain.FALSE",
                "Territory.NORMAL"},
                Arrays.asList(new beeProduct[]{new beeProduct(OpenBees.items.honey_comb.getStack(1, 0), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS})),
        MODEST("openbees.species.modest.name", 0xffff32, 0x4c4c00, new String[]{
                "Workspeed.FAST",
                "Lifespan.SHORT",
                "Pollination.SLOW",
                "Flower.CACTUS",
                "Climate.HOT",
                "Fertility.LOW",
                "Effect.NONE",
                "Nocturnal.FALSE",
                "Cave.FALSE",
                "Rain.FALSE",
                "Territory.NORMAL"},
                Arrays.asList(new beeProduct[]{new beeProduct(OpenBees.items.honey_comb.getStack(1, 2), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[]{BiomeDictionary.Type.SANDY})),
        MARSHY("openbees.species.marshy.name", 0x556b2f, 0xa2cd5a, new String[]{
                "Workspeed.FAST",
                "Lifespan.SHORT",
                "Pollination.SLOW",
                "Flower.BROWNMUSHROOM",
                "Climate.TEMPERATE",
                "Fertility.LOW",
                "Effect.NONE",
                "Nocturnal.FALSE",
                "Cave.FALSE",
                "Rain.FALSE",
                "Territory.NORMAL"},
                Arrays.asList(new beeProduct[]{new beeProduct(OpenBees.items.honey_comb.getStack(1, 0), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[]{BiomeDictionary.Type.SWAMP})),
        WINTRY("openbees.species.wintry.name", 0x7fffd4, 0x53868b, new String[]{
                "Workspeed.FAST",
                "Lifespan.SHORT",
                "Pollination.SLOW",
                "Flower.YELLOW",
                "Climate.COLD",
                "Fertility.LOW",
                "Effect.NONE",
                "Nocturnal.FALSE",
                "Cave.FALSE",
                "Rain.FALSE",
                "Territory.NORMAL"},
                Arrays.asList(new beeProduct[]{new beeProduct(OpenBees.items.honey_comb.getStack(1, 3), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[]{BiomeDictionary.Type.SNOWY})),
        TROPICAL("openbees.species.tropical.name", 0x7fff00, 0x458b00, new String[]{
                "Workspeed.FAST",
                "Lifespan.SHORT",
                "Pollination.SLOW",
                "Flower.VINES",
                "Climate.TEMPERATE",
                "Fertility.LOW",
                "Effect.NONE",
                "Nocturnal.FALSE",
                "Cave.FALSE",
                "Rain.FALSE",
                "Territory.NORMAL"},
                Arrays.asList(new beeProduct[]{new beeProduct(OpenBees.items.honey_comb.getStack(1, 4), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[]{BiomeDictionary.Type.JUNGLE})),
        COMMON("openbees.species.common.name", 0x383838, 0x707070, new String[]{
                "Workspeed.FAST",
                "Lifespan.SHORT",
                "Pollination.SLOW",
                "Flower.YELLOW",
                "Climate.TEMPERATE",
                "Fertility.LOW",
                "Effect.NONE",
                "Nocturnal.FALSE",
                "Cave.FALSE",
                "Rain.FALSE",
                "Territory.NORMAL"},
                Arrays.asList(new beeProduct[]{new beeProduct(OpenBees.items.honey_comb.getStack(1, 0), 0.25f)}),
                Arrays.asList(new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS}));

        private String unloc;
        private int bodyColour;
        private int outlineColour;
        private String[] genome;
        private List<beeProduct> products;
        private List<BiomeDictionary.Type> spawnIn;

        Species(String unloc, int bodyColour, int outlineColour, String[] genome, List<beeProduct> products, List<BiomeDictionary.Type> spawnIn) {
            this.unloc = unloc;
            this.bodyColour = bodyColour;
            this.outlineColour = outlineColour;
            this.genome = genome;
            this.products = products;
            this.spawnIn = spawnIn;
        }

        public String getUnloc() {
            return unloc;
        }

        public int getbodyColour() {
            return bodyColour;
        }

        public int getoutlineColour() {
            return outlineColour;
        }

        public String[] getGenome() {
            return genome;
        }

        public List<beeProduct> getProducts() {
            return products;
        }

        public List<BiomeDictionary.Type> getSpawnIn() {
            return spawnIn;
        }
    }
}
