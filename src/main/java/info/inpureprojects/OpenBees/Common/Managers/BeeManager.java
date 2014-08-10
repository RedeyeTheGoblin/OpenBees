package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.BeeUtils;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IAlleleManager;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeLogic;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeManager;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.Genetics.PunnettSquare;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.*;

/**
 * Created by den on 8/6/2014.
 */
public class BeeManager implements IBeeManager {

    private HashMap<String, ISpecies> beeSpecies = new HashMap();
    private HashMap<String, IBee> templates = new HashMap();
    private IAlleleManager alleles = new AlleleManager();
    private IBeeLogic logic = new BeeLogic();
    private HashMap<BiomeDictionary.Type, ArrayList<ISpecies>> hiveMap = new HashMap();

    @Override
    public List<ISpecies> getSpeciesForBiome(BiomeGenBase biome) {
        ArrayList<ISpecies> r = new ArrayList();
        for (BiomeDictionary.Type t : BiomeDictionary.getTypesForBiome(biome)) {
            if (hiveMap.containsKey(t)) {
                r.addAll(hiveMap.get(t));
            }
        }
        return r;
    }

    @Override
    public void registerBeeForHive(ISpecies species, List<BiomeDictionary.Type> biomes) {
        for (BiomeDictionary.Type t : biomes) {
            if (!hiveMap.containsKey(t)) {
                hiveMap.put(t, new ArrayList());
            }
            hiveMap.get(t).add(species);
        }
    }

    @Override
    public void registerSpecies(ISpecies species) {
        beeSpecies.put(species.getTag(), species);
        templates.put(species.getTag(), new BeeImpl(species.generateGenericGenome()));
    }

    @Override
    public IBeeLogic getCurrentLogic() {
        return this.logic;
    }

    @Override
    public ISpecies getSpeciesByTag(String tag) {
        return beeSpecies.get(tag);
    }

    @Override
    public IBee convertStackToBee(ItemStack stack) {
        return new BeeImpl(stack.getTagCompound());
    }

    @Override
    public Map<String, ISpecies> getSpeciesMap() {
        return Collections.unmodifiableMap(beeSpecies);
    }

    @Override
    public IBee getTemplate(ISpecies species) {
        return templates.get(species.getTag());
    }

    @Override
    public NBTTagCompound getBlankGenome() {
        return new NBTTagCompound();
    }

    @Override
    public ItemStack getBeeBySpeciesTag(String tag, Type type) {
        return this.getBeeBySpecies(this.getSpeciesByTag(tag), type);
    }

    @Override
    public ItemStack getBeeBySpecies(ISpecies species, Type type) {
        ItemStack bee = type.createStack();
        IBee ibee = this.getTemplate(species);
        bee.setTagCompound(ibee.getNBT());
        return bee;
    }

    @Override
    public IAlleleManager getAlleleManager() {
        return this.alleles;
    }

    public static class AlleleManager implements IAlleleManager {

        private HashMap<String, Allele> alleles = new HashMap();

        @Override
        public void registerAllele(Allele allele) {
            alleles.put(allele.getTag(), allele);
        }

        @Override
        public Allele getAlleleByTag(String tag) {
            return alleles.get(tag);
        }

        @Override
        public Map<String, Allele> getAlleleMap() {
            return Collections.unmodifiableMap(this.alleles);
        }
    }

    public static class BeeLogic implements IBeeLogic {
        @Override
        public ItemStack combine(ItemStack princess, ItemStack drone) {
            IBee p = OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(princess);
            IBee d = OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(drone);
            p.setMate(d);
            ItemStack q = Type.QUEEN.createStack();
            q.setTagCompound(p.getNBT());
            return q;
        }

        @Override
        public ItemStack produceOffspring(ItemStack queen, boolean princess) {
            IBee q = OpenBeesAPI.getAPI().getCommonAPI().beeManager.convertStackToBee(queen);
            Map<Allele.AlleleTypes, PunnettSquare> map = PunnettSquare.getGeneticPotentials(q);
            HashMap<Allele.AlleleTypes, Allele> dominant = new HashMap();
            HashMap<Allele.AlleleTypes, Allele> recessive = new HashMap();
            Random r = new Random();
            for (Allele.AlleleTypes t : map.keySet()) {
                int roll = r.nextInt(map.get(t).getPotential().size() - 1);
                dominant.put(t, (Allele) map.get(t).getPotential().get(roll)[0]);
                recessive.put(t, (Allele) map.get(t).getPotential().get(roll)[1]);
            }
            PunnettSquare speciesRoll = new PunnettSquare(new Object[]{q.getDominantGenome().getSpecies(), q.getRecessiveGenome().getSpecies()}, new Object[]{q.getMate().getDominantGenome().getSpecies(), q.getMate().getRecessiveGenome().getSpecies()});
            int sRoll = r.nextInt(speciesRoll.getPotential().size() - 1);
            ISpecies i1 = (ISpecies) speciesRoll.getPotential().get(sRoll)[0];
            ISpecies i2 = (ISpecies) speciesRoll.getPotential().get(sRoll)[1];
            NBTTagCompound newBee = BeeUtils.instance.generateGenome(i1, i2, dominant, recessive, i1 != i2, BeeUtils.GENERATE_NEW_LIFE_FLAG, null);
            ItemStack b;
            if (princess) {
                b = Type.PRINCESS.createStack();
            } else {
                b = Type.DRONE.createStack();
            }
            b.setTagCompound(newBee);
            return b;
        }

        @Override
        public List<ItemStack> produceItemsOnTick(ItemStack queen, int tick) {
            return null;
        }
    }
}
