package info.inpureprojects.OpenBees.Common.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeProduct;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by den on 8/9/2014.
 */
public class JsonBee {

    public static Gson json = new GsonBuilder().setPrettyPrinting().create();
    private String unloc;
    private int bodyColor;
    private int outlineColor;
    private String[] genome;
    private List<BeeProductJson> products;
    private List<BiomeDictionary.Type> spawnIn;

    public JsonBee(String unloc, int bodyColor, int outlineColor, String[] genome, List<BeeProductJson> products, List<BiomeDictionary.Type> spawnIn) {
        this.unloc = unloc;
        this.bodyColor = bodyColor;
        this.outlineColor = outlineColor;
        this.genome = genome;
        this.products = products;
        this.spawnIn = spawnIn;
    }

    public static ArrayList<BeeProductJson> convert(List<BeeProduct> p) {
        ArrayList<BeeProductJson> list = new ArrayList();
        for (BeeProduct d : p) {
            list.add(new BeeProductJson(GameRegistry.findUniqueIdentifierFor(d.getStack().getItem()).modId, GameRegistry.findUniqueIdentifierFor(d.getStack().getItem()).name, d.getStack().getItemDamage(), d.getChance()));
        }
        return list;
    }

    public static Gson getJson() {
        return json;
    }

    public static void setJson(Gson json) {
        JsonBee.json = json;
    }

    public String getUnloc() {
        return unloc;
    }

    public void setUnloc(String unloc) {
        this.unloc = unloc;
    }

    public int getBodyColor() {
        return bodyColor;
    }

    public void setBodyColor(int bodyColor) {
        this.bodyColor = bodyColor;
    }

    public int getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(int outlineColor) {
        this.outlineColor = outlineColor;
    }

    public String[] getGenome() {
        return genome;
    }

    public void setGenome(String[] genome) {
        this.genome = genome;
    }

    public List<BeeProductJson> getProducts() {
        return products;
    }

    public void setProducts(List<BeeProductJson> products) {
        this.products = products;
    }

    public List<BiomeDictionary.Type> getSpawnIn() {
        return spawnIn;
    }

    public void setSpawnIn(List<BiomeDictionary.Type> spawnIn) {
        this.spawnIn = spawnIn;
    }

    public static class BeeProductJson {

        private String modid;
        private String item;
        private int meta;
        private float chance;

        public BeeProductJson(String modid, String item, int meta, float chance) {
            this.modid = modid;
            this.item = item;
            this.meta = meta;
            this.chance = chance;
        }

        public String getModid() {
            return modid;
        }

        public void setModid(String modid) {
            this.modid = modid;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public int getMeta() {
            return meta;
        }

        public void setMeta(int meta) {
            this.meta = meta;
        }

        public float getChance() {
            return chance;
        }

        public void setChance(float chance) {
            this.chance = chance;
        }
    }
}
