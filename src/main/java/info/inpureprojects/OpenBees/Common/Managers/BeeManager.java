package info.inpureprojects.OpenBees.Common.Managers;

import cofh.lib.util.position.BlockPosition;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Allele;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.*;
import info.inpureprojects.OpenBees.API.Common.Bees.IBee;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeKeepingTile;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeLogic;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeManager;
import info.inpureprojects.OpenBees.API.Common.Tools.IFrameItem;
import info.inpureprojects.OpenBees.API.Common.Tools.ModifierBlock;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.Common.Genetics.PunnettSquare;
import net.minecraft.block.Block;
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
    private ArrayList<Mutation> mutations = new ArrayList();
    private HashMap<String, ModifierBlock> modBlocks = new HashMap();

    @Override
    public void registerMutation(String species1, String species2, String outcome, float chance) {
        this.registerMutation(this.getSpeciesByTag(species1), this.getSpeciesByTag(species2), this.getSpeciesByTag(outcome), chance);
    }

    @Override
    public void registerModifierBlock(ModifierBlock block) {
        modBlocks.put(block.getTag(), block);
    }

    @Override
    public ModifierBlock getModifierBlock(Block block, int meta) {
        ModifierBlockComparison c = new ModifierBlockComparison(block, meta);
        if (modBlocks.containsKey(c.getTag())) {
            return modBlocks.get(c.getTag());
        }
        return null;
    }

    @Override
    public void registerCustomMutation(Mutation mut) {
        mutations.add(mut);
    }

    @Override
    public void registerMutation(ISpecies species1, ISpecies species2, ISpecies outcome, float chance) {
        mutations.add(new Mutation(species1, species2, outcome, chance));
    }

    @Override
    public List<Mutation> getMutations(ISpecies species1, ISpecies species2) {
        Mutation testCase = new Mutation(species1, species2, null, 0.0f);
        List<Mutation> potential = new ArrayList();
        for (Mutation m : mutations) {
            if (m.isEqual(testCase)) {
                potential.add(m);
            }
        }
        return Collections.unmodifiableList(potential);
    }

    @Override
    public List<ISpecies> getSpeciesForBiome(BiomeGenBase biome) {
        ArrayList<ISpecies> r = new ArrayList();
        for (BiomeDictionary.Type t : BiomeDictionary.getTypesForBiome(biome)) {
            if (hiveMap.containsKey(t)) {
                r.addAll(hiveMap.get(t));
            }
        }
        return Collections.unmodifiableList(r);
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
    public List<ModifierBlock> getModifierBlocksNear(IBeeKeepingTile tile) {
        List<ModifierBlock> blocks = new ArrayList();
        for (BlockPosition b : tile.getSurroundingBlocks()) {
            if (b.blockExists(tile.getWorld())) {
                ModifierBlock block = OpenBeesAPI.getAPI().getCommonAPI().beeManager.getModifierBlock(tile.getWorld().getBlock(b.x, b.y, b.z), tile.getWorld().getBlockMetadata(b.x, b.y, b.z));
                if (block != null) {
                    blocks.add(block);
                }
            }
        }
        return Collections.unmodifiableList(blocks);
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
        public ItemStack combine(IBeeKeepingTile tile) {
            IBee p = tile.getQueen();
            IBee d = tile.getDrone();
            p.setMate(d);
            for (IFrameItem i : tile.getFrames()) {
                p.setLifeTicks(p.getLifeTicks() + i.getLifespanModifier());
            }
            for (ModifierBlock b : OpenBeesAPI.getAPI().getCommonAPI().beeManager.getModifierBlocksNear(tile)) {
                p.setLifeTicks(p.getLifeTicks() + b.getLifespanModifier());
            }
            ItemStack q = Type.QUEEN.createStack();
            q.setTagCompound(p.getNBT());
            return q;
        }

        @Override
        public ItemStack produceOffspring(IBeeKeepingTile tile, List<IFrameItem> allModifiers, boolean princess) {
            Random r = new Random();
            IBee q = tile.getQueen();
            Mutation selectedMutation = null;
            // Process potential mutations.
            List<Mutation> potentialMutations = OpenBeesAPI.getAPI().getCommonAPI().beeManager.getMutations(q.getDominantGenome().getSpecies(), q.getMate().getDominantGenome().getSpecies());
            if (!potentialMutations.isEmpty()) {
                // If multiple possible mutations random roll one.
                int selectMutation = r.nextInt(potentialMutations.size());
                Mutation mut = potentialMutations.get(selectMutation);
                float chance = mut.getChance();
                // Give frames a chance to modify the chances.
                for (IFrameItem i : allModifiers) {
                    chance = chance + (chance * i.getMutationModifer());
                }
                // Roll for if the mutation happens.
                float mutRoll = r.nextFloat();
                if (mutRoll <= chance) {
                    selectedMutation = mut;
                }
            }
            Map<Allele.AlleleTypes, PunnettSquare> map = PunnettSquare.getGeneticPotentials(q);
            HashMap<Allele.AlleleTypes, Allele> dominant = new HashMap();
            HashMap<Allele.AlleleTypes, Allele> recessive = new HashMap();
            for (Allele.AlleleTypes t : map.keySet()) {
                int roll = r.nextInt(map.get(t).getPotential().size() - 1);
                dominant.put(t, (Allele) map.get(t).getPotential().get(roll)[0]);
                recessive.put(t, (Allele) map.get(t).getPotential().get(roll)[1]);
            }
            PunnettSquare speciesRoll;
            if (selectedMutation == null) {
                speciesRoll = new PunnettSquare(new Object[]{q.getDominantGenome().getSpecies(), q.getRecessiveGenome().getSpecies()}, new Object[]{q.getMate().getDominantGenome().getSpecies(), q.getMate().getRecessiveGenome().getSpecies()});
            } else {
                speciesRoll = new PunnettSquare(new Object[]{q.getDominantGenome().getSpecies(), selectedMutation.getOutcome()}, new Object[]{q.getMate().getDominantGenome().getSpecies(), selectedMutation.getOutcome()});
            }
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
        public List<ItemStack> produceItemsOnTick(IBeeKeepingTile tile) {
            ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
            Random r = new Random();
            for (BeeProduct p : tile.getQueen().getDominantGenome().getSpecies().getPotentialProducts()) {
                float odds = r.nextFloat();
                if (odds <= p.getChance()) {
                    stacks.add(p.getStack().copy());
                }
            }
            return stacks;
        }
    }
}
