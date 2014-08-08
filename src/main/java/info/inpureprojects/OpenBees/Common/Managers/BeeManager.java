package info.inpureprojects.OpenBees.Common.Managers;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IAlleleManager;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeLogic;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by den on 8/6/2014.
 */
public class BeeManager implements IBeeManager {

    private HashMap<String, ISpecies> beeSpecies = new HashMap();
    private HashMap<String, IBee> templates = new HashMap();
    private IAlleleManager alleles = new AlleleManager();

    @Override
    public void registerSpecies(ISpecies species) {
        beeSpecies.put(species.getTag(), species);
        templates.put(species.getTag(), new BeeImpl(species.generateGenericGenome()));
    }

    @Override
    public IBeeLogic getCurrentLogic() {
        return null;
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
}
